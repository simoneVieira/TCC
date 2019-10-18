/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.service.NotificacaoService;
import static java.awt.SystemColor.desktop;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author SimoneBarbosa
 */
@Controller
public class ExecutaTarefa extends TimerTask {
    
    TelaEmprestimoController tec = new TelaEmprestimoController();

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
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                  
                        tec.verificaDataNotificacao();
                        
                        
                        
                        
                        
                       
                    }
                });
            }
        }, 0, 40 * 1000);
    }

}
