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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import util.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaClienteController implements Initializable {

    

    @Autowired
    ClienteService  clienteServi ;
   
    @FXML
    private Pane idVoltarMenu;

    @FXML
    private TextField fieldNome;

    @FXML
    private Label labelNome;

    @FXML
    private TextField fieldCPF;

    @FXML
    private Label labelCpf;

    @FXML
    private TextField fieldRG;

    @FXML
    private Label labelRG;

    @FXML
    private TextField fielTelefone1;

    @FXML
    private TextField fieldTelefone2;

    @FXML
    private Label labeltelefone1;

    @FXML
    private Label labelTelefone2;

    @FXML
    private TextField fieldNasciemento;

    @FXML
    private TextField fieldComplemento;

    @FXML
    private Label labelcomple;

    @FXML
    private TextField fieldSetor;

    @FXML
    private Label labelsetor;

    @FXML
    private TextField fieldCidade;

    @FXML
    private Label labelCidade;

    @FXML
    private TextField fieldCep;

    @FXML
    private Label labelcep;

    @FXML
    private TextField fieldEndereco;

    @FXML
    private Label labelendereco;

    @FXML
    private TextField fieldNumero;

    @FXML
    private Label labelnumero;

    @FXML
    private ImageView imageViewVolt;

    @FXML
    private Label labelConta;

    @FXML
    private Label labelbanco;

    @FXML
    private Label labelage;

    @FXML
    private Label labelcontaa;

    @FXML
    private TextField fieldBanco;

    @FXML
    private TextField fieldAgencia;

    @FXML
    private TextField fieldConta;

    @FXML
    private Label labeldados;

    @FXML
    private Button idSalvar;

    @FXML
    private Button idbtnCanccelar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private ImageView imageSalvar;

    @FXML
    private ImageView viewExcluir;
     @FXML
    private ImageView viewLupa;
     @Value("${my.url}")
   private String myUrl;

  
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Image imageVoltar = new Image("/imagem/voltar.png");
       imageViewVolt.setImage(imageVoltar);
        Image image = new Image("/imagem/salvar.png");
        imageSalvar.setImage(image);
        Image imageCancelar = new Image("/imagem/Delete-icon-2.png");
        viewExcluir.setImage(imageCancelar);
       Image imagePesquisar = new Image("/imagem/pesquisa4.png");
       viewLupa.setImage(imagePesquisar);
    }

    @FXML
    private void validaTelefone2() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("(##)-#####-####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldTelefone2);
        formata.formatter();

    }
    @FXML
    private void validaTelefone1() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("(##)-#####-####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fielTelefone1);
        formata.formatter();

    }

    @FXML
    private void validaCPF() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("###.###.###-##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldCPF);
        formata.formatter();
    }

    @FXML
    private void validaCep() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("#####-###");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldCep);
        formata.formatter();
    }

    @FXML
    private void validaNumero() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("########");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNumero);
        formata.formatter();
    }
    @FXML
    private void validaDataNascimento() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNasciemento);
        formata.formatter();
    }
    public void inativaCampo(){
        fieldConta.setEditable(false);
    
}
   @FXML
    public void idSalvar(ActionEvent e){
        Cliente c = new Cliente();
       c.setNome("João");
        
        clienteServi.salvarCli(c);
        
    }
}
