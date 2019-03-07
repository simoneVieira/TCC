/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
  
    
     
    
   // @Override
    public void initialize(URL url, ResourceBundle rb) {
     //  Image image = new Image("/recursos/sifraoLogin.jpg");
      // imageview.setImage(image);
       
       
   }
 //@FXML   
// protected void botaoEntrar(ActionEvent e){
  //   MyApp.changescren("cenaPrincipal");
// }

    @FXML
    private void botaoEntrar(ActionEvent event) {
    }

    
}
