/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.repository;

import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.model.Notificacao;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author SimoneBarbosa
 */
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>  {

    public List<Notificacao> findByProximaAlertaLessThanEqual(LocalDateTime proximaAlerta);

// @Query("select n from Notificacao n JOIN n.emprestimo e JOIN e.cliente c where c.id =:id")
//    public List<Notificacao> findId(@Param("id")Long id);
    
    @Query("select e from Notificacao e JOIN e.emprestimo c JOIN c.cliente c where c.id = 2")
    public List<Notificacao> findClienteById(Long id);
//    @Param("id") Long id
 
    
    // buscar pelo o id do cliente
    @Query("select e from Notificacao e join e.emprestimo em join em.cliente c where c.id = 1")
     public List<Notificacao> buscar(int x);
} 
 