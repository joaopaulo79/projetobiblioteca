package com.example.projetobiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("S")
public class Administrador extends Usuario {
    @Column(nullable = true)
    private String loginUsuario;

    @Column(nullable = true)
    private String loginSenha;
    
    public Administrador(){
    }
    public Administrador(String loginUsuario, String loginSenha){
        this.loginUsuario = loginUsuario;
        this.loginSenha = loginSenha;
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

    
}