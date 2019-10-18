/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author SimoneBarbosa
 */
@Entity
public class Notificacao {
    private Long id;
    private Date data;
    private Date proximaAlerta;
    private static String Status ;

    public Date getProximaAlerta() {
        return proximaAlerta;
    }

    public void setProximaAlerta(Date proximaAlerta) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    
    
}
