package com.br.itau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.itau.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
