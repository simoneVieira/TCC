/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Usuario;
import com.bettercoding.jfx.service.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author SimoneBarbosa
 */
@Controller
public class AlterarSenhaController implements Initializable {

    @FXML
    private Pane painelSenha;

    @FXML
    private PasswordField fieldNovaSenha;
    @FXML
    private TextField codigoUser;
    @FXML
    private Button butonSalvar;
    @Autowired
    UsuarioService usuarioService;
    ExecutaTarefa ect = new ExecutaTarefa();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void alteraSenha() {
//        //Usuario usuario = usuarioService.buscaSenhaUsuario(fieldNovaSenha.getText());
//
//        if (usuario != null) {
//
//            usuario.setSenha(fieldNovaSenha.getText());
//            usuario = usuarioService.salvaUsuario(usuario);
//
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("AVISO");
//            alert.setHeaderText("não deu certo");
//            alert.show();
//            ect.pegaDataAtual() ;
//            return;
//        }
//
//       
    }

   
}
