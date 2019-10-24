/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Notificacao;
import com.bettercoding.jfx.service.NotificacaoService;
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

   private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
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
   // @PostConstruct
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
                        listNotificacao = notificacaoService.buscaData(agora);
                        if (!listNotificacao.isEmpty()) {

                            listNotificacao.forEach(notfi -> {

                                System.out.println("id empréstimo: " + notfi.getEmprestimo().getId_Emprestimo());
                                System.out.println("cliente id: " + notfi.getEmprestimo().getCliente().getId());
                                System.out.println("cliente: " + notfi.getEmprestimo().getCliente().getNome());
                                System.out.println("banco: " + notfi.getEmprestimo().getBanco());
                                System.out.println("data: " + notfi.getData());
                                System.out.println("data: " + notfi.getProximaAlerta());
                                
                            });

                        }else{
                            System.out.println("Não há notificações");
                        }

                    }
                });
            }
        }, 0, 10 * 1000 * 1);
    }

}
