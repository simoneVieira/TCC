/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.repository;

import com.bettercoding.jfx.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SimoneBarbosa
 */
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
}
