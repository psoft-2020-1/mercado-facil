package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufcg.psoft.mercadofacil.model.FormaDePagamento;

@Entity
public class FormaDePagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private FormaDePagamento() {}
	
	public FormaDePagamento(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
