package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class ErroCarrinho {
	
	static final String ESTOQUE_INSUFICIENTE = "Não há estoque o suficiente do produto %s"
											 + "do frabricante %s";
	static final String CARRINHO_VAZIO = "O carrinho está vazio";

	public static ResponseEntity<CustomErrorType> erroEstoqueInsuficiente(Produto produto) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinho.ESTOQUE_INSUFICIENTE,
				produto.getNome(), produto.getFabricante())), HttpStatus.NOT_ACCEPTABLE);
	}
	
	public static ResponseEntity<CustomErrorType> erroCarrinhoVazio() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCarrinho.CARRINHO_VAZIO),
				HttpStatus.NO_CONTENT);
	}

}
