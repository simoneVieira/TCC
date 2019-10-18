/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.service;

import com.bettercoding.jfx.model.Notificacao;
import com.bettercoding.jfx.repository.NotificacaoRepository;
import java.util.Date;
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
    
    
//    public Notificacao buscaStatus(String status) {
//        return notificacaoRepository.findByStatus(status);
//    }
    public Notificacao buscaData(Date data) {
        return notificacaoRepository.findByData(data);
    }
    public Notificacao salvaNotificacao(Notificacao notifica){
       return notificacaoRepository.save(notifica);
    }
}
