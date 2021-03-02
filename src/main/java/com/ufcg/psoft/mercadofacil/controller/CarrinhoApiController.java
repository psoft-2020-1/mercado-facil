package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.LoteService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroCarrinho;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoApiController {
	@Autowired
	LoteService loteService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	CarrinhoService carrinhoService;
	
	
	@RequestMapping(value = "/carrinho", method = RequestMethod.GET)
	public ResponseEntity<?> consultarCarrinho() {

		List<Compra> compras = carrinhoService.listarCompras();
	
		if (compras.isEmpty()) {
			return ErroCarrinho.erroCarrinhoVazio();
		}
		
		return new ResponseEntity<List<Compra>>(compras, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/carrinho", method = RequestMethod.POST)
	public ResponseEntity<?> adicionarAoCarrinho(@RequestBody Compra compra) {
		
		Optional<Produto> optionalProduto = produtoService.getProdutoById(compra.getIdDoProduto());
		
		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(compra.getIdDoProduto());
		}
		
		Produto produto = optionalProduto.get();
		
		if (produto.getEstoque() < compra.getQuantidade()) {
			return ErroCarrinho.erroEstoqueInsuficiente(produto);
		}
		
		produto.addEstoque((-1)*compra.getQuantidade());
		if(produto.getEstoque() == 0)
			produto.tornaIndisponivel();
		
		produtoService.salvarProdutoCadastrado(produto);
		
		Optional<Compra> comp = carrinhoService.getByIdDoProduto(compra.getIdDoProduto());
		
		if(comp.isPresent()) {
			comp.get().merge(compra);
			compra = comp.get();
		}
		
		carrinhoService.adicionarCompra(compra);
		
		return new ResponseEntity<Compra>(compra, HttpStatus.OK);
	}
}
