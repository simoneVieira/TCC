/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import ch.qos.logback.core.CoreConstants;
import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.service.ClienteService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import util.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaClienteController implements Initializable {

    @FXML
    private Pane idVoltarMenu;
    @FXML
    private TextField fieldNome;
    @FXML
    private Label labelNome;
    @FXML
    private TextField fieldCPF;
    @FXML
    private Label labelCpf;
    @FXML
    private TextField fieldRG;
    @FXML
    private Label labelRG;
    @FXML
    private TextField fielTelefone1;
    @FXML
    private TextField fieldTelefone2;
    @FXML
    private Label labeltelefone1;
    @FXML
    private Label labelTelefone2;
    @FXML
    private TextField fieldNasciemento;
    @FXML
    private TextField fieldComplemento;
    @FXML
    private Label labelcomple;
    @FXML
    private TextField fieldSetor;
    @FXML
    private Label labelsetor;
    @FXML
    private TextField fieldCidade;
    @FXML
    private Label labelCidade;
    @FXML
    private TextField fieldCep;
    @FXML
    private Label labelcep;
    @FXML
    private TextField fieldEndereco;
    @FXML
    private Label labelendereco;
    @FXML
    private TextField fieldNumero;
    @FXML
    private Label labelnumero;
    @FXML
    private ImageView imageViewVolt;
    @FXML
    private Label labeldados;
    @FXML
    private Button idSalvar;
    @FXML
    private Button idbtnCanccelar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private ImageView imageSalvar;
    @FXML
    private TextField idCli;
    @FXML
    private ImageView viewExcluir;
    @FXML
    private ImageView viewLupa;
    @FXML
    private TextField campoPesquisa;
    @FXML
    private Button btnp;
    @Value("${my.url}")
    private String myUrl;
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, String> idCollumNome;
    @FXML
    private TableColumn<Cliente, String> idCollumCPF;
    @FXML
    private TableColumn<Cliente, String> idCollumTelefone;
    @FXML
    private TableColumn<Cliente, Date> idCollumNascimento;
    @FXML
    private TableColumn<Cliente, String> idCollumEnd;
    @FXML
    private TableColumn<Cliente, String> idCollumCidade;
    @FXML
    private TableColumn<Cliente, Long> codCLIENTE;
    private ObservableList<Cliente> cliente = FXCollections.observableArrayList();
    @Autowired
    ClienteService clienteService;
    @FXML
    private Label labelAsteriscoN;
    @FXML
    private Label labAsteriscoT;
    @FXML
    private Label labAsteriscoD;
    @FXML
    private Label lbAsteriscoCPF;
    @FXML
    private Label labAstersicoRG;
    public String opcao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imageVoltar = new Image("/imagem/voltar.png");
        imageViewVolt.setImage(imageVoltar);
        Image image = new Image("/imagem/salvar.png");
        imageSalvar.setImage(image);
        Image imageCancelar = new Image("/imagem/Delete-icon-2.png");
        viewExcluir.setImage(imageCancelar);
        Image imagePesquisar = new Image("/imagem/lupa.png");
        viewLupa.setImage(imagePesquisar);
        listarClientes();
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));

    }

    @FXML
    private void validaTelefone2() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("(##)-#####-####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldTelefone2);
        formata.formatter();

    }

    @FXML
    private void validaTelefone1() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("(##)-#####-####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fielTelefone1);
        formata.formatter();

    }

    @FXML
    private void validaCPF() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("###.###.###-##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldCPF);
        formata.formatter();
    }

