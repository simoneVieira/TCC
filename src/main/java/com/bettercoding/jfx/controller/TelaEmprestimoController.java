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
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;
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
    private ComboBox<Emprestimo> comboEmprestimos;
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
    private ComboBox<Emprestimo> comboFinanceira;
    @FXML
    private ComboBox<Emprestimo> comboBoxConvenio;
    @FXML
    private ComboBox<Emprestimo> idStatus;
    @FXML
    private ComboBox<Emprestimo> comboBanco;
    @FXML
    private ImageView viewPesquisa;
    @Autowired
    EmprestimoService emprestimoService;

    Emprestimo emprestimo = new Emprestimo();
    Cliente cliente;

    public TelaEmprestimoController() {

    }

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
        carregaBancos();
        carregaConvenio();
        carregaStatus();
        carregaEmprestimo();
        carregaFinanceira();
        initTable();
        listarEmprestimos();
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

        emprestimo.setValorParcela(fieldValor.getText());
        emprestimo.setObservacao(idTextArea.getText());
        emprestimo.setMatricula(fieldMatricula.getText());
        emprestimo.setBeneficio(fieldBeneficio.getText());
        emprestimo.setTaxa(idTaxa.getText());
        //emprestimo.setDataInicio(fieldDataInicio.getDate);
        //emprestimo.setDataFim(fieldDataFinal.getText());
        emprestimo.setValorComissao(idComissão.getText());
        emprestimo.setNumeroContrato(fieldNumContrato.getText());
        emprestimo.setPorcentagemComissao(fieldComi.getText());
        emprestimo.setQuantidadeParcela(idParcelas.getText());
        emprestimo.setValorLiberado(idValorLiberado.getText());
        emprestimo.setValorSolicitado(idValorSolicitado.getText());
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
        Emprestimo emprestimoNovo = new Emprestimo(1, "NOVO", "", "", "", "");
        Emprestimo emprestimoRefin = new Emprestimo(2, "REFINANCIAMENTO", "", "", "", "");
        Emprestimo emprestimoPortabi = new Emprestimo(3, "PORTABILIDADE", "", "", "", "");
        Emprestimo emprestimoCartao = new Emprestimo(4, "CARTÃO CONSIGNADO", "", "", "", "");
        tipoEmprestimo.add(emprestimoNovo);
        tipoEmprestimo.add(emprestimoRefin);
        tipoEmprestimo.add(emprestimoPortabi);
        tipoEmprestimo.add(emprestimoCartao);
        obsEmprestimo = FXCollections.observableArrayList(tipoEmprestimo);
        comboEmprestimos.setItems(obsEmprestimo);
        
    }

    public void carregaConvenio() {
        Emprestimo inss = new Emprestimo(5, "INSS", "", "", "", "");
        Emprestimo siape = new Emprestimo(5, "SIAPE", "", "", "", "");
        Emprestimo estado = new Emprestimo(5, "GOVERNO", "", "", "", "");
        Emprestimo marinha = new Emprestimo(5, "MARINHA", "", "", "", "");
        Emprestimo saneago = new Emprestimo(5, "SANEAGO", "", "", "", "");
        convenioEmprestimo.add(inss);
        convenioEmprestimo.add(siape);
        convenioEmprestimo.add(estado);
        convenioEmprestimo.add(marinha);
        convenioEmprestimo.add(saneago);
        observeEmprestimo = FXCollections.observableArrayList(convenioEmprestimo);
        comboBoxConvenio.setItems(observeEmprestimo);
    }

    public void carregaStatus() {
        Emprestimo pendente = new Emprestimo(5, "PENDENTE", "", "", "", "");
        Emprestimo cancelado = new Emprestimo(5, "CANCELADO", "", "", "", "");
        Emprestimo pago = new Emprestimo(5, "PAGO", "", "", "", "");
        statusEmprestimo.add(pendente);
        statusEmprestimo.add(cancelado);
        statusEmprestimo.add(pago);
        observeEmp = FXCollections.observableArrayList(statusEmprestimo);
        idStatus.setItems(observeEmp);

    }

    public void carregaBancos() {
        Emprestimo bmg = new Emprestimo(5, "BMG", "", "", "", "");
        Emprestimo pan = new Emprestimo(5, "PAN", "", "", "", "");
        Emprestimo jvItau = new Emprestimo(5, "JV_ITAU", "", "", "", "");
        Emprestimo bgn = new Emprestimo(5, "BGN", "", "", "", "");
        Emprestimo safra = new Emprestimo(5, "SAFRA", "", "", "", "");
        Emprestimo ole = new Emprestimo(5, "OLE", "", "", "", "");
        Emprestimo mercantil = new Emprestimo(5, "MERCANTIL", "", "", "", "");
        Emprestimo bradescoBmc = new Emprestimo(5, "BRADESCO_BMC", "", "", "", "");
        Emprestimo daycoval = new Emprestimo(5, "DAYCOVAL", "", "", "", "");
        Emprestimo intermedium = new Emprestimo(5, "INTERMEDIUM", "", "", "", "");
        Emprestimo bv = new Emprestimo(5, "BV", "", "", "", "");
        Emprestimo banrisul = new Emprestimo(5, "BANRISUL", "", "", "", "");
        Emprestimo santander = new Emprestimo(5, "SANTANDER", "", "", "", "");
        bancoEmprestimo.add(bmg);
        bancoEmprestimo.add(pan);
        bancoEmprestimo.add(jvItau);
        bancoEmprestimo.add(bgn);
        bancoEmprestimo.add(safra);
        bancoEmprestimo.add(ole);
        bancoEmprestimo.add(mercantil);
        bancoEmprestimo.add(bradescoBmc);
        bancoEmprestimo.add(daycoval);
        bancoEmprestimo.add(intermedium);
        bancoEmprestimo.add(bv);
        bancoEmprestimo.add(banrisul);
        bancoEmprestimo.add(santander);
        observebanco = FXCollections.observableArrayList(bancoEmprestimo);
        comboBanco.setItems(observebanco);

    }

    public void carregaFinanceira() {
        Emprestimo financeira = new Emprestimo(5, "Contrato Vindo De Outra Financeira", "", "", "", "");
        emprestimoFinanceira.add(financeira);
        observeFinanceira = FXCollections.observableArrayList(emprestimoFinanceira);
        comboFinanceira.setItems(observeFinanceira);
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
        fieldValor.setText(emprestimo.getValorParcela());
        fieldNomeCliente.setText(emprestimo.getCliente().getNome());
       
       
        
        comboBanco.getSelectionModel().select(emprestimo);
  
        
        
     

    }
}


