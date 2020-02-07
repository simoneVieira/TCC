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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane labelNome;
    @FXML
    private Button botaoEmprestimo;
    @FXML
    private Button botaoRelatorio;
    @FXML
    private ImageView viewEmprestimo;
    @FXML
    private ImageView imgRelatorio;
    @FXML
    private Button botaoCliente;
    @FXML
    private ImageView imgCliente;
    @FXML
    private Button botaoOp;
    @FXML
    private ImageView imagemOp;
    @FXML
    private Button idCadastro;
    @FXML
    private Label labelRedefineSenha;
    @FXML
    private Label voltarLogin;
    @FXML
    private Label painelNotificacao;
    @FXML
    private ImageView viewUsuario;
    @FXML
    private Button btnLixeira;
    @FXML
    private ImageView imgLixeira;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void botaoEmprestimo(ActionEvent event) {
    }

    @FXML
    private void geraPdf(ActionEvent event) {
    }

    @FXML
    private void botaoCliente(ActionEvent event) {
    }

    @FXML
    private void chamaTelaOp(ActionEvent event) {
    }

    @FXML
    private void idCadastro(ActionEvent event) {
    }

    @FXML
    private void abreTelaNovaSenha(MouseEvent event) {
    }

    @FXML
    private void abreTelaNovaSenha(KeyEvent event) {
    }

    @FXML
    private void reiniciar(MouseEvent event) {
    }

    @FXML
    private void chamaTelaPainel(MouseEvent event) {
    }

    @FXML
    private void TelaDadosRemovidos(ActionEvent event) {
    }
    
}
