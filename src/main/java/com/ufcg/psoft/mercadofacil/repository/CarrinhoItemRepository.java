package com.ufcg.psoft.mercadofacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.mercadofacil.model.CarrinhoItem;
import com.ufcg.psoft.mercadofacil.model.Produto;

/**
 * Entidade responsável por gerenciar o armazenamento dos itens do carrinho.
 * 
 * @author henri
 *
 */
public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {
	List<CarrinhoItem> findByProduto(Produto produto);

	void deleteByProduto(Produto produto);
}