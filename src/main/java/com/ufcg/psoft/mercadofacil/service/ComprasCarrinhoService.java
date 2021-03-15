package com.ufcg.psoft.mercadofacil.service;

import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.mercadofacil.DTO.FormaDePagamentoDTO;
import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;

public interface ComprasCarrinhoService {

	public ResponseEntity<?> adcionarItemCarrinho(long id, CarrinhoItem carrinhoItem);

	public ResponseEntity<?> removerItemCarrinho(long id, CarrinhoItem carrinhoItem);

	public ResponseEntity<?> listarItensCarrinho();

	public ResponseEntity<?> finalizarCompra(long idFormaDePagamento);

	public ResponseEntity<?> descartarCarrinho();
	
	public ResponseEntity<?> listarCompras();
	
	public ResponseEntity<?> adicionarFormaDePagamento(FormaDePagamentoDTO formaDePagamentoDTO);
	
	public ResponseEntity<?> listarFormasDePagamento();

	public ResponseEntity<?> consultarCompra(long idCompra);
}
