package com.example.projetobiblioteca.dto;

import java.sql.Date;

import com.example.projetobiblioteca.model.Administrador;
import com.example.projetobiblioteca.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private int cpf;
    private int telefone;
    private String email;
    private Date dtNascimento;

    private String tipo;    
    
    private String loginUsuario;
    private String loginSenha;

    public UsuarioDTO(){
    }

    // Construtor que constroí a partir de uma entidade existente
    public UsuarioDTO(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        cpf = usuario.getCpf();
        telefone = usuario.getTelefone();
        email = usuario.getEmail();
        dtNascimento = usuario.getDtNascimento();
        tipo = "usuario";

        if (usuario instanceof Administrador adm) {
            tipo = "administrador";
            loginUsuario = adm.getLoginUsuario();
            loginSenha = adm.getLoginSenha();
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getLoginSenha() {
        return loginSenha;
    }

    public void setLoginSenha(String loginSenha) {
        this.loginSenha = loginSenha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario toUsuario() {
        Usuario usuario = new Usuario(nome, cpf, telefone, email, dtNascimento);
        usuario.setId(id);
        return usuario;
    }

    public Administrador toAdministrador() {
        Administrador administrador = new Administrador(loginUsuario, loginSenha);
        administrador.setId(id);
        administrador.setNome(nome);
        administrador.setCpf(cpf);
        administrador.setTelefone(telefone);
        administrador.setEmail(email);
        administrador.setDtNascimento(dtNascimento);
        return administrador;
    }
}
