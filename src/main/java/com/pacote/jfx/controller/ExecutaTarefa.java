/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.MyApp;
import com.pacote.jfx.model.Notificacao;
import com.pacote.jfx.service.NotificacaoService;
import static java.awt.SystemColor.desktop;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class ExecutaTarefa extends TimerTask {

    @Autowired
    private NotificacaoService notificacaoService;
    Notificacao notifica;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
      Alert alert;
    

    private void completeTask() {
        try {
           
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pegaDataAtual() {
        Date dataAtual = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = formatar.format(dataAtual);
        System.out.println("data e hora atual atual" + "    " + dataFormatada);
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
                        List<Notificacao> listNotificacao;
                        listNotificacao = notificacaoService.buscaData(agora,PainelNotificacaoController.status_Andamento);
                        if (!listNotificacao.isEmpty()) {
                            listNotificacao.forEach(notfi -> {
                                if (alert == null) {
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                }
                             
                                alert.setTitle("RENOVAÇÃO DE CONTRATO");
                                alert.setHeaderText(" "
                                        + "\n" + "COD.NOTIFICAÇÃO: " + notfi.getId() + "\n"
                                        + "\n" + "NOME: " + notfi.getEmprestimo().getCliente().getNome()
                                        + "\n" + "CPF:  " + notfi.getEmprestimo().getCliente().getCpf()
                                        + "\n" + "TELEFONE:  " + notfi.getEmprestimo().getCliente().getTelefone1()
                                        + "\n" + "BANCO:  " + notfi.getEmprestimo().getBanco()
                                        + "\n" + "NOTIFICAÇÂO DE:   "+ notfi.getTipoNotificacao());
                               alert.show();                     
                            });

                        } else {
                            System.out.println("Não há notificações");
                        }

                    }
                });
            }
        }, 0, 10 * 1000 * 5);
    }

}
