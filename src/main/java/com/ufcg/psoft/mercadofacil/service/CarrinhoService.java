package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;

import java.util.List;

public interface CarrinhoService {
    void salvarCarrinho(List<Carrinho> carrinho);

    List<Carrinho> consultarCarrinho();

    void deletarCarrinho();
}
