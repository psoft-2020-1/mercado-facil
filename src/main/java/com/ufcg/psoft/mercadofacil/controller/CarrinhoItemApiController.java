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

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.CarrinhoItemService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoItemApiController {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	CarrinhoItemService carrinhoItemService;

	@RequestMapping(value = "/carrinho/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> adcionarItemCarrinho(@PathVariable("id") long id, @RequestBody int numItens) {
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);

		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}

		Produto produto = optionalProduto.get();
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, numItens);

		return carrinhoItemService.adcionarItemCarrinho(id, carrinhoItem);
	}

	@RequestMapping(value = "/carrinho/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerItemCarrinho(@PathVariable("id") long id, @RequestBody int numItens) {
		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);

		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}

		Produto produto = optionalProduto.get();
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, numItens);

		return carrinhoItemService.removerItemCarrinho(id, carrinhoItem);
	}

	@RequestMapping(value = "/carrinho", method = RequestMethod.GET)
	public ResponseEntity<?> listarItensCarrinho() {
		return carrinhoItemService.listarItensCarrinho();
	}

	// verbo completamente inadequado
	@RequestMapping(value = "/carrinho", method = RequestMethod.PUT)
	public ResponseEntity<?> finalizarCompra() {
		return carrinhoItemService.finalizarCompra();
	}

	@RequestMapping(value = "/carrinho", method = RequestMethod.DELETE)
	public ResponseEntity<?> descartarCarrinho() {
		return carrinhoItemService.descartarCarrinho();
	}
}