/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.service.EmprestimoService;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificacaoController implements Initializable {

   @FXML
    private Label labelNomeCliente;

    @FXML
    private Label labelTelefone;

    @FXML
    private Label labelCpf;

    @FXML
    private Label labelBanco;

    @FXML
    private Button botaoConfimar;

    @FXML
    private Button botaoAdiar;

    @FXML
    private Button botaoCancelar;
   
    @FXML
    private TextField novaData;

    @FXML
    private TextField novoHorario;

    @FXML
    private Button butnSalvar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
    }

   
    @FXML
    public void mostrarCampos() {
     novaData.setVisible(true);
     novoHorario.setVisible(true);
     butnSalvar.setVisible(true);
       
    }
     @FXML
    public void chamaTelaPainelNotificacao(){
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
    
}