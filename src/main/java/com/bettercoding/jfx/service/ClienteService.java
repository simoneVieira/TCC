/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.service;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.model.OrdemPagamento;
import com.bettercoding.jfx.repository.ClienteRepository;
import com.bettercoding.jfx.repository.EmprestimoRepository;
import com.bettercoding.jfx.repository.OrdemPagamentoRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private OrdemPagamentoRepository ordemRepository;
    List<Emprestimo> emprestimo;

    public Cliente salvarCli(Cliente c) {
 
        return clienteRepository.save(c);
    }

    public List<Cliente> clie() {
        return clienteRepository.findAll();
    }
     public List<Cliente> buscaCli(String cpf) {
        return clienteRepository.findByCpfContaining(cpf);
    }
      public List<Cliente> buscaCliNome(String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }
    public Cliente buscaCliente(Long id) {
        return clienteRepository.findById(id).get();
    }
    public List<Cliente> buscaData(Date primeiraData, Date segundaData){
        return clienteRepository.findByDataCadastroBetween(primeiraData, segundaData);
    }
      public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
        Cliente c = new Cliente();
        c.setId(id);
        
         emprestimo = emprestimoRepository.findByCliente(c);
        if(!emprestimo.isEmpty()){
            
            emprestimo.forEach(emp->{
                emprestimoRepository.delete(emp);
            });
       
        Emprestimo emprest= new Emprestimo();
        emprest.setId_Emprestimo(id);
        OrdemPagamento op = ordemRepository.findByEmprestimo(emprest);
         if(op!= null){
          ordemRepository.delete(op);
        }
        
    }
 }
      public List<Cliente> buscaDadosRemovidos(){
          return clienteRepository.FindAllEvenRemoved();
      }
}
  