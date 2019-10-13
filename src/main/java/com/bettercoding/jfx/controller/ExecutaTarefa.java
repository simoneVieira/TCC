/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
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

/**
 *
 * @author SimoneBarbosa
 */
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
                        Stage stage = new Stage();
                        Parent root = null;
                        try {
                            FXMLLoader fxml = new FXMLLoader();
                            fxml.setControllerFactory(MyApp.springContext::getBean);
                            fxml.setLocation(getClass().getResource("/fxml/TelaNotificacao.fxml"));
                            root = fxml.load();
                        } catch (IOException ex) {
                            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        stage.setResizable(false);
                        stage.setTitle("Tela de Messagem!");
                        stage.setResizable(false);
                    }
                });
            }
        }, 0, 40 * 1000);
    }

}
