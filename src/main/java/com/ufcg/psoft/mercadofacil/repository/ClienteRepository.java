package com.ufcg.psoft.mercadofacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufcg.psoft.mercadofacil.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByCPF(long cpf);
}
