/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import ch.qos.logback.core.CoreConstants;
import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.service.ClienteService;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
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

    
   @FXML private Pane idVoltarMenu;
   @FXML private TextField fieldNome;
   @FXML private Label labelNome;
   @FXML private TextField fieldCPF;
   @FXML private Label labelCpf;
   @FXML private TextField fieldRG;
   @FXML private Label labelRG;     
   @FXML private TextField fielTelefone1;
   @FXML private TextField fieldTelefone2;
   @FXML private Label labeltelefone1;
   @FXML private Label labelTelefone2;
   @FXML private TextField fieldNasciemento;
   @FXML private TextField fieldComplemento;
   @FXML private Label labelcomple;
   @FXML private TextField fieldSetor;
   @FXML private Label labelsetor;
   @FXML private TextField fieldCidade;
   @FXML private Label labelCidade;
   @FXML private TextField fieldCep;
   @FXML private Label labelcep;
   @FXML private TextField fieldEndereco;
   @FXML private Label labelendereco;
   @FXML private TextField fieldNumero;
   @FXML private Label idAsteriscoN;
   @FXML private Label labelnumero;
   @FXML private ImageView imageViewVolt;
   @FXML private Label labeldados;
   @FXML private Button idSalvar;
   @FXML private Button idbtnCanccelar;
   @FXML private Button btnPesquisar;
   @FXML private ImageView imageSalvar;
   @FXML private TextField idCli;
   @FXML private ImageView viewExcluir;
   @FXML private ImageView viewLupa;
   @Value("${my.url}") 
   private String myUrl; 
   @FXML private TableView<Cliente> tabela;
   @FXML private TableColumn<Cliente, String> idCollumNome;
   @FXML private TableColumn<Cliente, String> idCollumCPF;
   @FXML private TableColumn<Cliente, String> idCollumTelefone;
   @FXML private TableColumn<Cliente, Date> idCollumNascimento;
   @FXML private TableColumn<Cliente, String> idCollumEnd;
   @FXML private TableColumn<Cliente, String> idCollumCidade;

    @Autowired
    ClienteService clienteService;
   
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
    // listarClientes();
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

//    @FXML
//    private void validaCep() {
//        TextFieldFormatter formata = new TextFieldFormatter();
//        formata.setMask("#####-###");
//        formata.setCaracteresValidos("0123456789");
//        formata.setTf(fieldCep);
//        formata.formatter();
//    }
    @FXML
    private void validaNumero() {

        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("########");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNumero);
        formata.formatter();
        MaskFormatter mask = new MaskFormatter();
        mask.setValueContainsLiteralCharacters(false);

    }

    public String removerMascara(String str) {
        return str.replaceAll("\\D", "");
    }

    @FXML
    private void validaDataNascimento() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNasciemento);
        formata.formatter();

    }

    

    @FXML
    public void salvarCli() {

        Cliente cli = new Cliente();

        cli.setNome(fieldNome.getText());
        cli.setCidade(fieldCidade.getText());
        cli.setCep(fieldCep.getText());
        cli.setNumero(fieldNumero.getText());
        cli.setCpf(fieldCPF.getText());
        cli.setComplemento(fieldComplemento.getText());
        cli.setTelefone1(fielTelefone1.getText());
        cli.setTelefone2(fieldTelefone2.getText());
        cli.setSetor(fieldSetor.getText());
        cli.setRg(fieldRG.getText());
        cli.setEndereco(fieldEndereco.getText());

        cli.setDataNascimento(new Date());

        if (idCli.getText().equals("")) {
            Cliente salvarCli = clienteService.salvarCli(cli);
            validaNumero();

        } else {
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
        }
        clienteService.clie();
    }
//    public void initTable(){
//    idCollumNome.setCellValueFactory(new PropertyValueFactory("nome"));
//    idCollumCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
//    idCollumTelefone.setCellValueFactory(new PropertyValueFactory("telefone1"));
//    idCollumNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
//    idCollumEnd.setCellValueFactory(new PropertyValueFactory("endereco"));
//    idCollumCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
//   
//    }
//    private final  List<Cliente>clientes = clienteService.findAll();
   
   //  public ObservableList<Cliente>atualizaTabela = FXCollections.observableArrayList();
     
//     public void listarClientes(){
//         if(!atualizaTabela.isEmpty()){
//             atualizaTabela.clear();
//             System.out.println("Limpou a tabela");
//         }
//         
//         for(Cliente cliente: clientes){
//             Cliente c = new Cliente(cliente.getNome());
//             atualizaTabela.add(c);
//         }
//          idCollumNome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nome"));
//          tabela.setItems(atualizaTabela);
//     }
        
//   Cliente buscaCli = (Cliente) clienteService.listAll();
       // retu/rn FXCollections.observableArrayList(clienteService.getList());
       // System.out.println();
       
   
   // return null;
    //}
    
    
    // public void miau() {
       
        
    //}
     
  
}
