package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Compra;

public interface CarrinhoService {

	void adicionarCompra(Compra compra);

	Optional<Compra> getByIdDoProduto(long idDoProduto);

	List<Compra> listarCompras();

	void limparCompras();

}
