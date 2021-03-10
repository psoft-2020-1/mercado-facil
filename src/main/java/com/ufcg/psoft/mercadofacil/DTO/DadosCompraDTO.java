package com.ufcg.psoft.mercadofacil.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;

/**
 * Entidade usada para transportar dados. Mais especificamente, para ser enviada
 * ao cliente ao finalizar uma compra.
 * 
 * @author henri
 *
 */
public class DadosCompraDTO {

	private List<CarrinhoItem> itens;

	private BigDecimal precoTotal;

	public DadosCompraDTO(List<CarrinhoItem> itens, BigDecimal precoTotal) {
		this.itens = itens;
		this.precoTotal = precoTotal;
	}

	public List<CarrinhoItem> getItens() {
		return itens;
	}

	public void setItens(List<CarrinhoItem> itens) {
		this.itens = itens;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
}