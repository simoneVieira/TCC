/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.repository;

import com.pacote.jfx.model.Adminstrador;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SimoneBarbosa
 */
public interface AdministradorRepository  extends JpaRepository<Adminstrador, Long> {
    
}