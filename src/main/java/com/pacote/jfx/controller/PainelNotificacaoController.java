/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.model.Cliente;
import com.pacote.jfx.model.Emprestimo;
import com.pacote.jfx.model.Notificacao;
import com.pacote.jfx.service.NotificacaoService;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import util.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class PainelNotificacaoController implements Initializable {

    @FXML
    private TableColumn<Notificacao, Long> colunaCodigo;
    @FXML
    private TableColumn<Notificacao, Cliente> colunaCliente;
    @FXML
    private TableColumn<Notificacao, String> colunaStatus;
    @FXML
    private TableColumn<Notificacao, Emprestimo> idBanco;
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
    private TextField fieldNotificacao;
    @FXML
    private ComboBox<String> comNote;
    @FXML
    private TextField campoTelefone;

    @FXML
    private TextField campoBanco;

    @FXML
    private TextField campoCpf;
    @FXML
    private TextField DATACADASTRO;

    @FXML
    private TextField campoadiar;

    @FXML
    private TextField campoData;
    @Autowired
    NotificacaoService notificacaoService;
    Cliente c = new Cliente();
    Emprestimo emp = new Emprestimo();
    Notificacao not;
    public static final String status_Andamento = "Andamento";
    public static final String status_Resolvido = "Resolvido";
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    @FXML
    private ComboBox<String> boxStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initTable();
        carregaStatus();
        listarNotificacao();
        comboNotificacao();
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (Notificacao, oldValue, newValue) -> selecionarItemTableViewNotificacao(newValue));
    }

    public void carregaStatus() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll(PainelNotificacaoController.status_Andamento, PainelNotificacaoController.status_Resolvido);
        boxStatus.setItems(itens);
    }
     public void comboNotificacao() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Refinanciamento", "Contrato Finalizado", "Portabilidade");
        comNote.setItems(itens);
    }

    @FXML
    public void alterarNotificacaos() {
        not.setStatus(boxStatus.getSelectionModel().getSelectedItem());
        not.setTipoNotificacao(comNote.getSelectionModel().getSelectedItem());
        not.setProximaAlerta(LocalDateTime.parse(campoData.getText(), formater));

        not = notificacaoService.salvaNotificacao(not);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
        alert.show();
    }

    public void initTable() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory("Status"));
        idBanco.setCellValueFactory(new PropertyValueFactory("banco"));
    }

    public ObservableList<Notificacao> atualizaTabela = FXCollections.observableArrayList();

    public void listarNotificacao() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }
        List<Notificacao> listNotificacao = notificacaoService.notifica();

        if (listNotificacao.isEmpty()) {
            
        }

        for (Notificacao not : listNotificacao) {
            atualizaTabela.add(not);
        }

        idBanco.setCellValueFactory((TableColumn.CellDataFeatures<Notificacao, Emprestimo> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getBanco()));
        colunaCliente.setCellValueFactory((TableColumn.CellDataFeatures<Notificacao, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getCliente().getNome()));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tabela.setItems(atualizaTabela);

    }

    public void selecionarItemTableViewNotificacao(Notificacao notifi) {
        campoNome.setText(notifi.getEmprestimo().getCliente().getNome());
        campoCpf.setText(notifi.getEmprestimo().getCliente().getCpf());
        campoTelefone.setText(String.valueOf(notifi.getEmprestimo().getCliente().getTelefone1()));
        campoBanco.setText(notifi.getEmprestimo().getBanco());
        campoData.setText(formater.format(notifi.getProximaAlerta()));
        boxStatus.setValue(notifi.getStatus());
        comNote.setValue(notifi.getTipoNotificacao());
        campoCodigo.setText("" + notifi.getId());
        DATACADASTRO.setText(formater.format(notifi.getData()));
        c = notifi.getEmprestimo().getCliente();
        emp = notifi.getEmprestimo();
        not = notifi;

    }

    public void listarNotificacaoPorId() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }
        List<Notificacao> listNotificacao = notificacaoService.buscarPorId(Long.parseLong(fieldCpf.getText()));

        if (listNotificacao.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Notificação Não Cadastrada!");
            alert.show();
            return;
        }

        for (Notificacao not : listNotificacao) {
            atualizaTabela.add(not);
        }

        idBanco.setCellValueFactory((TableColumn.CellDataFeatures<Notificacao, Emprestimo> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getBanco()));
        colunaCliente.setCellValueFactory((TableColumn.CellDataFeatures<Notificacao, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getCliente().getNome()));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tabela.setItems(atualizaTabela);

    }

    public void atualizaNotificacao() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Notificacao not : notificacaoService.notifica()) {
            atualizaTabela.add(not);
        }
    }
     private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.positionCaret(textField.getText().length());
            }
        });
    }
    
    @FXML
    private void validaDataNotificacao() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/#### ##:##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(campoData);
        formata.formatter();
        positionCaret(campoData);

    }
}
