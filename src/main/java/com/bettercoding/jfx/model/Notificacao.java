/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
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
    private static String Status;

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
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
