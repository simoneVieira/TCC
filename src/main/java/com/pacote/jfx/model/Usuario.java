/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.model;

import java.io.Serializable;
import static java.lang.Integer.max;
import static java.lang.Long.max;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.Length;



/**
 *
 * @author SimoneBarbosa
 */
@Entity
//
//@SQLDelete(sql = "update Usuario set ativo = false where id = ?")
//@Where(clause = "ativo = true")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("U")
public class Usuario implements Serializable {

    private Long id;
    private String usuario;
    private String senha;
    private String email;
    private String role;
    private Date removedAt;
   // private boolean ativo = true;

//    public boolean isAtivo() {
//        return ativo;
//    }
//
//    public void setAtivo(boolean ativo) {
//        this.ativo = ativo;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "usuario", unique = true)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @Column(name = "senha", unique = true)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   
    @NotEmpty
   
    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return role;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.role = tipoUsuario;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public Date getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(Date removedAt) {
        this.removedAt = removedAt;
    }

}
