/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.MyApp;
import com.pacote.jfx.model.Cliente;
import com.pacote.jfx.model.Usuario;
import com.pacote.jfx.service.Criptografar;
import com.pacote.jfx.service.UsuarioService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author SimoneBarbosa
 *
 */
@Controller
public class EmailController implements Initializable {

    @FXML
    private AnchorPane pEmail;

    @FXML
    private Pane painelEmail;

    @FXML
    private TextField fieldEmail;
     @FXML
    private ImageView emailSenha;

    @FXML
    private Label labelEmail;

    @FXML
    private Button buttonEnviar;

    AdminstradorController usuarioC = new AdminstradorController();
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    Criptografar criptografarService;
    Usuario u = new Usuario();

    public void initialize(URL location, ResourceBundle resources) {
         Image email = new Image("/imagem/sair12.png");
        emailSenha.setImage(email);

    }

    public void enviarEmail() {
        String enviarEmail = "emaildetestejavatcc@gmail.com";
        String minhaSenha = "simone123@";
        Usuario usuario = usuarioService.buscaPorEmail(fieldEmail.getText());

        if (usuario == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Email não Cadastrado");
            alert.show();
            return;
        }
        String senha = AdminstradorController.geraNumeroAleatorio();
        String s = Criptografar.criptoSenha(senha);
        usuario.setSenha(s);
        usuario = usuarioService.salvaUsuario(usuario);

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(enviarEmail, minhaSenha));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(enviarEmail);
            email.setSubject("Sua nova senha");
            email.setMsg(" foi gerado uma nova senha para acesso do sistema: \n Favor copiar a senha e colar no campo\n Para evitar possíveis erros de digitação!  "+ "SENHA: " + senha);
            email.addTo(usuario.getEmail());
            email.send();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Email enviado com sucesso !");
            alert.show();
            fieldEmail.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
