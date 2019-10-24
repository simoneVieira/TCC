/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.repository;

import com.bettercoding.jfx.model.Notificacao;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SimoneBarbosa
 */
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>  {
  //  public Notificacao findByStatus(String status);
    public List<Notificacao> findByProximaAlertaLessThanEqual(LocalDateTime proximaAlerta);
}