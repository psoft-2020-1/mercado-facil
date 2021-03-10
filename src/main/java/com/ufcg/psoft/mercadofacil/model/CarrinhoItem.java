package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entidade que representa um item de um carrinho de compras. Contém um produto
 * e uma quantidade.
 * 
 * @author henri
 *
 */
@Entity
public class CarrinhoItem {
	@Id
	@GeneratedValue
	private long id;

	@OneToOne
	private Produto produto;

	private int numeroDeItens;

	public CarrinhoItem() {
		this.id = 0;
	}

	public CarrinhoItem(Produto produto, int numeroDeItens) {
		super();
		this.produto = produto;
		this.numeroDeItens = numeroDeItens;
	}

	/**
	 * Adiciona unidades do produto ao item.
	 * 
	 * @param qtd A quantidade de produtos a ser adicionada.
	 */
	public void adicionaItens(int qtd) {
		this.numeroDeItens += qtd;
	}

	/**
	 * Remove unidades do produto do item.
	 * 
	 * @param qtd A quantidade de produtos a ser removida.
	 */
	public void removeItens(int qtd) {
		this.numeroDeItens -= qtd;
	}

	/**
	 * Calcula o preco final do item.
	 * 
	 * @return O preço final do item.
	 */
	public BigDecimal calculaPreco() {
		return produto.getPreco().multiply(new BigDecimal(numeroDeItens));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getNumeroDeItens() {
		return numeroDeItens;
	}

	public void setNumeroDeItens(int numeroDeItens) {
		this.numeroDeItens = numeroDeItens;
	}
}
