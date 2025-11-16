
package com.example.projetobiblioteca.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String titulo;
    private String editora;
    private int anoPublicacao;
    private int numeroEdicao;
    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "secao_id")
    private Secao secao;
    
    @ManyToMany
    @JoinTable(
        name="autor_livro",
        joinColumns = @JoinColumn(name ="livro_id"),
        inverseJoinColumns = @JoinColumn(name="autor_id")
    ) 
    private List<Autor> autores = new ArrayList<Autor>();

    @ManyToMany
    @JoinTable(
        name="categoria_livro",
        joinColumns = @JoinColumn(name ="livro_id"),
        inverseJoinColumns = @JoinColumn(name="categoria_id")
    ) 
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @ManyToMany(mappedBy = "livros")
    private List<Locacao> locacoes = new ArrayList<Locacao>();

    public Livro() {
    }

    public Livro(String isbn, String titulo, String editora, int anoPublicacao, int numeroEdicao, boolean status) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numeroEdicao = numeroEdicao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Relações
    // Seção
    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    // Autor
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;

    }

    public void addAutor(Autor autor) {
        this.autores.add(autor);
        if (!autor.getListaLivros().contains(this)) {
            autor.getListaLivros().add(this);
        }
    }
    
    // Categorias
    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
        if (!categoria.getListaLivros().contains(this)) {
            categoria.getListaLivros().add(this);
        }
    }
    
    // Locacoes
    public List<Locacao> getLocacoes() {
        return locacoes;
    }
    
    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

}