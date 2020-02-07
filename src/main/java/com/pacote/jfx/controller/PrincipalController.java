/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.MyApp;
import com.pacote.jfx.model.Cliente;
import com.pacote.jfx.model.Emprestimo;
import com.pacote.jfx.model.Usuario;
import com.pacote.jfx.service.EmprestimoService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.management.Query.value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class PrincipalController implements Initializable {

    @FXML
    private Button botaoRelatorio;

    @FXML
    private Button botaoCliente;

    @FXML
    private Button botaoEmprestimo;
     
      @FXML
    private Button btnLixeira;

    @FXML
    private ImageView imgLixeira;

    @FXML
    private Button buscarDados;
    @FXML
    private Button idCadastro;
    @FXML
    private ImageView imagemOp;
    @FXML
    private ImageView viewUsuario;
    @FXML
    private Label painelNotificacao;
    @FXML
    private ImageView imgCliente;
    @FXML
    private ImageView viewEmprestimo;
    @FXML
    private ImageView imgRelatorio;

    @FXML
    private Label voltarLogin;
    @FXML
    private Label labelRedefineSenha;
    private static Scene cenaLogin;

    private static Stage stage;
    private static Stage stages;

    UsuarioController usc = new UsuarioController();
    Usuario us;
    @Autowired
    ExecutaTarefa exect;
    @Autowired
    ExecutaTarefaOp exectTaOP;
    @FXML
    @Autowired
    EmprestimoService emprestimoService;
    Emprestimo em = new Emprestimo();
    private AnchorPane labelNome;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/fxml/cliente.png");
        imgCliente.setImage(image);
        Image img = new Image("/imagem/emp.png");
        viewEmprestimo.setImage(img);
        Image imgRl = new Image("/imagem/relatorio.png");
        imgRelatorio.setImage(imgRl);
        Image imgOP = new Image("/imagem/op.png");
        imagemOp.setImage(imgOP);
        Image imguse = new Image("/imagem/icone_usuario_novo.png");
        viewUsuario.setImage(imguse);
        Image imgL = new Image("/imagem/lixeira.png");
        imgLixeira.setImage(imgL);
        
        

        if (UsuarioController.userLogado.getTipoUsuario().equals(UsuarioController.TIPO_ADMIN)) {
            botaoRelatorio.setVisible(true);
            imgRelatorio.setVisible(true);
            idCadastro.setVisible(true);
            viewUsuario.setVisible(true);
            labelRedefineSenha.setVisible(false);
            btnLixeira.setVisible(true);
            imgLixeira.setVisible(true);
            
        }

         exect.run();
       exectTaOP.run();
    }

    @FXML
    protected void botaoCliente() {

        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaCliente.fxml"));
            root = fxml.load();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root, 1800, 700);

        stage.setScene(scene);

        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

        botaoCliente.getScene().getWindow().hide();

    }

    public static Stage retornaStage() {
        return stage;
    }

    @FXML
    protected void botaoEmprestimo() {
        stages = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaEmprestimo.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stages.setScene(scene);
        stages.show();

        botaoEmprestimo.getScene().getWindow().hide();

    }

    @FXML
    protected void idCadastro() {

        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaUsuario.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);
    }

    public static Stage retornaCenaEmprestimo() {
        return stage;
    }

    public void fechaTelaPrincipal() {
        UsuarioController.retornaStage().close();
    }

    public static Stage fechaCliente() {
        return stage;
    }

    public static Stage fechaEmprestimo() {
        return stages;
    }

    @FXML
    public void abreTelaNovaSenha() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaAlterarSenha.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Redefinir Senha");
        stage.setResizable(false);

    }

    @FXML
    public void reiniciar() {

        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/login.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TELA LOGIN");
        fechaTelaPrincipal();
        fechaEmprestimo().close();
       // retornaStage();
        voltarLogin.getScene().getWindow().hide();
    }

    @FXML
    public void chamaTelaOp() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaOrdemDePagamento.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

    }

    @FXML
    public void chamaTelaPainel() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaPainelNotificacao.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

    }

    public void TelaRelatorio() {
        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaRelatorio.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

    }
    public void TelaDadosRemovidos() {
        stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaAtivarDados.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.resizableProperty();
        stage.setResizable(false);

    }

    @FXML
    private void abreRelatorio(ActionEvent event) {
    }

    @FXML
    public void geraPdf() {
        Document doc = new Document();
        try {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/Public/emprestimo.pdf/"));
                doc.open();
                Paragraph p = new Paragraph();
                p.add("RELATÓRIO DE EMPRÉSTIMOS");
                p.setAlignment(1);
                doc.add(p);
                List<Emprestimo> emprestimo = emprestimoService.emprestimos();
                for (int x = 0; x < emprestimo.size(); x++) {
                    doc.add(new Paragraph("\n"));
                    doc.add(new Paragraph("\n"));
                    doc.add(new Paragraph("\n"));
                    doc.add(new Paragraph("CODIGO DO EMPRÉSTIMO: " + emprestimo.get(x).getId_Emprestimo().toString()));
                    doc.add(new Paragraph("NOME CLIENTE: " + emprestimo.get(x).getCliente().getNome()));
                    doc.add(new Paragraph("CPF CLIENTE: " + emprestimo.get(x).getCliente().getCpf()));
                    doc.add(new Paragraph("BANCO: " + emprestimo.get(x).getBanco()));
                    doc.add(new Paragraph("VALOR COMISSÃO R$: " + emprestimo.get(x).getValorComissao()));
                    doc.add(new Paragraph("TIPO CONTRATO: " + emprestimo.get(x).getFormaContrato()));
                    doc.add(new Paragraph("NUMERO CONTRATO: " + emprestimo.get(x).getNumeroContrato()));
                    doc.add(new Paragraph("DATA DE CADASTRO: " + emprestimo.get(x).getDataInicio().format(formatter)));
                    doc.add(new Paragraph("CADASTRO EFETUADO POR : " + emprestimo.get(x).getLogin().getUsuario()));
                    doc.add(new Paragraph("USUÁRIO COM A PERMISSÃO DE : " + emprestimo.get(x).getLogin().getTipoUsuario()));
                }
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("\n"));
                
                Double valorTotal = emprestimoService.somaComissao();
                doc.add(new Paragraph("Valor Total Comissão R$: " + EmprestimoController.converteMoeda(valorTotal)));
                doc.close();
            } catch (DocumentException ex) {
                System.out.println("ERROR" + ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR" + ex);
        }
        try {
            Process p = Runtime.getRuntime().exec("cmd.exe /C c:/Users/Public/emprestimo.pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
