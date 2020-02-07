/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.repository;

import com.pacote.jfx.model.Emprestimo;
import com.pacote.jfx.model.OrdemPagamento;
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
public interface OrdemPagamentoRepository extends JpaRepository<OrdemPagamento, Long> {
    public List<OrdemPagamento> findByDataSaqueBeforeAndStatus (LocalDateTime dataSaque,String status);
     @Query("select op from OrdemPagamento op where op.id=:id")
    public List<OrdemPagamento> findByIdOp(@Param("id") Long id);
     public OrdemPagamento findByEmprestimo(Emprestimo emprestimo);
}
