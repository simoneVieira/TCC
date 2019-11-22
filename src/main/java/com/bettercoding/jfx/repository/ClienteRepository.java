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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yuequan.jpa.soft.delete.repository.SoftDelete;
@SoftDelete 
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByCpfContaining(String cpf);
    public List<Cliente>findByNomeContaining(String nome);
    public List<Cliente>findByDataCadastroBetween(Date primeriraData, Date segundaData);
   @Query("select c from Cliente c where c.removedAt != NULL")
    public  List<Cliente> FindAllEvenRemoved();


   
   
}
