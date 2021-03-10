package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCarrinhoItem {

	static final String ESTOQUE_INSUFICIENTE = "Não há unidades suficientes do produto.";

	static final String CARRINHO_VAZIO = "Não há produtos no carrinho.";

	static final String ITEM_NAO_TEM_UNIDADES_SUFICIENTES = "Não há unidades suficientes desse produto no carrinho.";

	static final String ITEM_NAO_CADASTRADO = "Não há unidades desse produto no carrinho.";

	public static ResponseEntity<CustomErrorType> erroEstoqueInsuficiente(long id) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroCarrinhoItem.ESTOQUE_INSUFICIENTE, id)), HttpStatus.CONFLICT);
	}

	public static ResponseEntity<CustomErrorType> carrinhoVazio() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCarrinhoItem.CARRINHO_VAZIO)),
				HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<CustomErrorType> erroItemNaoTemUnidadesSuficientes(long id) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroCarrinhoItem.ITEM_NAO_TEM_UNIDADES_SUFICIENTES, id)),
				HttpStatus.CONFLICT);
	}

	public static ResponseEntity<CustomErrorType> erroItemNaoCadastrado(long id) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroCarrinhoItem.ITEM_NAO_CADASTRADO, id)), HttpStatus.CONFLICT);
	}
}
