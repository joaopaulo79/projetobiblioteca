package com.example.projetobiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetobiblioteca.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository <Livro, Long> {
}
