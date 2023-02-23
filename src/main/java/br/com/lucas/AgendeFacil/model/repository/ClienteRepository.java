package br.com.lucas.AgendeFacil.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.AgendeFacil.model.entitiy.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
