package com.example.projetobiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetobiblioteca.model.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository <Locacao, Long> {
    
}
