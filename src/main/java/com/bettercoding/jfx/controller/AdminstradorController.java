/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import static com.bettercoding.jfx.controller.UsuarioController.userLogado;
import com.bettercoding.jfx.model.Usuario;
import com.bettercoding.jfx.service.Criptografar;
import com.bettercoding.jfx.service.UsuarioService;
import java.net.URL;
import static java.util.Collections.list;
import java.util.Random;
import java.util.ResourceBundle;
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
public class AdminstradorController implements Initializable {

    @FXML
    private TextField idNomeUsuario;
    @FXML
    private TextField idcodigo;

    @FXML
    private TextField senhaUsuario;

    @FXML
    private TextField emailUsuario;

    @FXML
    private Button butaoSalvar;

    @FXML
    private Button butaoDesativar;
    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableColumn<Usuario, String> idNomeUsu;
    @FXML
    private ComboBox<String> comBoUsuario;

    @FXML
    private TableColumn<Usuario, String> idSenha;

    @FXML
    private TableColumn<Usuario, String> idEmail;

    @FXML
    private TextField idPesquisa;
    Usuario usuario = new Usuario();
    @FXML
    private Button pesquisaUsuario;
    @Autowired
    UsuarioService usuarioService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarUsuarios();
        initTable();
        combos();
        tabelaUsuario.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }

    public void initTable() {
        idNomeUsu.setCellValueFactory(new PropertyValueFactory("usuario"));
        idSenha.setCellValueFactory(new PropertyValueFactory("senha"));
        idEmail.setCellValueFactory(new PropertyValueFactory("email"));

    }
    public ObservableList<Usuario> atualizaTabela = FXCollections.observableArrayList();

    public void listarUsuarios() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Usuario usuario : usuarioService.user()) {
            atualizaTabela.add(usuario);
        }
        idNomeUsu.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        idSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        idEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tabelaUsuario.setItems(atualizaTabela);
    }

    public void combos() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll(UsuarioController.TIPO_ATENDENTE, UsuarioController.TIPO_ADMIN);
        comBoUsuario.setItems(itens);
    }

    @FXML
    public void salvaUsuario() {

        if (idNomeUsuario.getText().equals("") || senhaUsuario.getText().equals("") || emailUsuario.getText().equals("")
                || comBoUsuario.getSelectionModel().getSelectedItem().equals(" ")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA");
            alert.setHeaderText("FAVOR PREENCHER TODOS OS CAMPOS!!");
            alert.show();
            geraNumeroAleatorio();
        } else {
            Usuario user = new Usuario();
            user.setEmail(emailUsuario.getText());
            user.setSenha(senhaUsuario.getText());
           // Criptografar.criptoSenha(senhaUsuario.getText())
            user.setUsuario(idNomeUsuario.getText());
            user.setTipoUsuario("" + comBoUsuario.getSelectionModel().getSelectedItem());

            if (idcodigo.getText().equals("")) {
                try {
                    Usuario usua = usuarioService.salvaUsuario(user);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("SALVO COM SUCESSO!");
                    alert.show();

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("AVISO! CPF JÀ CADASTRADO");
                    alert.show();

                }

            } else {
                user.setId(Long.parseLong(idcodigo.getText()));
                Usuario usua = usuarioService.salvaUsuario(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
                alert.show();

            }

        }

    }

    public void selecionarItemTableViewClientes(Usuario usuario) {
        emailUsuario.setText(usuario.getEmail());
        comBoUsuario.setValue(usuario.getTipoUsuario());
        senhaUsuario.setText(usuario.getSenha());
        idNomeUsuario.setText(usuario.getUsuario());
        idcodigo.setText(String.valueOf(usuario.getId()));

    }

    public static String geraNumeroAleatorio() {
        Random ran = new Random();
        int n = ran.nextInt(1000000) + 1;
        String valorAleatorio = String.valueOf(n);
        return valorAleatorio;

    }

}
