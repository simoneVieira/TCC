/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.management.Query.value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaPrincipalController implements Initializable {

    @FXML
    private Button botaoRelatorio;

    @FXML
    private Button botaoCliente;

    @FXML
    private Button botaoEmprestimo;
    @FXML
    private Button idCadastro;
    @FXML
    private Label painelNotificacao;
    @FXML
    private ImageView imgCliente;
    @FXML
    private ImageView viewEmprestimo;
    @FXML
    private ImageView imgRelatorio;
    @FXML
    private Label voltarLogin;
    @FXML
    private Label labelRedefineSenha;
    private static Scene cenaLogin;

    private static Stage stage;

    UsuarioController usc = new UsuarioController();
    Usuario us;
    @Autowired
    ExecutaTarefa exect;
    @FXML
    private AnchorPane labelNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/fxml/cliente.png");
        imgCliente.setImage(image);
        Image img = new Image("/imagem/emp.png");
        viewEmprestimo.setImage(img);
        Image imgRl = new Image("/imagem/relatorio.png");
        imgRelatorio.setImage(imgRl);

        if (UsuarioController.userLogado.getTipoUsuario().equals(UsuarioController.TIPO_ADMIN)) {
            botaoRelatorio.setVisible(true);
            imgRelatorio.setVisible(true);
            idCadastro.setVisible(true);
            labelRedefineSenha.setVisible(false);
        }
       
            exect.run();
        
    }

    @FXML
    protected void botaoCliente() {

        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaCliente.fxml"));
            root = fxml.load();

        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root, 1800, 700);

        stage.setScene(scene);

        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

        botaoCliente.getScene().getWindow().hide();

    }

    public static Stage retornaStage() {
        return stage;
    }

    @FXML
    protected void botaoEmprestimo() {
        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaEmprestimo.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        botaoEmprestimo.getScene().getWindow().hide();

    }

    @FXML
    protected void idCadastro() {

        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaUsuario.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);
    }

    public static Stage retornaCenaEmprestimo() {
        return stage;
    }

    public void fechaTelaPrincipal() {
        UsuarioController.retornaStage().close();
    }

    public static Stage fechaCliente() {
        return stage;
    }

    public static Stage fechaEmprestimo() {
        return stage;
    }

    @FXML
    public void abreTelaNovaSenha() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaAlterarSenha.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Redefinir Senha");
        stage.setResizable(false);

    }

    @FXML
    public void reiniciar() {

        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/login.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TELA LOGIN");
        fechaTelaPrincipal();
        retornaStage();
        voltarLogin.getScene().getWindow().hide();
    }

    @FXML
    public void chamaTelaPainel() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaPainelNotificacao.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);
    }

    @FXML
    public void TelaRelatorio() {
        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaRelatorio.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

    }

    @FXML
    private void chamaRelatorio(ActionEvent event) {
    }
}
