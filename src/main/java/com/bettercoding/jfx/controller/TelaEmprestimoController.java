/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.service.EmprestimoService;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import util.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaEmprestimoController implements Initializable, ReceptorCliente {

//    @FXML
//    private MenuButton IDCONVENIO;
//
//    @FXML
//    private MenuButton boxStatus;
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
    private Button buttonSalvar;

    @FXML
    private Button buttonVoltar;

    @FXML
    private Button buttonExcluir;

    @FXML
    private TableView<Emprestimo> tabela;

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
    private TableColumn<Emprestimo, Cliente> colunaCliente;

    @FXML
    private TableColumn<Emprestimo, Cliente> colunaCpf;

    @FXML
    private TableColumn<Emprestimo, String> colunaEmprestimo;

    @FXML
    private TableColumn<Emprestimo, String> colunaBanco;

    @FXML
    private TableColumn<Emprestimo, String> colunaStatus;

    @FXML
    private TableColumn<Emprestimo, String> colunaConvenio;

    @FXML
    private TextField idCliente;
    @FXML
    private ComboBox<String> comboEmprestimos;
    private List<Emprestimo> tipoEmprestimo = new ArrayList<>();
    private ObservableList<Emprestimo> obsEmprestimo;
    private List<Emprestimo> convenioEmprestimo = new ArrayList<>();
    private ObservableList<Emprestimo> observeEmprestimo;
    private List<Emprestimo> statusEmprestimo = new ArrayList<>();
    private ObservableList<Emprestimo> observeEmp;
    private List<Emprestimo> bancoEmprestimo = new ArrayList<>();
    private ObservableList<Emprestimo> observebanco;
    private List<Emprestimo> emprestimoFinanceira = new ArrayList<>();
    private ObservableList<Emprestimo> observeFinanceira;
    @FXML
    private ComboBox<String> comboFinanceira;
    @FXML
    private ComboBox<String> comboBoxConvenio;
    @FXML
    private ComboBox<String> idStatus;
    @FXML
    private ComboBox<String> comboBanco;
    @FXML
    private ImageView viewPesquisa;
    @Autowired
    EmprestimoService emprestimoService;

    Emprestimo emprestimo = new Emprestimo();
    Cliente cliente;
    private SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat df = new DecimalFormat(",000.00");

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
//        carregaBancos();
////        carregaConvenio();
//        carregaStatus();
        carregaEmprestimo();
        comboStatus();
        carregaBancos();
//        carregaFinanceira();
        comboConvenio();
        carregaFinanceira();
        initTable();
        listarEmprestimos();
        //  mascaraNumero(fieldValor);
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (Emprestimo, oldValue, newValue) -> selecionarItemTableViewEmprestimo(newValue));

    }

    @FXML
    public void botaoVoltar() {
        TelaClienteController tcc = new TelaClienteController();
        tcc.abreTelaPrincipal();

    }

    public void chamaTelaCliente() {
        TelaClienteController tc = new TelaClienteController();
        TelaClienteController.setReceptor(this);

        TelaPrincipalController t = new TelaPrincipalController();

        t.botaoCliente();
        tc.inativaButon(true);

    }

    public void initTable() {
        colunaCliente.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaEmprestimo.setCellValueFactory(new PropertyValueFactory("Emprestimo"));
        colunaBanco.setCellValueFactory(new PropertyValueFactory("Banco"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory("Status"));
        colunaConvenio.setCellValueFactory(new PropertyValueFactory("Convenio"));
    }
    public ObservableList<Emprestimo> atualizaTabela = FXCollections.observableArrayList();

    public void listarEmprestimos() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Emprestimo emprestimo : emprestimoService.emprestimos()) {
            atualizaTabela.add(emprestimo);

        }

        colunaCliente.setCellValueFactory((TableColumn.CellDataFeatures<Emprestimo, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getCliente().getNome()));
        colunaCpf.setCellValueFactory((TableColumn.CellDataFeatures<Emprestimo, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getCliente().getCpf()));
        colunaEmprestimo.setCellValueFactory(new PropertyValueFactory<>("formaContrato"));
        colunaBanco.setCellValueFactory(new PropertyValueFactory<>("Banco"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colunaConvenio.setCellValueFactory(new PropertyValueFactory<>("Convenio"));
        tabela.setItems(atualizaTabela);
    }

    @FXML
    public void salvarEmprestimo() {
        emprestimo.setCliente(cliente);

        emprestimo.setValorParcela(Float.parseFloat(fieldValor.getText()));
        emprestimo.setObservacao(idTextArea.getText());
        emprestimo.setMatricula(fieldMatricula.getText());
        emprestimo.setBeneficio(fieldBeneficio.getText());
        emprestimo.setTaxa(Float.parseFloat(idTaxa.getText()));
        try {
            emprestimo.setDataInicio(data.parse(fieldDataInicio.getText()));
            emprestimo.setDataFim(data.parse(fieldDataFinal.getText()));
        } catch (ParseException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("favor ,informar uma data");
            alert.show();
        }

        emprestimo.setValorComissao(Float.parseFloat((idComissão.getText())));
//        emprestimo.setNumeroContrato(fieldNumContrato.getText());
        emprestimo.setPorcentagemComissao(Float.parseFloat(fieldComi.getText()));
        emprestimo.setQuantidadeParcela(Integer.parseInt(idParcelas.getText()));
        emprestimo.setValorLiberado(Double.parseDouble(idValorLiberado.getText()));
        emprestimo.setValorSolicitado(Double.parseDouble(idValorSolicitado.getText()));
        emprestimo.setConvenio("" + comboBoxConvenio.getSelectionModel().getSelectedItem());
        emprestimo.setFormaContrato("" + comboEmprestimos.getSelectionModel().getSelectedItem());
        emprestimo.setStatus("" + idStatus.getSelectionModel().getSelectedItem());
        emprestimo.setFinanceira("" + comboFinanceira.getSelectionModel().getSelectedItem());
        emprestimo.setBanco("" + comboBanco.getSelectionModel().getSelectedItem());

        emprestimo = emprestimoService.salvaEmprestimo(emprestimo);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DADOS SALVOS COM SUCESSO!");
        alert.show();
    }

    @Override
    public void receberCliente(Cliente c) {
        this.cliente = c;
        fieldNomeCliente.setText(c.getNome());
        fieldCpfCli.setText(c.getCpf());

    }

    public void novoEmprestimo() {
        fieldValor.setText("");
        idComissão.setText("");

        emprestimo = new Emprestimo();
    }

    public void carregaEmprestimo() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Novo", "Refinanciamento", "Portabilidade", "Cartao Consignado");
        comboEmprestimos.setItems(itens);
    }

    public void comboConvenio() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("inss", "siape", "estado", "marinha", "saneago", "exército");
        comboBoxConvenio.setItems(itens);
    }

    public void comboStatus() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Andamento", "Pendente", "Pago", "Cancelado");
        idStatus.setItems(itens);
    }

    public void carregaBancos() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Bmg", "Pan", "JV-Itau", "Bgn", "Safra", "Olé", "Mercantil", "Bradesco_Bmc", "Daycoval", "Intermedio,"
                + "Bv", "Banrisul", "Santander");
        comboBanco.setItems(itens);
    }

    public void carregaFinanceira() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Financeira Externa");
        comboFinanceira.setItems(itens);
    }

    public void listarEmprestimo() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        Cliente c = null;

        List<Emprestimo> listaEmprestimo;
        listaEmprestimo = emprestimoService.buscaNome(c);

        if (listaEmprestimo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Não foi possível encontrar um Empréstimo para esse Cliente!");
            alert.show();
            return;
        }

        for (Emprestimo emprestimo : listaEmprestimo) {
            atualizaTabela.add(emprestimo);
        }

        colunaCliente.setCellValueFactory((TableColumn.CellDataFeatures<Emprestimo, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getCliente().getNome()));
        colunaCpf.setCellValueFactory((TableColumn.CellDataFeatures<Emprestimo, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getCliente().getCpf()));
        colunaEmprestimo.setCellValueFactory(new PropertyValueFactory<>("formaContrato"));
        colunaBanco.setCellValueFactory(new PropertyValueFactory<>("Banco"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colunaConvenio.setCellValueFactory(new PropertyValueFactory<>("Convenio"));
        tabela.setItems(atualizaTabela);
    }

    public void selecionarItemTableViewEmprestimo(Emprestimo emprestimo) {
        fieldValor.setText(String.valueOf(emprestimo.getValorParcela()));
        idValorSolicitado.setText(String.valueOf(emprestimo.getValorSolicitado()));
        idValorLiberado.setText(String.valueOf(emprestimo.getValorLiberado()));
        idComissão.setText(String.valueOf(emprestimo.getValorComissao()));
        fieldComi.setText(String.valueOf(emprestimo.getPorcentagemComissao()));
        idTaxa.setText(String.valueOf(emprestimo.getTaxa()));

        fieldNomeCliente.setText(emprestimo.getCliente().getNome());
        fieldDataInicio.setText(data.format(emprestimo.getDataInicio()));
        fieldDataFinal.setText(data.format(emprestimo.getDataFim()));
        comboBoxConvenio.setValue(emprestimo.getConvenio());
        comboEmprestimos.setValue(emprestimo.getFormaContrato());
        idStatus.setValue(emprestimo.getStatus());
        comboBanco.setValue(emprestimo.getBanco());
        comboFinanceira.setValue(emprestimo.getFinanceira());

    }

    @FXML
    private void validaDataInicio() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldDataInicio);
        formata.formatter();

    }
//    @FXML
//     public static void  mascaraNumero(){
//         TextField textField = new  TextField();
//       
//        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//            newValue = newValue.replaceAll(",",".");
//            if(newValue.length()>0){
//                try{
//                    Double.parseDouble(newValue);
//                    textField.setText(newValue.replaceAll(",","."));
//                }catch(Exception e){
//                    textField.setText(oldValue);
//                }
//            }
//        });
//       
//    } 
     @FXML
    public void formata() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("###.##" + " "+ "R$");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldValor);
        formata.formatter();

    }
}
