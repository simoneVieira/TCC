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
import org.springframework.stereotype.Repository;
import org.yuequan.jpa.soft.delete.repository.SoftDelete;

/**
 *
 * @author SimoneBarbosa
 */
@SoftDelete 
@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>  {

    public List<Notificacao> findByProximaAlertaLessThanEqualAndStatus(LocalDateTime proximaAlerta,String status);

// @Query("select n from Notificacao n JOIN n.emprestimo e JOIN e.cliente c where c.id =:id")
//    public List<Notificacao> findId(@Param("id")Long id);
    
//    @Query("select e from Notificacao e JOIN e.emprestimo c JOIN c.cliente c where c.id = 2")
//    public List<Notificacao> findClienteById(Long id);
    
//     @Query("select not from Notificacao not where not.id=:id")
//    public List<Notificacao> noti(@Param("id") Long id);
    
    @Query("select n from Notificacao n where n.id=:id")
    public List<Notificacao>findByN(@Param("id")Long id);
    

//    @Query("select e from Notificacao e join e.emprestimo em join em.cliente c where c.id = 1")
//     public List<Notificacao> buscar(int x);
     
     public Notificacao findByStatus(String status);
     
     public Notificacao findByEmprestimo(Emprestimo emprestimo);
    
     
} 
 