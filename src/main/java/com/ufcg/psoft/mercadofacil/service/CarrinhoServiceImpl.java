package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public void salvarCarrinho(List<Carrinho> carrinho) {
        carrinhoRepository.saveAll(carrinho);
    }

    @Override
    public List<Carrinho> consultarCarrinho() {
        return carrinhoRepository.findAll();
    }

    @Override
    public void deletarCarrinho() {
        carrinhoRepository.deleteAll();
    }
}
