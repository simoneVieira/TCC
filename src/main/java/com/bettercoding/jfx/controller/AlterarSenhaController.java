/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Usuario;
import com.bettercoding.jfx.service.Criptografar;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private TextField senhaExistente;

    @FXML
    private TextField campoNova;

    @FXML
    private CheckBox boxAntiga;

    @FXML
    private CheckBox boxNova;

    @FXML
    private ImageView imagemSalvar;

    @FXML
    private Button butonSalvar;
    @Autowired
    UsuarioService usuarioService;
    Usuario usuario;
    @Autowired
    Criptografar criptografarService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("/imagem/salvar.png");
        imagemSalvar.setImage(image);
    }

    public void alteraSenha() {

        Usuario usuario = usuarioService.buscaSenha(Criptografar.criptoSenha(fieldNovaSenha.getText()));

        if (usuario == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("A senha Antiga está Incorreta!");
            alert.show();

        } else {
            usuario.setSenha(Criptografar.criptoSenha(codigoUser.getText()));
            usuario = usuarioService.salvaUsuario(usuario);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Alterado com Sucesso");
            alert.show();
        }

    }

    public void mostrarSenha() {
        if (boxAntiga.isSelected()) {
            fieldNovaSenha.setVisible(false);
            String c = fieldNovaSenha.getText();
            senhaExistente.setText(c);
            senhaExistente.setVisible(true);
        } else {
           fieldNovaSenha.setVisible(true);
            senhaExistente.setVisible(false);
        }
    }

    public void mostrarSenhaNova() {
        if (boxNova.isSelected()) {
            campoNova.setVisible(false);
            String c = codigoUser.getText();
            campoNova.setText(c);
            campoNova.setVisible(true);
        } else {
            codigoUser.setVisible(true);
            campoNova.setVisible(false);
        }
    }

}
