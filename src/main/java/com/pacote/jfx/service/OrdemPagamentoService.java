/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.service;

import com.pacote.jfx.model.OrdemPagamento;
import com.pacote.jfx.repository.OrdemPagamentoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class OrdemPagamentoService {
   @Autowired
    private OrdemPagamentoRepository ordemRepository;
   
     public OrdemPagamento salvaOrdem(OrdemPagamento ordemPagamento) {
        return ordemRepository.save(ordemPagamento);
    }
      public List<OrdemPagamento> buscaDataOrdem(LocalDateTime dataSaque,String status) {
        return ordemRepository.findByDataSaqueBeforeAndStatus(dataSaque,status);
    }
       public List<OrdemPagamento> buscaOrdemPagamento() {
        return ordemRepository.findAll();
    }
     public List<OrdemPagamento> buscarPorId(Long id) {
      return ordemRepository.findByIdOp(id);
    }
       public void excluirOrdem(Long id) {
        ordemRepository.deleteById(id);
    }
}
