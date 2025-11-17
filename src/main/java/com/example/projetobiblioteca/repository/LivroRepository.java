package com.example.projetobiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetobiblioteca.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository <Livro, Long> {
    List<Livro> findByAutorId(Long autorId);
    List<Livro> findBySecaoId(Long secaoId);
    List<Livro> findByCategoriaId(Long categoriaId);
}
