/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx;

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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private Button botaoRelatorio;

    @FXML
    private Button botaoCliente;

    @FXML
    private Button botaoEmprestimo;

    @FXML
    private Button botaoContratos;

    @FXML
    private Button botaoOP;

    @FXML
    private ImageView imgCliente;
    @FXML
    private ImageView viewEmprestimo;
    @FXML
    private ImageView imagemContrato;
    @FXML
    private ImageView imagemOP;
    @FXML
    private ImageView imgRelatorio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/fxml/cliente.png");
        imgCliente.setImage(image);
        Image img = new Image("/imagem/emp.png");
        viewEmprestimo.setImage(img);
        Image imgContrato = new Image("/imagem/contratos.png");
        imagemContrato.setImage(imgContrato);
        Image imgOp = new Image("/imagem/op.png");
        imagemOP.setImage(imgOp);
        Image imgRl = new Image("/imagem/relatorio.png");
        imgRelatorio.setImage(imgRl);

    }
   

    @FXML
    protected void botaoCliente(ActionEvent e) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/TelaCliente.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TELA PRINCIPAL");
        botaoCliente.getScene().getWindow().hide();

        System.out.println("chegouu aqui");

    }
}
