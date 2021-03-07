package com.ufcg.psoft.mercadofacil.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long idDoProduto;
	private int quantidade;
	
	public Compra() {
	}
	
	public Compra(long idDoProduto, int quantidade) {
		this.idDoProduto = idDoProduto;
		this.quantidade = quantidade;
	}

	public long getIdDoProduto() {
		return this.idDoProduto;
	}

	public int getQuantidade() {
		return this.quantidade;
	}
	
	public void setQuantidade(int novoValor) {
		this.quantidade = novoValor;
	}
	
	public int hashMap() {
		return Objects.hashCode(idDoProduto);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (this.getIdDoProduto() != other.getIdDoProduto()) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return this.idDoProduto + " x " + this.quantidade;
	}

	public void merge(Compra compra) {
		this.quantidade += compra.getQuantidade();
	}
}
