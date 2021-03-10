package com.ufcg.psoft.mercadofacil.service;

import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;

public interface CarrinhoItemService {

	public ResponseEntity<?> adcionarItemCarrinho(long id, CarrinhoItem carrinhoItem);

	public ResponseEntity<?> removerItemCarrinho(long id, CarrinhoItem carrinhoItem);

	public ResponseEntity<?> listarItensCarrinho();

	public ResponseEntity<?> finalizarCompra();

	public ResponseEntity<?> descartarCarrinho();
}
