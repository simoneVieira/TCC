/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.model.OrdemPagamento;
import com.pacote.jfx.service.EnviaEmailOpService;
import com.pacote.jfx.service.OrdemPagamentoService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class ExecutaTarefaOp extends TimerTask {

    @Autowired
    private OrdemPagamentoService ordemService;
    @Autowired
    EnviaEmailOpService emailService;

    Alert alert;

    private void completeTask() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        LocalDateTime agora = LocalDateTime.now();
                        //siLocalDateTime agora = LocalDateTime.now(ZoneId.of("Etc/GMT+3"));
                        List<OrdemPagamento> listOrdemPagamento;
                            System.out.println("Agora: "+ agora);
                        listOrdemPagamento = ordemService.buscaDataOrdem(agora, OrdemDePagamentoController.status_Andamento);
                        if (!listOrdemPagamento.isEmpty()) {
                            
                            listOrdemPagamento.forEach(ordemP -> {
                                
                                System.out.println("Ordem data "+ ordemP.getDataSaque());

                                if (alert == null) {
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                }
                                alert.setTitle("ORDEM DE PAGAMENTO");
                                alert.setHeaderText(" "
                                        + "\n" + "COD.NOTIFICAÇÃO: " + ordemP.getId() + "\n"
                                        + "\n" + "BANCO : " + ordemP.getBanco()
                                        + "\n" + "AGENCIA:  " + ordemP.getAgencia()
                                        + "\n" + "CIDADE:  " + ordemP.getCidade()
                                        + "\n" + "NUMERO CONTRATO:  " + ordemP.getEmprestimo().getNumeroContrato()
                                        + "\n" + "NOME DO CLIENTE:  " + ordemP.getEmprestimo().getCliente().getNome()
                                        + "\n" + "CPF DO CLIENTE:  " + ordemP.getEmprestimo().getCliente().getCpf()
                                        + "\n" + "CEL. DO CLIENTE:  " + ordemP.getEmprestimo().getCliente().getTelefone1()
                                        + "\n" + "ORDEM DE PAGAMENTO DISPONIVEL PARA SAQUE!");
                                alert.show();                   

                            });

                        } else {
                            System.out.println("Não há notificações");
                        }
                    }
                });
            }
        }, 0, 10 * 1000*8);
    }

}
