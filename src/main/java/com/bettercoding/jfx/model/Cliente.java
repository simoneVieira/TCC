/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
    
    private Long id;
    private String nome;
    private String usuario;
    private Long senha;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(Long senha) {
        this.senha = senha;
    }
    

    public String getUsuario() {
        return usuario;
    }

    public Long getSenha() {
        return senha;
    }
  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
}

