package com.example.projetobiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetobiblioteca.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository <Autor, Long> {
    
}
