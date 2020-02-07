/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.jfx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
public class AlterarSenhaController implements Initializable {

    @FXML
    private Pane painelSenha;
    @FXML
    private PasswordField fieldNovaSenha;
    @FXML
    private Button butonSalvar;
    @FXML
    private PasswordField codigoUser;
    @FXML
    private TextField senhaExistente;
    @FXML
    private TextField campoNova;
    @FXML
    private CheckBox boxAntiga;
    @FXML
    private CheckBox boxNova;
    @FXML
    private ImageView imagemSalvar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void alteraSenha(ActionEvent event) {
    }

    @FXML
    private void mostrarSenha(ActionEvent event) {
    }

    @FXML
    private void mostrarSenhaNova(ActionEvent event) {
    }
    
}
