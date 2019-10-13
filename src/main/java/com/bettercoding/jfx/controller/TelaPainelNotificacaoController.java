/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaPainelNotificacaoController implements Initializable {

    @FXML
    private TableColumn<?, ?> nomeCliente;

    @FXML
    private TableColumn<?, ?> cpfCliente;

    @FXML
    private TableColumn<?, ?> telefoneCliente;

    @FXML
    private TableColumn<?, ?> bancoCliente;

    @FXML
    private TableColumn<?, ?> statusCliente;

    @FXML
    private TableColumn<?, ?> dataCliente;

    @FXML
    private Button botaoAnexarArquivo;

    @FXML
    private TextField fieldCpf;

    @FXML
    private Button botaoPesquisar;
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCodigo;

    @FXML
    private Button salvar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
