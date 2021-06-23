package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.model.Cliente;

public interface ClienteService {

	public Optional<Cliente> getClienteById(Long id);
	
	public Optional<Cliente> getClienteByCPF(Long cpf);
	
	public void removerClienteCadastrado(Cliente cliente);

	public void salvarClienteCadastrado(Cliente cliente);

	public List<Cliente> listarClientes();
	
	public Cliente criaCliente(ClienteDTO clienteDTO);
	
	public Cliente atualizaCliente(ClienteDTO clienteDTO, Cliente cliente);

}