//    @FXML
//    private void validaCep() {
//        TextFieldFormatter formata = new TextFieldFormatter();
//        formata.setMask("#####-###");
//        formata.setCaracteresValidos("0123456789");
//        formata.setTf(fieldCep);
//        formata.formatter();
//    }
    @FXML
    private void validaNumero() {

        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("########");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNumero);
        formata.formatter();
        MaskFormatter mask = new MaskFormatter();
        mask.setValueContainsLiteralCharacters(false);

    }

    public String removerMascara(String str) {
        return str.replaceAll("\\D", "");
    }

    @FXML
    private void validaDataNascimento() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNasciemento);
        formata.formatter();

    }

    @FXML
    public void salvarCli() {

        if (fieldNome.getText().equals("") || fielTelefone1.getText().equals("")
                || fieldCPF.getText().equals("") || fieldRG.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CONFIRMAÇÃO");
            alert.setHeaderText("Campos com asterico" + "  "
                    + "(*) " + "são de preenchimentos obrigatórios!");
            alert.show();
        } else {

            Cliente cli = new Cliente();

            cli.setNome(fieldNome.getText());
            cli.setCidade(fieldCidade.getText());
            cli.setCep(fieldCep.getText());
            cli.setNumero(fieldNumero.getText());
            cli.setCpf(fieldCPF.getText());
            cli.setComplemento(fieldComplemento.getText());
            cli.setTelefone1(fielTelefone1.getText());
            cli.setTelefone2(fieldTelefone2.getText());
            cli.setSetor(fieldSetor.getText());
            cli.setRg(fieldRG.getText());
            cli.setEndereco(fieldEndereco.getText());

            cli.setDataNascimento(new Date());

            if (idCli.getText().equals("")) {
                try {
                    Cliente salvarCli = clienteService.salvarCli(cli);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("SALVO COM SUCESSO!");
                    alert.show();
                    LimpaCampos();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("AVISO! CPF JÀ CADASTRADO");
                    alert.show();
                    LimpaCampos();
                }

            } else {
                cli.setId(Long.parseLong(idCli.getText()));
                Cliente salvarCli = clienteService.salvarCli(cli);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
                alert.show();
                LimpaCampos();

            }

        }
    }

    public void initTable() {
        idCollumNome.setCellValueFactory(new PropertyValueFactory("nome"));
        idCollumCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        idCollumTelefone.setCellValueFactory(new PropertyValueFactory("telefone1"));
        idCollumNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        idCollumEnd.setCellValueFactory(new PropertyValueFactory("endereco"));
        idCollumCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        codCLIENTE.setCellValueFactory(new PropertyValueFactory("id"));

    }

    public ObservableList<Cliente> atualizaTabela = FXCollections.observableArrayList();
    // metodo q seta o outro metodo de busca pelo o cpf.
    public ObservableList<Cliente> atualizaTabela() {
         Cliente c = new Cliente();
        cliente = (ObservableList<Cliente>) clienteService.buscaCli(c.getCpf());
        return cliente;
    }

    public void listarClientes() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();
            System.out.println("Limpou a tabela");
        }

        for (Cliente cliente : clienteService.clie()) {
            atualizaTabela.add(cliente);
        }

        idCollumNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        idCollumCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        idCollumTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone1"));
        idCollumNascimento.setCellValueFactory(new PropertyValueFactory<Cliente, Date>("dataNascimento"));
        idCollumEnd.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
        idCollumCidade.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cidade"));
        codCLIENTE.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("id"));
        tabela.setItems(atualizaTabela);
    }

    // metodo intitulado para filtro com o cpf
    private ObservableList<Cliente> buscaCli() {
        ObservableList<Cliente> clientePesquisa = FXCollections.observableArrayList();
        for (int x = 0; x < cliente.size(); x++) {
            if (cliente.get(x).getCpf().contains(campoPesquisa.getText())) {
                clientePesquisa.add(cliente.get(x));
            }
        }
        return clientePesquisa;
    }

    //chamada do metodo dentro do botão de busca
    @FXML
    public void botaoP() {
        tabela.setItems(buscaCli());
        System.out.println("oieee");
    }

    public void selecionarItemTableViewClientes(Cliente cliente) {
        fieldNome.setText(cliente.getNome());
        fieldCPF.setText(cliente.getCpf());
        fieldRG.setText(cliente.getRg());
        fielTelefone1.setText(cliente.getTelefone1());
        fieldTelefone2.setText(cliente.getTelefone2());
        //fieldNasciemento.setText(cliente.getDataNascimento());
        fieldComplemento.setText(cliente.getComplemento());
        fieldSetor.setText(cliente.getSetor());
        fieldCidade.setText(cliente.getCidade());
        fieldCep.setText(cliente.getCep());
        fieldEndereco.setText(cliente.getEndereco());
        fieldNumero.setText(cliente.getNumero());
        idCli.setText(String.valueOf(cliente.getId()));
    }

    public void excluirCliente() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("OK");
        //ButtonType btnNao = new ButtonType("Não");
        ButtonType btnNaoResponder = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DESEJA REALMENTE APAGAR DADOS? ");
        alert.getButtonTypes().setAll(btnSim, btnNaoResponder);
        alert.showAndWait().ifPresent((ButtonType b) -> {
            if (b == btnSim) {
                Cliente cli = new Cliente();
                cli.setId(Long.parseLong(idCli.getText()));
                clienteService.excluirCliente(cli.getId());
                fieldNome.setText("");
                fieldCPF.setText("");
                fieldRG.setText("");
                fielTelefone1.setText("");
                fieldTelefone2.setText("");
                //fieldNasciemento.setText(cliente.getDataNascimento());
                fieldComplemento.setText("");
                fieldSetor.setText("");
                fieldCidade.setText("");
                fieldCep.setText("");
                fieldEndereco.setText("");
                fieldNumero.setText("");
                idCli.setText("");

                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("INFORMAÇÂO");
                dialogoResultado.setContentText("DADOS DELETADOS COM SUCESSO!");
                dialogoResultado.showAndWait();
            } else {
               alert.close();

            }

        });
    }

    public void LimpaCampos() {
        fieldNome.setText("");
        fieldCPF.setText("");
        fieldRG.setText("");
        fielTelefone1.setText("");
        fieldTelefone2.setText("");
        fieldComplemento.setText("");
        fieldSetor.setText("");
        fieldCidade.setText("");
        fieldCep.setText("");
        fieldEndereco.setText("");
        fieldNumero.setText("");
        idCli.setText("");
    }
   
    public void abreTelaPrincipal(){
        TelaPrincipalController tpc = new TelaPrincipalController();
        
    }

}
