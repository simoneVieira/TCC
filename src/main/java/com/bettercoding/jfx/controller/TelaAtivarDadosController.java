/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.service.ClienteService;
import com.bettercoding.jfx.service.EmprestimoService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaAtivarDadosController implements Initializable {

    @FXML
    private TableView<Cliente> tabela;

    @FXML
    private TableColumn<Cliente, Long> colunaCodigo;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaCPF;

    @FXML
    private TableColumn<Cliente, Date> dataRemocao;
    @FXML
    private ImageView imgrelatorio;
   

    @FXML
    private TableView<Emprestimo> tabelaEMP;

    @FXML
    private TableColumn<Emprestimo, Long> colunaIdEmprestimo;

    @FXML
    private TableColumn<Emprestimo, Cliente> colunaCliente;
    @FXML
    private TableColumn<Emprestimo, Cliente> colunaCPFcli;

    @FXML
    private TableColumn<Emprestimo, String> colunaContrato;

    @FXML
    private TableColumn<Emprestimo, String> colunaBanco;

    @FXML
    private TableColumn<Emprestimo, Date> colunaNumero;

    @FXML
    private Button idRell;

    @FXML
    private Button butaoAtivar;

    @Autowired
    ClienteService clienteService;
    Cliente c;
    @Autowired
    EmprestimoService emprestimoService;
    Emprestimo e;

    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image imgRl = new Image("/imagem/relatorio.png");
        imgrelatorio.setImage(imgRl);
        initTable();
        listarClientes();
        initTableE();
        listarEmprestimos();
//        tabela.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> selecionarItemTableViewcli(newValue));
    }

    public void initTable() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        dataRemocao.setCellValueFactory(new PropertyValueFactory("removedAt"));

    }
    public ObservableList<Cliente> atualizaTabela = FXCollections.observableArrayList();

    public void listarClientes() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Cliente cliente : clienteService.buscaDadosRemovidos()) {
            atualizaTabela.add(cliente);
        }
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        dataRemocao.setCellValueFactory(new PropertyValueFactory("removedAt"));
        tabela.setItems(atualizaTabela);
    }

    @FXML
    public void ativaDados() {
//        String emissao = null;
//        try {
//
//            c.setRemovedAt(sde.parse(emissao));
//            c = clienteService.salvarCli(c);
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("CONFIRMAÇÃO");
//            alert.setHeaderText("Dados Ativados com sucesso");
//            alert.show();
//
//        } catch (ParseException ex) {
//            System.out.println("error" + ex);
//        }

    }

//    public void selecionarItemTableViewcli(Cliente cliente) {
//        fieldNome.setText(cliente.getNome());
//        fieldRemocao.setText(sd.format(cliente.getRemovedAt()));
//        fieldCodigo.setText("" + cliente.getId());
//        fieldCpf.setText(cliente.getCpf());
//        c = cliente;
//
//    }
    public void initTableE() {
        colunaIdEmprestimo.setCellValueFactory(new PropertyValueFactory("id_Emprestimo"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCPFcli.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaContrato.setCellValueFactory(new PropertyValueFactory("formaContrato"));
        colunaBanco.setCellValueFactory(new PropertyValueFactory("banco"));
        colunaNumero.setCellValueFactory(new PropertyValueFactory(("removedAt")));

    }
    public ObservableList<Emprestimo> atualizaTabel = FXCollections.observableArrayList();

    public SimpleObjectProperty<String> listarEmprestimos() {
        if (!atualizaTabel.isEmpty()) {
            atualizaTabel.clear();

        }

        for (Emprestimo emprestimo : emprestimoService.buscaDadosRemovidosEmp()) {
            atualizaTabel.add(emprestimo);
        }
        colunaIdEmprestimo.setCellValueFactory(new PropertyValueFactory("id_Emprestimo"));
        colunaCliente.setCellValueFactory((TableColumn.CellDataFeatures<Emprestimo, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getCliente().getNome()));
        colunaCPFcli.setCellValueFactory((TableColumn.CellDataFeatures<Emprestimo, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getCliente().getCpf()));
        colunaContrato.setCellValueFactory(new PropertyValueFactory("formaContrato"));
        colunaBanco.setCellValueFactory(new PropertyValueFactory("banco"));
        colunaNumero.setCellValueFactory(new PropertyValueFactory(("removedAt")));
        tabelaEMP.setItems(atualizaTabel);
        return null;
    }

   
    @FXML
    public void geraPdf() {
        Document doc = new Document();
        try {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/Public/emprestimos.pdf/"));
                doc.open();
                Paragraph p = new Paragraph();
                p.add("RELATÓRIO DE EMPRÉSTIMOS CANCELADOS");
                p.setAlignment(1);
                doc.add(p);
                List<Emprestimo> emprestimo = emprestimoService.buscaDadosRemovidosEmp();
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
                    doc.add(new Paragraph("DATA DO CANCELAMENTO: " + sd.format(emprestimo.get(x).getRemovedAt())));

                }
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("\n"));

                doc.close();
            } catch (DocumentException ex) {
                System.out.println("ERROR" + ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR" + ex);
        }
        try {
            Process p = Runtime.getRuntime().exec("cmd.exe /C c:/Users/Public/emprestimos.pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
