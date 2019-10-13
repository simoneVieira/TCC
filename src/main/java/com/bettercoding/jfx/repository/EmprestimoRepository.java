/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.repository;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SimoneBarbosa
 */
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
   public List<Emprestimo> findByCliente(Cliente c );
     public Emprestimo findByDataNotificacaoAndHoraNotificacao( Date dataNotificacao,  Date horaNotificacao);
   
//   @Query(value = "SELECT  banco, SUM(valor_comissao)AS total FROM Emprestimo GROUP BY banco")
//   public List<Emprestimo> buscaESomaPorBanco();
}
