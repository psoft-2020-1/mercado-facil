package com.ufcg.psoft.mercadofacil.controller;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.CarrinhoForm;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoApiController {

    @Autowired
    CarrinhoService carrinhoService;

    @RequestMapping(value = "/carrinho", method = RequestMethod.POST)
    public ResponseEntity<?> criarCarrinho(@RequestBody CarrinhoForm carrinhoForm) {

        List<Carrinho> carrinho = carrinhoService.consultarCarrinho();
        if (!carrinho.isEmpty()) {
            return new ResponseEntity<>("Carrinho já existe.", HttpStatus.BAD_REQUEST);
        }
        carrinhoService.salvarCarrinho(carrinhoForm.getCarrinho());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/carrinho", method = RequestMethod.GET)
    public ResponseEntity<?> listarCarrinho() {
        List<Carrinho> carrinho = carrinhoService.consultarCarrinho();

        if (carrinho.isEmpty()) {
            return new ResponseEntity<>("Carrinho não existe.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(carrinho, HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarCarrinho(@RequestBody CarrinhoForm carrinhoForm){
        List<Carrinho> carrinho = carrinhoService.consultarCarrinho();

        if (carrinho.isEmpty()) {
            return new ResponseEntity<>("Carrinho não existe.", HttpStatus.BAD_REQUEST);
        }

        carrinhoService.deletarCarrinho();
        carrinhoService.salvarCarrinho(carrinhoForm.getCarrinho());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarCarrinho(){
        List<Carrinho> carrinho = carrinhoService.consultarCarrinho();

        if (carrinho.isEmpty()) {
            return new ResponseEntity<>("Carrinho não existe.", HttpStatus.BAD_REQUEST);
        }

        carrinhoService.deletarCarrinho();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
