/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.service.ClienteService;
import com.bettercoding.jfx.service.EmprestimoService;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaEmprestimoController implements Initializable, ReceptorCliente {
    
    @FXML
    private MenuButton IDCONVENIO;
    
    @FXML
    private MenuButton boxStatus;
    
    @FXML
    private RadioButton radioConta;
    
    @FXML
    private RadioButton radioOP;
    
    @FXML
    private TextField idParcelas;
    
    @FXML
    private TextField fieldMatricula;
    
    @FXML
    private MenuButton boxBanco;
    
    @FXML
    private TextField fieldBeneficio;
    
    @FXML
    private TextField fieldValor;
    
    @FXML
    private TextField idTaxa;
    
    @FXML
    private TextField fieldDataInicio;
    
    @FXML
    private TextField fieldDataFinal;
    
    @FXML
    private TextField fieldNumContrato;
    
    @FXML
    private TextField idValorSolicitado;
    
    @FXML
    private TextField idValorLiberado;
    
    @FXML
    private TextField idComissão;
    
    @FXML
    private TextField fieldComi;
    
    @FXML
    private Button buttonSelecionarCliente;
    
    @FXML
    public TextField fieldNomeCliente;
    @FXML
    private TextField fieldCpfCli;
    
    @FXML
    private MenuButton boxFinan;
    
    @FXML
    private TextArea idTextArea;
    
    @FXML
    private ImageView viewNovo;
    @FXML
    private MenuButton menuFormaContrato;
    
    @FXML
    private Button buttonSalvar;
    
    @FXML
    private Button buttonVoltar;
    
    @FXML
    private Button buttonExcluir;
    
    @FXML
    private TableView<?> tabela;
    
    @FXML
    private TextField fieldPesquisa;
    
    @FXML
    private Button buttonP;
    
    @FXML
    private ImageView viewSalvar;
    
    @FXML
    private ImageView viewVoltar;
    
    @FXML
    private ImageView viewCancelar;
    
    @FXML
    private TextField idCliente;
    
    @FXML
    private ImageView viewPesquisa;
    @Autowired
    EmprestimoService emprestimoService;
    
    Emprestimo emprestimo = new Emprestimo();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/imagem/salvar.png");
        viewSalvar.setImage(image);
        Image imageVoltar = new Image("/imagem/voltar.png");
        viewVoltar.setImage(imageVoltar);
        Image imageCancelar = new Image("/imagem/Delete-icon-2.png");
        viewCancelar.setImage(imageCancelar);
        Image imagePesquisar = new Image("/imagem/pesquisa4.png");
        viewPesquisa.setImage(imagePesquisar);
        Image imageNovo = new Image("/imagem/cliente.png");
        viewNovo.setImage(imageNovo);
        
        
        fieldNomeCliente.setText(TelaClienteController.getNomeCli());
        fieldCpfCli.setText(TelaClienteController.getCpfCli());
    }
    
    @FXML
    public void botaoVoltar() {
        TelaClienteController tcc = new TelaClienteController();
        tcc.abreTelaPrincipal();
        // tcc.fechaTela();

    }

    public void chamaTelaCliente() {
        TelaPrincipalController t = new TelaPrincipalController();
        //TelaClienteController tc = new TelaClienteController(this);
       // TelaPrincipalController.retornaCenaEmprestimo().close();
       t.botaoCliente();
       TelaClienteController.setReceptor(this);
       
       
       
       
        
    }
    //  salvar dentro o metodo que salva emprestimo  o id do cliente para associar ao emprestimo e salvar 
    // a relação das tabelas no banco.

    @FXML
    public void salvarEmprestimo() {
        //Cliente c = new Cliente();
        
        emprestimo.setValorParcela(fieldValor.getText());
        emprestimo.setCliente(new Cliente());
        emprestimo.getCliente().getId();
      // emprestimo.getCliente().setId(1L);
       
        
        Emprestimo salvarEmprestimo = emprestimoService.salvaEmprestimo(emprestimo);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
        alert.show();
    }

    @Override
    public void receberCliente(Cliente c) {
    this.emprestimo.setCliente(c);
    }
    
}
