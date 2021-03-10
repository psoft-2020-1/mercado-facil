package com.ufcg.psoft.mercadofacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface LoteRepository extends JpaRepository<Lote, Long>{
	List<Lote> findByProduto(Produto produto);
}