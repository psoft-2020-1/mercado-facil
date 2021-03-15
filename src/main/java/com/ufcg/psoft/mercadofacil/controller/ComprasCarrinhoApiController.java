package com.ufcg.psoft.mercadofacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.ComprasCarrinhoService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ComprasCarrinhoApiController {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	ComprasCarrinhoService comprasCarrinhoService;

	@RequestMapping(value = "/carrinho/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> adcionarItemCarrinho(@PathVariable("id") long id, @RequestBody int numItens) {
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);

		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}

		Produto produto = optionalProduto.get();
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, numItens);

		return comprasCarrinhoService.adcionarItemCarrinho(id, carrinhoItem);
	}

	@RequestMapping(value = "/carrinho/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerItemCarrinho(@PathVariable("id") long id, @RequestBody int numItens) {
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);

		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}

		Produto produto = optionalProduto.get();
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, numItens);

		return comprasCarrinhoService.removerItemCarrinho(id, carrinhoItem);
	}

	@RequestMapping(value = "/carrinho", method = RequestMethod.GET)
	public ResponseEntity<?> listarItensCarrinho() {
		return comprasCarrinhoService.listarItensCarrinho();
	}
	
	@RequestMapping(value = "/carrinho", method = RequestMethod.DELETE)
	public ResponseEntity<?> descartarCarrinho() {
		return comprasCarrinhoService.descartarCarrinho();
	}

	@RequestMapping(value = "/compras/pagamento/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> finalizarCompra(@PathVariable("id") long idFormaDePagamento) {
		return comprasCarrinhoService.finalizarCompra(idFormaDePagamento);
	}
	
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	public ResponseEntity<?> listarCompras() {
		return comprasCarrinhoService.listarCompras();
	}
	
	@RequestMapping(value = "/compras/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarCompra(@PathVariable("id") long idCompra) {
		return comprasCarrinhoService.consultarCompra(idCompra);
	}
	
	@RequestMapping(value = "/compras/pagamento", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarFormaDePagamento(@RequestBody FormaDePagamentoDTO formaDePagamentoDTO) {
		return comprasCarrinhoService.adicionarFormaDePagamento(formaDePagamentoDTO);
	}
	
	@RequestMapping(value = "/compras/pagamento", method = RequestMethod.GET)
	public ResponseEntity<?> listarFormasDePagamento() {
		return comprasCarrinhoService.listarFormasDePagamento();
	}
}