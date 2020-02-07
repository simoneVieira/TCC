/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 *
 * @author SimoneBarbosa
 */
@Entity
public class Notificacao {
    private Long id;
    private LocalDateTime data;
    private LocalDateTime proximaAlerta;
    private Emprestimo emprestimo;
    private String status;
    private String tipoNotificacao;
     private Date removedAt;

    public LocalDateTime getData() {
        return data;
    }

    
    @OneToOne
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getProximaAlerta() {
        return proximaAlerta;
    }

    public void setProximaAlerta(LocalDateTime proximaAlerta) {
        this.proximaAlerta = proximaAlerta;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public Date getRemovedAt() {
        return removedAt;
    }
    @Column(name = "removed_at")
    public void setRemovedAt(Date removedAt) {
        this.removedAt = removedAt;
    }

   

}
