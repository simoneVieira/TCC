/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import static com.pacote.jfx.controller.UsuarioController.userLogado;
import com.pacote.jfx.model.Usuario;
import com.pacote.jfx.service.Criptografar;
import com.pacote.jfx.service.UsuarioService;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.regex.*;

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
    private Label labelAtualizar;

    @FXML
    private TextField senhaUsuario;

    @FXML
    @NotEmpty(message = "favor preencher")
    private TextField emailUsuario;

    @FXML
    private Button butaoSalvar;

    @FXML
    private Label idNovo;

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
    Usuario user = new Usuario();
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

        if (idNomeUsuario.getText().equals("") || senhaUsuario.getText().equals("")
                || emailUsuario.getText().equals("")
                || comBoUsuario.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA");
            alert.setHeaderText("FAVOR PREENCHER TODOS OS CAMPOS!!");
            alert.show();
            geraNumeroAleatorio();
        } else {
      
            user.setSenha(Criptografar.criptoSenha(senhaUsuario.getText()));
            // Criptografar.criptoSenha(senhaUsuario.getText());
            user.setUsuario(idNomeUsuario.getText());
            user.setTipoUsuario("" + comBoUsuario.getSelectionModel().getSelectedItem());

            if (idcodigo.getText().equals("")) {
                Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
                Matcher m = p.matcher(emailUsuario.getText());
                if (m.find()) {
                    user.setEmail(emailUsuario.getText());
                } else {
                    Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                    dialogoResultado.setHeaderText("INFORMAÇÃO");
                    dialogoResultado.setContentText("Email inválido!");
                    dialogoResultado.showAndWait();
                    return;
                }
                Usuario usua = usuarioService.salvaUsuario(user);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("SALVO COM SUCESSO!");
                alert.show();
                return;
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

    @FXML
    public void desativarUsuario() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("OK");
        ButtonType btnNaoResponder = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DESEJA REALMENTE APAGAR DADOS? ");
        alert.getButtonTypes().setAll(btnSim, btnNaoResponder);
        alert.showAndWait().ifPresent((ButtonType b) -> {
            if (b == btnSim) {
                Usuario user = new Usuario();
                user.setId(Long.parseLong(idcodigo.getText()));
                usuarioService.desativarUsuario(user.getId());
                idNomeUsuario.setText("");
                idcodigo.setText("");
                senhaUsuario.setText("");
                emailUsuario.setText("");
                comBoUsuario.setValue("");
                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("INFORMAÇÂO");
                dialogoResultado.setContentText("DADOS DELETADOS COM SUCESSO!");
                dialogoResultado.showAndWait();
            } else {
                alert.close();

            }

        });
    }

    public void atualizarTabela() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Usuario usuario : usuarioService.user()) {
            atualizaTabela.add(usuario);
        }

    }

    public void listarUsuarioBynome() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        List<Usuario> listaUsuario = usuarioService.buscaPorUsuario("" + idPesquisa.getText());

        if (listaUsuario.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Usuario Não encontrado!");
            alert.show();
            return;
        }

        for (Usuario usuario : listaUsuario) {
            atualizaTabela.add(usuario);
        }

        emailUsuario.setText(usuario.getEmail());
        comBoUsuario.setValue(usuario.getTipoUsuario());
        senhaUsuario.setText(usuario.getSenha());
        idNomeUsuario.setText(usuario.getUsuario());
        idcodigo.setText(String.valueOf(usuario.getId()));

        tabelaUsuario.setItems(atualizaTabela);
    }

    public void limpaCampos() {
        emailUsuario.setText(" ");
        comBoUsuario.setValue(" ");
        senhaUsuario.setText(" ");
        idNomeUsuario.setText(" ");
        idcodigo.setText(" ");
    }

    
}
