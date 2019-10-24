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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SimoneBarbosa
 */
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    public List<Emprestimo> findByCliente(Cliente c);
     public List<Emprestimo> findByNumeroContrato(int numeroContrato);
    
    @Query("select e from Emprestimo e  JOIN e.cliente c where c.nome like concat(:nome,'%') OR c.cpf=:cpf")
    public List<Emprestimo> findClienteByNome(@Param("nome") String nome, @Param("cpf") String cpf);
    
    @Query("select b.banco, sum(b.valorComissao)from Emprestimo b group by b.banco")
    public List<Emprestimo> findBancoAndValorComissao(String banco, float valorComissao);
}
