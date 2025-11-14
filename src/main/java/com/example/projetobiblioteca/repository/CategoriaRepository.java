package com.example.projetobiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetobiblioteca.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
    
}
