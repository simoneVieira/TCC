/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.model;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.yuequan.jpa.soft.delete.repository.SoftDelete;

/**
 *
 * @author SimoneBarbosa
 */
@Entity
public class OrdemPagamento {

    private Long id;
    private String banco;
    private String agencia;
    private String cidade;
    private String estado;
    private LocalDateTime dataSaque;
    private Emprestimo emprestimo;
    private String status;
    private Date removedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public LocalDateTime getDataSaque() {
        return dataSaque;
    }

    public void setDataSaque(LocalDateTime dataSaque) {
        this.dataSaque = dataSaque;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    @OneToOne
    @JoinColumn(name = "id_Emprestimo",nullable = false)
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "removed_at")
    public Date getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(Date removedAt) {
        this.removedAt = removedAt;
    }
    



}
