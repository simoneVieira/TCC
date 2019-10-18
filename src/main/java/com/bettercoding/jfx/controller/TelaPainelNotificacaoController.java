/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Notificacao;
import com.bettercoding.jfx.service.NotificacaoService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
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

    @FXML
    private TextField campoTelefone;

    @FXML
    private TextField campoBanco;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoadiar;

    @FXML
    private TextField campoData;
    @Autowired
    NotificacaoService notificacaoService;

    @FXML
    private ComboBox<String> boxStatus;
    public static final String STATUS_RESOLVER = "ROSOLVIDO";
    public static final String STATUS_ADIAR = "ADIAR";
    public static final String STATUS_CANCELAR = "DESATIVAR";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaStatus();
    }

    public void carregaStatus() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll(TelaPainelNotificacaoController.STATUS_ADIAR, TelaPainelNotificacaoController.STATUS_CANCELAR, TelaPainelNotificacaoController.STATUS_RESOLVER);
        boxStatus.setItems(itens);
    }
    @FXML
    public void salvarNotificacao() {
        if (boxStatus.getSelectionModel().getSelectedItem().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA");
            alert.setHeaderText("FAVOR PREENCHER O STATUS DA NOTIFICAÇÂO");
            alert.show();

        }else{
            Notificacao notificacao = new Notificacao();
            notificacao.setStatus("" + boxStatus.getSelectionModel().getSelectedItem());
            Notificacao not = notificacaoService.salvaNotificacao(notificacao);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA");
            alert.setHeaderText("SALVO COM SUCESSO! ");
            alert.show();
        }
    }
}
