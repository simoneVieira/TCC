/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.service.ClienteService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaCliController implements Initializable {
    
    
    @Autowired
    ClienteService  clienteService ;
   
   @FXML
    private TextField idTeste;

    @FXML
    private Button iidsalvaTeste;
      

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
   
    }    
   
}
