/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.MyApp;
import com.pacote.jfx.model.Cliente;
import com.pacote.jfx.model.Emprestimo;
import com.pacote.jfx.model.Notificacao;
import com.pacote.jfx.model.Usuario;
import com.pacote.jfx.service.EmprestimoService;
import com.pacote.jfx.service.NotificacaoService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import util.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class EmprestimoController implements Initializable, ReceptorCliente {

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
    private Button buttaoOP;

    @FXML
    private TextField fieldBeneficio;
    @FXML
    private TextField idCodigoEmprestimo;

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
    private Label idAtualiza;

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
    private ToggleGroup grupo;
    @FXML
    private ToggleGroup grupoRadio;
    @FXML
    private ComboBox<String> comboNotificacao;

    @FXML
    private TextArea idTextArea;

    @FXML
    private ImageView viewNovo;

    @FXML
    private Button buttonSalvar;
    @FXML
    private Button calcular;

    @FXML
    private Button buttonVoltar;
    @FXML
    private RadioButton radioSim;

    @FXML
    private RadioButton radioNao;
    @FXML
    private Label labelOp;

    @FXML
    private TextField fieldData;
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
    private TextField tf;
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
    Notificacao notificacao;

    @Autowired
    NotificacaoService notificacaoService;
    Usuario user = new Usuario();
    public static final String status_Cancelado = "Cancelado";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    DecimalFormat df = new DecimalFormat(",000.00");
    private static ReceptorEmprestimo receptorEmprestimo;

    public static void setReceptor(ReceptorEmprestimo rc) {
        receptorEmprestimo = rc;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttaoOP.setDisable(receptorEmprestimo == null);
        Image image = new Image("/imagem/salvar.png");
        viewSalvar.setImage(image);
        Image imageVoltar = new Image("/imagem/voltar.png");
        viewVoltar.setImage(imageVoltar);
        Image imageCancelar = new Image("/imagem/Delete-icon-2_1.png");
        viewCancelar.setImage(imageCancelar);
        Image imagePesquisar = new Image("/imagem/pesquisa4.png");
        viewPesquisa.setImage(imagePesquisar);
        Image imageNovo = new Image("/imagem/cliente.png");
        viewNovo.setImage(imageNovo);
        carregaEmprestimo();
        comboStatus();
        carregaBancos();
        comboConvenio();
        comboNotificacao();
        initTable();

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (Emprestimo, oldValue, newValue) -> selecionarItemTableViewEmprestimo(newValue));
        listarEmprestimos();
    }

    @FXML
    public void botaoVoltar() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setControllerFactory(MyApp.springContext::getBean);
            fxml.setLocation(getClass().getResource("/fxml/TelaPrincipal.fxml"));
            root = fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        PrincipalController.retornaStage().close();

        UsuarioController lc = new UsuarioController();
        UsuarioController.retornaStage();
        PrincipalController.fechaEmprestimo().close();
    }

    public void chamaTelaCliente() {

        ClienteController.setReceptor(this);
        PrincipalController t = new PrincipalController();
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

        if (idParcelas.getText().equals("") || fieldMatricula.getText().equals("") || fieldValor.getText().equals("") || idComissão.getText().equals("") || idValorSolicitado.getText().equals("")
                || idValorLiberado.getText().equals("") || fieldComi.getText().equals("") || idTaxa.getText().equals("") || fieldDataInicio.getText().equals("") || fieldDataFinal.getText().equals("")
                || fieldNumContrato.getText().equals("") || fieldBeneficio.getText().equals("") || fieldData.getText().equals("")
                || comboBoxConvenio.getSelectionModel().isEmpty() || comboEmprestimos.getSelectionModel().isEmpty() || idStatus.getSelectionModel().isEmpty() || comboBanco.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("favor preencher todos os campos");
            alert.show();
        } else if (fieldNomeCliente.getText().equals("") || fieldCpfCli.getText().equals(" ")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("favor adicionar um cliente");
            alert.show();

        } else {
            emprestimo.setCliente(cliente);

            emprestimo.setValorParcela(Float.parseFloat(fieldValor.getText().replace(".", "").replace(",", ".").replace("R$", "")));

            emprestimo.setObservacao(idTextArea.getText());

            emprestimo.setMatricula((fieldMatricula.getText().replaceAll("[^0-9]", "").replace("#", "").replace("", "")));
            emprestimo.setBeneficio(fieldBeneficio.getText().replaceAll("[^0-9]", ""));
            emprestimo.setTaxa(Float.parseFloat(idTaxa.getText().replace(".", ".").replace("%", "")));
            try {
                emprestimo.setDataInicio(LocalDate.parse(fieldDataInicio.getText(), formatter));
                emprestimo.setDataFim(LocalDate.parse(fieldDataFinal.getText(), formatter));
            } catch (DateTimeParseException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("favor ,informar uma data");
                alert.show();
            }
            emprestimo.setValorComissao(Float.parseFloat(idComissão.getText().replace(".", ".").replace(",", ".").replace("R$", "")));

            emprestimo.setNumeroContrato((fieldNumContrato.getText().replaceAll("[^0-9]", "")));
            emprestimo.setPorcentagemComissao(Float.parseFloat(fieldComi.getText().replace(".", ".")));
            emprestimo.setQuantidadeParcela(Integer.parseInt(idParcelas.getText().replaceAll("[^0-9]", "")));
            emprestimo.setValorLiberado(Double.parseDouble(idValorLiberado.getText().replace(".", "").replace(",", ".").replace("R$", "")));
            emprestimo.setValorSolicitado(Double.parseDouble(idValorSolicitado.getText().replace(".", "").replace(",", ".").replace("R$", "")));
            emprestimo.setConvenio("" + comboBoxConvenio.getSelectionModel().getSelectedItem());
            emprestimo.setFormaContrato("" + comboEmprestimos.getSelectionModel().getSelectedItem());
            emprestimo.setStatus("" + idStatus.getSelectionModel().getSelectedItem());
            emprestimo.setBanco("" + comboBanco.getSelectionModel().getSelectedItem());
            RadioButton radio = (RadioButton) grupo.getSelectedToggle();
            emprestimo.setFormaPagamento(radio.getText());
            emprestimo.setFormaPagamento(radio.getText());
            RadioButton rad = (RadioButton) grupoRadio.getSelectedToggle();
            emprestimo.setGerarNotificacao(radioSim.getText());
            emprestimo.setLogin(UsuarioController.userLogado);
            if (idCodigoEmprestimo.getText().equals("")) {

                if (radioSim.isSelected() && emprestimo.getNotificacao() == null) {
                    emprestimo.setNotificacao(new Notificacao());
                    emprestimo.getNotificacao().setData(LocalDateTime.parse(fieldData.getText(), formater));
                    emprestimo.getNotificacao().setProximaAlerta(emprestimo.getNotificacao().getData());
                    emprestimo.getNotificacao().setEmprestimo(emprestimo);
                    emprestimo.getNotificacao().setTipoNotificacao("" + comboNotificacao.getSelectionModel().getSelectedItem());

                    emprestimo.getNotificacao().setStatus("Andamento");
                }
                emprestimo = emprestimoService.salvaEmprestimo(emprestimo);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("DADOS SALVOS COM SUCESSO!");
                alert.show();
            } else {

                emprestimo.setId_Emprestimo((Long.parseLong(idCodigoEmprestimo.getText())));
                Emprestimo AlterarEmpres = emprestimoService.salvaEmprestimo(emprestimo);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
                alert.show();
            }

        }

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
        idValorSolicitado.setText("");
        idValorLiberado.setText("");
        fieldComi.setText("");
        idTaxa.setText("");
        fieldNomeCliente.setText("");
        fieldCpfCli.setText("");
        fieldDataInicio.setText("");
        fieldDataFinal.setText("");
        comboBoxConvenio.setValue("");
        comboEmprestimos.setValue("");
        idStatus.setValue("");
        comboBanco.setValue("");
        fieldNumContrato.setText("");
        fieldMatricula.setText("");
        idParcelas.setText("");
        fieldBeneficio.setText("");
        idCodigoEmprestimo.setText("");
        fieldData.setText("");
        idTextArea.setText(" ");
        radioSim.setSelected(false);
        radioNao.setSelected(false);
        radioConta.setSelected(false);
        radioOP.setSelected(false);

        emprestimo = new Emprestimo();

    }

    public void somaComissao() {
        Double valorLiberado;
        Double valorTaxa;
        valorLiberado = Double.parseDouble(idValorLiberado.getText().replace(".", "").replace(",", "."));
        valorTaxa = Double.parseDouble(fieldComi.getText());
        Double soma = ((valorLiberado * valorTaxa) / 100);  
        idComissão.setText(String.valueOf(soma));
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

    public void comboNotificacao() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Refinanciamento", "Contrato Finalizado", "Portabilidade");
        comboNotificacao.setItems(itens);
    }

    public void comboStatus() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Andamento", "Pendente", "Pago", EmprestimoController.status_Cancelado);
        idStatus.setItems(itens);
    }

    public void carregaBancos() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("Bmg", "Pan", "JV-Itau", "Bgn", "Safra", "Olé", "Mercantil", "Bradesco_Bmc", "Daycoval", "Intermedio,"
                + "Bv", "Banrisul", "Santander");
        comboBanco.setItems(itens);
    }

    @FXML
    private void botaoPesquisa() {

        String x = fieldPesquisa.getText();

        try {

            long cpf = Long.parseLong(x);

            listarEmprestimoPorNumero();

        } catch (NumberFormatException ignore) {

            listarClienteEmprestimo();

        }

    }

    public void listarClienteEmprestimo() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }
        List<Emprestimo> listaPeloNomeCli = emprestimoService.buscaEmprestimoObjCli(fieldPesquisa.getText(), fieldPesquisa.getText());

        if (listaPeloNomeCli.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Não foi possível encontrar um Empréstimo para esse Cliente!");
            alert.show();
            return;
        }

        for (Emprestimo emprestimo : listaPeloNomeCli) {
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

    public void listarEmprestimoPorNumero() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }
        List<Emprestimo> listaPeloNomeCli = emprestimoService.buscaNumeroContrato((fieldPesquisa.getText()));
        if (listaPeloNomeCli.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Não foi possível encontrar um Empréstimo para esse Cliente!");
            alert.show();
            return;
        }

        for (Emprestimo emprestimo : listaPeloNomeCli) {
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
        fieldValor.setText(EmprestimoController.converteMoeda(emprestimo.getValorParcela()));
        idValorSolicitado.setText(EmprestimoController.converteMoeda(emprestimo.getValorSolicitado()));
        idValorLiberado.setText(EmprestimoController.converteMoeda(emprestimo.getValorLiberado()));
        idComissão.setText(EmprestimoController.converteMoeda(emprestimo.getValorComissao()));
        fieldComi.setText("" + emprestimo.getPorcentagemComissao());
        idTaxa.setText(String.valueOf(emprestimo.getTaxa()));
        fieldNomeCliente.setText(emprestimo.getCliente().getNome());
        fieldCpfCli.setText(String.valueOf(emprestimo.getCliente().getCpf()));
        fieldDataInicio.setText(formatter.format(emprestimo.getDataInicio()));
        fieldDataFinal.setText(formatter.format(emprestimo.getDataFim()));
        comboBoxConvenio.setValue(emprestimo.getConvenio());
        comboEmprestimos.setValue(emprestimo.getFormaContrato());
        idStatus.setValue(emprestimo.getStatus());
        comboBanco.setValue(emprestimo.getBanco());
        fieldNumContrato.setText(String.valueOf(emprestimo.getNumeroContrato()));
        fieldMatricula.setText(String.valueOf(emprestimo.getMatricula()));
        idParcelas.setText(String.valueOf(emprestimo.getQuantidadeParcela()));
        fieldBeneficio.setText(String.valueOf(emprestimo.getBeneficio()));
        idCodigoEmprestimo.setText(String.valueOf(emprestimo.getId_Emprestimo()));
        fieldData.setText(formatter.format(emprestimo.getNotificacao().getProximaAlerta()));
        idTextArea.setText(emprestimo.getObservacao());
        cliente = emprestimo.getCliente();
        notificacao = emprestimo.getNotificacao();

        if (emprestimo.getFormaPagamento().equals("Conta")) {
            radioConta.setSelected(true);
        } else {
            radioOP.setSelected(true);
        }
        if (emprestimo.getNotificacao() != null) {
            radioSim.setSelected(true);
            fieldData.setText(formater.format(emprestimo.getNotificacao().getProximaAlerta()));
        } else if (emprestimo.getNotificacao().equals("NÃO")) {
            radioNao.setSelected(true);

        }

    }

    private static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > length) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    @FXML
    private void validaDataNotificacao() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/#### ##:##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldData);
        formata.formatter();
        positionCaret(fieldData);

    }

    @FXML
    public void validaDataInicio() {
        maxField(fieldDataInicio, 10);

        fieldDataInicio.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = fieldDataInicio.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3"
                            + ""
                            + ""
                            + "");
                    fieldDataInicio.setText(value);
                    positionCaret(fieldDataInicio);
                }
            }
        });
    }

    @FXML
    public void validaDataFim() {
        maxField(fieldDataFinal, 10);

        fieldDataFinal.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = fieldDataFinal.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3"
                            + ""
                            + ""
                            + "");
                    fieldDataFinal.setText(value);
                    positionCaret(fieldDataFinal);
                }
            }
        });
    }

    public static String converteMoeda(double valor) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String va = currencyFormatter.format(valor);
        return va;
    }

    @FXML
    public void formatas() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("#.##%");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(idTaxa);
        formata.formatter();
        positionCaret(idTaxa);

    }

    @FXML
    public void formataTaxa() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##.#");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldComi);
        formata.formatter();
        positionCaret(fieldComi);

    }

    @FXML
    public void formatQuantidadeParcelas() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(idParcelas);
        formata.formatter();
        positionCaret(idParcelas);
    }

    @FXML
    public void formatMatricula() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##########");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldMatricula);
        formata.formatter();
        positionCaret(fieldMatricula);
    }

    @FXML
    public void formatNumeroB() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldBeneficio);
        formata.formatter();
        positionCaret(fieldBeneficio);
    }

    @FXML
    public void formatNumeroC() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##########");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNumContrato);
        formata.formatter();
        positionCaret(fieldNumContrato);
    }

    public void chamaTelaNotificacao() {
        ExecutaTarefa exect = new ExecutaTarefa();
        exect.run();
    }

    @FXML
    public void excluirEmprestimo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("OK");
        ButtonType btnNaoResponder = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DESEJA REALMENTE APAGAR DADOS? ");
        alert.getButtonTypes().setAll(btnSim, btnNaoResponder);
        alert.showAndWait().ifPresent((ButtonType b) -> {
            if (b == btnSim) {
                Emprestimo emp = new Emprestimo();
                emp.setId_Emprestimo(Long.parseLong(idCodigoEmprestimo.getText()));
                emprestimoService.excluirEmprestimo(emp.getId_Emprestimo());
                fieldValor.setText("");
                idComissão.setText("");
                idValorSolicitado.setText("");
                idValorLiberado.setText("");
                fieldComi.setText("");
                idTaxa.setText("");
                fieldNomeCliente.setText("");
                fieldCpfCli.setText("");
                fieldDataInicio.setText("");
                fieldDataFinal.setText("");
                comboBoxConvenio.setValue("");
                comboEmprestimos.setValue("");
                idStatus.setValue("");
                comboBanco.setValue("");
                fieldNumContrato.setText("");
                fieldMatricula.setText("");
                idParcelas.setText("");
                fieldBeneficio.setText("");
                idCodigoEmprestimo.setText("");
                fieldData.setText("");
                radioSim.setSelected(false);
                radioNao.setSelected(false);
                radioConta.setSelected(false);
                radioOP.setSelected(false);
                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("INFORMAÇÃO");
                dialogoResultado.setContentText("DADOS DELETADOS COM SUCESSO!");
                dialogoResultado.showAndWait();
                alert.show();
            } else {
                alert.close();

            }

        });
    }
    @FXML
    public void formata() {
        fieldValor.setAlignment(Pos.CENTER_RIGHT);
        fieldValor.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            String value = fieldValor.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
            fieldValor.setText(value);
            System.out.println("teste");
            fieldValor.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue1, String newValue1) -> {
                if (newValue1.length() > 17) {
                    fieldValor.setText(oldValue1);
                }
                System.out.println("mudou");
            });
        });
    }

    @FXML
    public void valorSolicitado() {
        idValorSolicitado.setAlignment(Pos.CENTER_RIGHT);
        idValorSolicitado.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = idValorSolicitado.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
                idValorSolicitado.setText(value);
                idValorSolicitado.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.length() > 17) {
                            idValorSolicitado.setText(oldValue);
                        }
                    }
                });
            }
        });
    }

    @FXML
    public void valorLiberado() {
        idValorLiberado.setAlignment(Pos.CENTER_RIGHT);
        idValorLiberado.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = idValorLiberado.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
                idValorLiberado.setText(value);
                idValorLiberado.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.length() > 17) {
                            idValorLiberado.setText(oldValue);
                        }
                    }
                });
            }
        });
    }

    @FXML
    public void valorComissao() {
        idComissão.setAlignment(Pos.CENTER_RIGHT);
        idComissão.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = idComissão.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
                idComissão.setText(value);
                idComissão.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.length() > 17) {
                            idComissão.setText(oldValue);
                        }
                    }
                });
            }
        });
    }

    @FXML
    public void atualizaEmprestimo() {

        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Emprestimo emprestimo : emprestimoService.emprestimos()) {
            atualizaTabela.add(emprestimo);

        }

    }

    @FXML
    public void adcionarEmprestimo() {
        PrincipalController.fechaEmprestimo().close();
        receptorEmprestimo.receberEmprestimo(tabela.getSelectionModel().getSelectedItem());
        receptorEmprestimo = null;

    }

    @FXML
    public void ativaButao() {
        buttaoOP.setVisible(true);

    }

}
