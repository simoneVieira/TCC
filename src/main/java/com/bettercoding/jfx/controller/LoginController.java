/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

//import com.sun.istack.internal.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
//import view.ExibirLogin;

/**
 *
 * @author SimoneBarbosa
 */
@Controller
public class LoginController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Pane painelLogin;

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
    private AnchorPane AnchorPane;
    private static Stage stage;
    // @Override
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  Image image = new Image("/recursos/sifraoLogin.jpg");
        // imageview.setImage(image);

    }
    //@FXML   
// protected void botaoEntrar(ActionEvent e){
    //   MyApp.changescren("cenaPrincipal");
// }

    @FXML
    protected void botaoEntrar(ActionEvent event) {

        if (idUsuario.getText().equals("simone") && (idSenha.getText().equals("123"))) {
           stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/TelaPrincipal.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null,ex);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("TELA PRINCIPAL");

            botaoEntrar.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DADOS PARA LOGAR");
            alert.setHeaderText("dados errados");
            alert.show();
        }

    }
    public static Stage retornaStage(){
        return stage;
    }
}

