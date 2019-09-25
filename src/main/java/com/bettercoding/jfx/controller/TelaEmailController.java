/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Cliente;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 *
 * @author SimoneBarbosa
 * 
 */
@Controller
public class TelaEmailController implements Initializable {
      @FXML
    private AnchorPane pEmail;

    @FXML
    private Pane painelEmail;

    @FXML
    private TextField fieldEmail;

    @FXML
    private Label labelEmail;

    @FXML
    private Button buttonEnviar;

    @FXML
    private Button buttonCancelar;
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
   

    }
 

}
