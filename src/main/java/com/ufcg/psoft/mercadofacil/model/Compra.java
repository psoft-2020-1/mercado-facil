package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Calendar data;
	
	@OneToMany
	private List<Produto> produtos;
	
	private BigDecimal precoTotal;
	
	@OneToOne
	private FormaDePagamento formaDePagamento;
	
	private Compra() {}
	
	public Compra(Calendar data, List<Produto> produtos, BigDecimal precoTotal, FormaDePagamento formaDePagamento) {
		this.data = data;
		this.produtos = produtos;
		this.precoTotal = precoTotal;
		this.formaDePagamento = formaDePagamento;
	}

	public Long getId() {
		return id;
	}

	public Calendar getData() {
		return data;
	}

	public List<Produto> getItens() {
		return produtos;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
}
