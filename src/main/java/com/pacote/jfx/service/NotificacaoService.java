/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.service;

import com.pacote.jfx.model.Cliente;
import com.pacote.jfx.model.Emprestimo;
import com.pacote.jfx.model.Notificacao;
import com.pacote.jfx.repository.NotificacaoRepository;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;
    
    public List<Notificacao> buscaData(LocalDateTime proximaAlerta,String status) {
        return notificacaoRepository.findByProximaAlertaLessThanEqualAndStatus(proximaAlerta,status);
    }

    public Notificacao salvaNotificacao(Notificacao notifica) {
        return notificacaoRepository.save(notifica);
    }
   
//    
//      public List<Notificacao> notificacao(int x) {
//        return notificacaoRepository.buscar(x);
//      }
        public List<Notificacao> notifica() {
        return notificacaoRepository.findAll();
    }
      public Notificacao buscaStatus(String status) {
        return notificacaoRepository.findByStatus(status);
    }  
      public List<Notificacao> buscarPorId(Long id) {
      return notificacaoRepository.findByN(id);
    }
}
