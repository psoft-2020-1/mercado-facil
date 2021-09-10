package com.ufcg.psoft.mercadofacil.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;

public class ErroCliente {
	
	static final String CLIENTE_NAO_CASTRADO = "Cliente com id %s não está cadastrado";
	
	static final String CLIENTES_NAO_CASTRADOS = "Não há clienters cadastrados";

	static final String NAO_FOI_POSSIVEL_ATUALIZAR = "Não foi possível mudar atualizar a situação do cliente %s "
			+ "nome %s";

	static final String CLIENTE_JA_CADASTRADO = "O cliente %s nome %s já esta cadastrado";

	public static ResponseEntity<CustomErrorType> erroClienteNaoEnconrtrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCliente.CLIENTE_NAO_CASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroSemClientesCadastrados() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCliente.CLIENTES_NAO_CASTRADOS),
				HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<?> erroClienteJaCadastrado(ClienteDTO clienteDTO) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCliente.CLIENTE_JA_CADASTRADO,
				clienteDTO.getCPF(), clienteDTO.getNome())), HttpStatus.CONFLICT);
	}
}
