/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

//import com.sun.istack.internal.logging.Logger;
import static antlr.build.ANTLR.root;
import com.pacote.jfx.MyApp;
import com.pacote.jfx.model.Usuario;
import com.pacote.jfx.service.Criptografar;
import com.pacote.jfx.service.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import view.ExibirLogin;

/**
 *
 * @author SimoneBarbosa
 */
@Controller
public class UsuarioController implements Initializable {

    @FXML
    private AnchorPane p;

    @FXML
    private Label label;
    @FXML

    private Pane painelLogin;
    
    @FXML
    private TextField mostrarSenha;

    @FXML
    private CheckBox boxMostar;
    @FXML
    private TextField idUsuario;

    @FXML
    private PasswordField idSenha;

    @FXML
    private Label labelRecuperaSenha;

    @FXML
    private Label labelDica;

    @FXML
    private Button botaoEntrar;

    @FXML
    private ImageView imageview;

    @FXML
    private Label labelTitulo;

    @FXML
    private ImageView viewCadeado;
    @FXML
    private AnchorPane pane;
    private static Stage stage;
    @FXML
    private AnchorPane AnchorPane;
    Usuario usuario = new Usuario();
    @Autowired
    UsuarioService usuarioService;
    AdminstradorController tc;
    public static Usuario userLogado;
    public static final String TIPO_ADMIN = "Adm";
    public static final String TIPO_ATENDENTE = "ATEN";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    }
    

    @FXML
    protected void botaoEntrar() {

        userLogado = usuarioService.buscaUsuarioESenha(idUsuario.getText(),Criptografar.criptoSenha(idSenha.getText()));
        // colocar no segundo paramentro do metodo buscaUsuarioESenha no lugar de idSenha.getText
       // Criptografar.criptoSenha(idSenha.getText())

        if (userLogado != null) {
            stage = new Stage();

           // stage = new Stage();
            Parent root = null;
            try {
                FXMLLoader fxml = new FXMLLoader();
                fxml.setControllerFactory(MyApp.springContext::getBean);
                fxml.setLocation(getClass().getResource("/fxml/TelaPrincipal.fxml"));
                root = fxml.load();
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

//            
            botaoEntrar.getScene().getWindow().hide();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DADOS PARA LOGAR");
            alert.setHeaderText(" Usuário ou senha Incorreto!");
            alert.show();

        }
  
    }

    public static Stage retornaStage() {
        return stage;
    }

    @FXML
    protected void abreTelaEmail() {

        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaEmail.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Recupera Senha");

        //    labelRecuperaSenha.getScene().getWindow().hide();
    }
    public void mostrarSenha(){
        if(boxMostar.isSelected()){
         idSenha.setVisible(false);
         String c = idSenha.getText();
         mostrarSenha.setText(c);
         mostrarSenha.setVisible(true);
        }else{
         idSenha.setVisible(true);
         mostrarSenha.setVisible(false);
        }
    }
}

