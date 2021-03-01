package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProdutoApiController {

	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ResponseEntity<?> listarProdutos() {
		
		List<Produto> produtos = produtoService.listarProdutos();
		
		if (produtos.isEmpty()) {
			return ErroProduto.erroSemProdutosCadastrados();
		}
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produto/", method = RequestMethod.POST)
	public ResponseEntity<?> criarProduto(@RequestBody Produto produto, UriComponentsBuilder ucBuilder) {

		List<Produto> produtos = produtoService.getProdutoByCodigoBarra(produto.getCodigoBarra());
		
		if (!produtos.isEmpty()) {
			return ErroProduto.erroProdutoJaCadastrado(produto);
		}

		produto.tornaDisponivel();
		produtoService.salvarProdutoCadastrado(produto);

		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}
	

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) {

		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);
	
		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
		
		return new ResponseEntity<Produto>(optionalProduto.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarProduto(@PathVariable("id") long id, @RequestBody Produto produto) {

		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);
		
		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
		
		Produto currentProduto = optionalProduto.get();
		
		currentProduto.setNome(produto.getNome());
		currentProduto.setPreco(produto.getPreco());
		currentProduto.setCodigoBarra(produto.getCodigoBarra());
		currentProduto.mudaFabricante(produto.getFabricante());
		currentProduto.mudaCategoria(produto.getCategoria());

		produtoService.salvarProdutoCadastrado(currentProduto);
		
		return new ResponseEntity<Produto>(currentProduto, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerProduto(@PathVariable("id") long id) {

		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);
		
		if (!optionalProduto.isPresent()) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
				
		produtoService.removerProdutoCadastrado(optionalProduto.get());

		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}
}