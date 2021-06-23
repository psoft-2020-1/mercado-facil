package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<Cliente> getClienteById(Long id) {
		return clienteRepository.findById(id);
	}
	
	public Optional<Cliente> getClienteByCPF(Long cpf) {
		return clienteRepository.findByCPF(cpf);
	}
	
	public void removerClienteCadastrado(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	public void salvarClienteCadastrado(Cliente cliente) {
		clienteRepository.save(cliente);		
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public Cliente criaCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente(clienteDTO.getCPF(), clienteDTO.getNome(), 
				clienteDTO.getIdade(), clienteDTO.getEndereco());
		
		return cliente;
	}

	public Cliente atualizaCliente(ClienteDTO clienteDTO, Cliente cliente) {
		cliente.setIdade(clienteDTO.getIdade());
		cliente.setEndereco(clienteDTO.getEndereco());
		
		return cliente;
	}
}
