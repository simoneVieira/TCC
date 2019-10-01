/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;


/**
 *
 * @author SimoneBarbosa
 * 
 */
@Controller
public class TelaEmailController implements Initializable {
      @FXML
    private AnchorPane pEmail;

    @FXML
    private Pane painelEmail;

    @FXML
    private TextField fieldEmail;

    @FXML
    private Label labelEmail;

    @FXML
    private Button buttonEnviar;

    @FXML
    private Button buttonCancelar;
    TelaUsuarioController usuarioC = new TelaUsuarioController();
    Usuario u = new Usuario();
    
    public void initialize(URL location, ResourceBundle resources) {
        
     
    }
  public void enviarEmail(){
      String enviarEmail = "emaildetestejavatcc@gmail.com";
      String minhaSenha = "simone123@";
      
   
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(enviarEmail, minhaSenha));
        email.setSSLOnConnect(true);
        try
        {
          email.setFrom(enviarEmail);
          email.setSubject("Sua nova senha");
          email.setMsg(" Senha provisória para acessar o sistema " );
          email.addTo(enviarEmail);
          email.send();
          System.out.println("email enviado com sucesso");
        }catch ( Exception e){
        e.printStackTrace();
        }
    }
      
  }
    
    
    
    


