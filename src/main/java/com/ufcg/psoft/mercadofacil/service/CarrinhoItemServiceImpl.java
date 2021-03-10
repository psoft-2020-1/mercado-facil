package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.DTO.DadosCompraDTO;
import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoItemRepository;
import com.ufcg.psoft.mercadofacil.util.ErroCarrinhoItem;

/**
 * Classe responsável pela lógica de negócio do carrinho.
 * 
 * @author henri
 *
 */
@Service
public class CarrinhoItemServiceImpl implements CarrinhoItemService {

	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;

	@Autowired
	private LoteService loteService;

	/**
	 * Adiciona uma certa quantidade de um produto ao carrinho, subtraindo as
	 * unidades dos lotes.
	 */
	@Override
	public ResponseEntity<?> adcionarItemCarrinho(long id, CarrinhoItem carrinhoItem) {
		List<Lote> lotesProduto = loteService.getLoteByProduto(carrinhoItem.getProduto());

		int estoqueTotal = 0;
		for (Lote lote : lotesProduto) {
			estoqueTotal += lote.getNumeroDeItens();
		}

		if (estoqueTotal >= carrinhoItem.getNumeroDeItens()) {
			List<CarrinhoItem> itensCarrinho = carrinhoItemRepository.findByProduto(carrinhoItem.getProduto());

			int itensRestantes = carrinhoItem.getNumeroDeItens();
			int i = 0;
			while (itensRestantes > 0) {
				Lote loteAtual = lotesProduto.get(i);

				if (loteAtual.getNumeroDeItens() >= itensRestantes) {
					loteAtual.setNumeroDeItens(loteAtual.getNumeroDeItens() - itensRestantes);
					itensRestantes = 0;

				} else {
					itensRestantes -= loteAtual.getNumeroDeItens();
					loteAtual.setNumeroDeItens(0);
				}

				loteService.salvarLote(loteAtual);

				i++;
			}

			CarrinhoItem carrinhoAux;
			ResponseEntity<?> resposta;
			if (itensCarrinho.isEmpty()) {
				carrinhoAux = carrinhoItem;

				resposta = new ResponseEntity<>(HttpStatus.CREATED);

			} else {
				carrinhoAux = itensCarrinho.get(0);

				carrinhoAux.adicionaItens(carrinhoItem.getNumeroDeItens());

				resposta = new ResponseEntity<>(HttpStatus.OK);
			}

			carrinhoItemRepository.save(carrinhoAux);

			return resposta;

		} else {
			return ErroCarrinhoItem.erroEstoqueInsuficiente(id);
		}
	}

	/**
	 * Remove uma certa quantidade de um produto do carrinho, devolvendo as unidades
	 * aos lotes.
	 */
	@Override
	public ResponseEntity<?> removerItemCarrinho(long id, CarrinhoItem carrinhoItem) {
		if (carrinhoIsEmpty()) {
			return ErroCarrinhoItem.carrinhoVazio();
		}

		List<CarrinhoItem> itensCarrinho = carrinhoItemRepository.findByProduto(carrinhoItem.getProduto());

		if (itensCarrinho.isEmpty()) {
			return ErroCarrinhoItem.erroItemNaoCadastrado(id);
		}

		CarrinhoItem noCarrinho = itensCarrinho.get(0);

		List<Lote> lotesProduto = loteService.getLoteByProduto(carrinhoItem.getProduto());

		if (noCarrinho.getNumeroDeItens() > carrinhoItem.getNumeroDeItens()) {
			Lote loteAux = lotesProduto.get(0);

			loteAux.setNumeroDeItens(loteAux.getNumeroDeItens() + noCarrinho.getNumeroDeItens());

			loteService.salvarLote(loteAux);

			noCarrinho.removeItens(carrinhoItem.getNumeroDeItens());

			carrinhoItemRepository.save(noCarrinho);

		} else if (noCarrinho.getNumeroDeItens() == carrinhoItem.getNumeroDeItens()) {
			Lote loteAux = lotesProduto.get(0);

			loteAux.setNumeroDeItens(loteAux.getNumeroDeItens() + noCarrinho.getNumeroDeItens());

			loteService.salvarLote(loteAux);

			carrinhoItemRepository.deleteById(noCarrinho.getId());

		} else {
			return ErroCarrinhoItem.erroItemNaoTemUnidadesSuficientes(id);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Encerra a operação de compra, retornando a lista de produtos, suas
	 * quantidades e o custo final.
	 */
	@Override
	public ResponseEntity<?> finalizarCompra() {
		if (carrinhoIsEmpty()) {
			return ErroCarrinhoItem.carrinhoVazio();
		}

		List<CarrinhoItem> itensCarrinho = carrinhoItemRepository.findAll();

		BigDecimal precoTotal = new BigDecimal(0);
		for (CarrinhoItem carrinhoItem : itensCarrinho) {
			precoTotal = precoTotal.add(carrinhoItem.calculaPreco());
		}

		carrinhoItemRepository.deleteAll();

		return new ResponseEntity<>(new DadosCompraDTO(itensCarrinho, precoTotal), HttpStatus.OK);
	}

	/**
	 * Remove todos os itens do carrinho, devolvendo as unidades aos lotes.
	 */
	@Override
	public ResponseEntity<?> descartarCarrinho() {
		if (carrinhoIsEmpty()) {
			return ErroCarrinhoItem.carrinhoVazio();
		}

		List<CarrinhoItem> itensCarrinho = carrinhoItemRepository.findAll();

		for (CarrinhoItem item : itensCarrinho) {
			Produto itemProduto = item.getProduto();

			List<Lote> lotesProduto = loteService.getLoteByProduto(itemProduto);

			Lote loteAux = lotesProduto.get(0);

			loteAux.setNumeroDeItens(loteAux.getNumeroDeItens() + item.getNumeroDeItens());
		}

		carrinhoItemRepository.deleteAll();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Retorna uma listagem dos produtos no carrinho.
	 */
	@Override
	public ResponseEntity<?> listarItensCarrinho() {
		if (carrinhoIsEmpty()) {
			return ErroCarrinhoItem.carrinhoVazio();
		}

		return new ResponseEntity<>(carrinhoItemRepository.findAll(), HttpStatus.OK);
	}

	private boolean carrinhoIsEmpty() {
		List<CarrinhoItem> itensCarrinho = carrinhoItemRepository.findAll();

		if (itensCarrinho.isEmpty()) {
			return true;
		}

		return false;
	}
}