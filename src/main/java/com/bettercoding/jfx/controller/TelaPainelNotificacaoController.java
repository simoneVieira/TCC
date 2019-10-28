/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.model.Notificacao;
import com.bettercoding.jfx.service.NotificacaoService;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Notificacao, Emprestimo> banco;

    @FXML
    private TableColumn<Notificacao, Emprestimo> tipoEmprestimo;

    @FXML
    private TableColumn<Notificacao, Emprestimo> codigo;

    @FXML
    private TableColumn<Notificacao, String> status;
    @FXML

    private TableView<Notificacao> tabela;
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

       
        initTable();

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

        } else {
            Notificacao notificacao = new Notificacao();
            notificacao.setStatus("" + boxStatus.getSelectionModel().getSelectedItem());
            Notificacao not = notificacaoService.salvaNotificacao(notificacao);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA");
            alert.setHeaderText("SALVO COM SUCESSO! ");
            alert.show();
        }

    }

    public void initTable() {
        codigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
        tipoEmprestimo.setCellValueFactory(new PropertyValueFactory("TipoEmprestimo"));
        banco.setCellValueFactory(new PropertyValueFactory("banco"));
        status.setCellValueFactory(new PropertyValueFactory("Status"));
    }
    public ObservableList<Notificacao> atualizaTabela = FXCollections.observableArrayList();

    public void listarNotificacao() {
//        if (!atualizaTabela.isEmpty()) {
//            atualizaTabela.clear();
//
//        }
//    //    List<Notificacao> listaNotificacaoPorId = notificacaoService.buscaPorId(Long.parseLong(fieldCpf.getText()));
//
//        if (listaNotificacaoPorId.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("AVISO");
//            alert.setHeaderText("Não foi possível encontrar um Empréstimo para esse Cliente!");
//            alert.show();
//            return;
//        }

        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Notificacao not : notificacaoService.notificacao()) {
            atualizaTabela.add(not);

        }

      
        banco.setCellValueFactory((TableColumn.CellDataFeatures<Notificacao, Emprestimo> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getBanco()));
        tipoEmprestimo.setCellValueFactory((TableColumn.CellDataFeatures<Notificacao, Emprestimo> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getFormaContrato()));
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tabela.setItems(atualizaTabela);

    }

}
