package com.ufcg.psoft.mercadofacil.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Cliente;


/**
 * Serviço que implementa a lógica de autenticação através dos dados enviados pelo client
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteService clienteService;

	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		
		Optional<Cliente> opCliente = clienteService.getClienteByNome(nome);

		if (opCliente.isEmpty()) {
			throw new UsernameNotFoundException("User not found with name: " + nome);
		}
		
		Cliente cliente = opCliente.get();
		
		if (cliente.getNome().equals(nome)) {
			return new User(cliente.getNome(), cliente.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with name: " + nome);
		}
	}
}