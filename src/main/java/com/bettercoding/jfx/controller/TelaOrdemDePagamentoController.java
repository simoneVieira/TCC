/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.model.OrdemPagamento;
import com.bettercoding.jfx.service.EmprestimoService;
import com.bettercoding.jfx.service.OrdemPagamentoService;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import util.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaOrdemDePagamentoController implements Initializable, ReceptorEmprestimo {

    @FXML
    private Pane painelOP;
    @FXML
    private ComboBox<String> comboBancos;
    @FXML

    private TextField idAgencia;
    @FXML
    private TextField UFCidade;
    @FXML
    private TextField fieldPesquisa;

    @FXML
    private Button butonPesquisar;
    @FXML
    private TextField campoCidade;
    @FXML
    private TextField dataSaque;
    @FXML
    private Button buscaEmprestimo;
    @FXML
    private TextField emprestimoId;
    @FXML
    private TextField numeroContrato;
    @FXML
    private ComboBox<String> comboOrdem;
    @FXML
    private TextField codigoOrdemPagamento;
    @FXML
    private ImageView imagemLupaPesquisa;
    @FXML
    private Label ordemNovo;
    @FXML
    private Label ordemAtualizar;
    @FXML
    private Button botaoExcluir;

    @FXML
    private ImageView viewExcluir;

    @FXML
    private ImageView viewEmprestimo;

    @FXML
    private ImageView imageSalvar;

    @FXML
    private Button idSalvar;

    @FXML
    private TableView<OrdemPagamento> tableOp;
    @FXML
    private TableColumn<OrdemPagamento, Long> colunaCodigo;

    @FXML
    private TableColumn<OrdemPagamento, Cliente> colunaNome;

    @FXML
    private TableColumn<OrdemPagamento, Cliente> colunaCpf;

    @FXML
    private TableColumn<OrdemPagamento, String> colunaStatus;

    @FXML
    private TableColumn<OrdemPagamento, Emprestimo> valorOrdem;

    @FXML
    private TableColumn<OrdemPagamento, String> colunaBanco;

    Emprestimo emp;
    OrdemPagamento op = new OrdemPagamento();
    @Autowired
    OrdemPagamentoService ordemService;
    @Autowired
    EmprestimoService emprestimoService;
    public static final String status_Andamento = "Andamento";
    public static final String status_Resolvido = "Resolvido";

    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image imglupa = new Image("/imagem/pesquisa4.png");
        imagemLupaPesquisa.setImage(imglupa);
        Image imglupaemprestimo = new Image("/imagem/pesquisa4.png");
        viewEmprestimo.setImage(imglupaemprestimo);
        Image imgSalvar = new Image("/imagem/salvar.png");
        imageSalvar.setImage(imgSalvar);
        Image imgExcluir = new Image("/imagem/Delete-icon-2_1.png");
        viewExcluir.setImage(imgExcluir);
        initTable();
        comboBancos();
        listarOrdemPagamento();
        comboStatus();
        tableOp.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewOrdemPagamento(newValue));
    }

    public void initTable() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory("status"));
        valorOrdem.setCellValueFactory(new PropertyValueFactory("valorLiberado"));
        colunaBanco.setCellValueFactory(new PropertyValueFactory("banco"));

    }

    public void comboBancos() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll("341-Itau", "237-Bradesco", "104-Caixa", "001-Brasil");
        comboBancos.setItems(itens);
    }

    @FXML
    public void salvarDadosOrdemPagamento() {

        if (numeroContrato.equals("") || campoCidade.getText().equals("") || UFCidade.getText().equals("") || emprestimoId.equals("")
                || comboBancos.getSelectionModel().isEmpty() || comboOrdem.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA");
            alert.setHeaderText("FAVOR PREENCHER TODOS OS CAMPOS!!");
            alert.show();

        } else {
            op.setEmprestimo(emp);
            op.setAgencia(idAgencia.getText());
            op.setDataSaque(LocalDateTime.parse(dataSaque.getText(), formater));
            op.setBanco("" + comboBancos.getSelectionModel().getSelectedItem());
            op.setCidade(campoCidade.getText());
            op.setEstado(UFCidade.getText());
            op.setStatus("" + comboOrdem.getSelectionModel().getSelectedItem());

            if (codigoOrdemPagamento.getText().equals("")) {
                try {
                    op = ordemService.salvaOrdem(op);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("SALVO COM SUCESSO!");
                    alert.show();

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("FAVOR ADICIONAR UM EMPRÉSTIMO!");
                    alert.show();            
                }
                return;
            } else {
                op.setId(Long.parseLong(codigoOrdemPagamento.getText()));
                OrdemPagamento ordp = ordemService.salvaOrdem(op);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
                alert.show();
            }
        }
    }

    @Override
    public void receberEmprestimo(Emprestimo e) {
        this.emp = e;
        numeroContrato.setText(e.getNumeroContrato() + "");
        emprestimoId.setText(e.getId_Emprestimo() + "");

    }

    public void comboStatus() {
        ObservableList<String> itens = FXCollections.observableArrayList();
        itens.addAll(TelaOrdemDePagamentoController.status_Andamento, TelaOrdemDePagamentoController.status_Resolvido);
        comboOrdem.setItems(itens);

    }

    public void chamaTelaEmprestimo() {
        TelaEmprestimoController.setReceptor(this);
        TelaPrincipalController t = new TelaPrincipalController();
        t.botaoEmprestimo();
        idSalvar.setDisable(true);
    }
    public ObservableList<OrdemPagamento> atualizaTabela = FXCollections.observableArrayList();

    public void listarOrdemPagamento() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (OrdemPagamento ordemPagamento : ordemService.buscaOrdemPagamento()) {
            atualizaTabela.add(ordemPagamento);
        }
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory((TableColumn.CellDataFeatures<OrdemPagamento, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getCliente().getNome()));
        colunaCpf.setCellValueFactory((TableColumn.CellDataFeatures<OrdemPagamento, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getCliente().getCpf()));
        colunaStatus.setCellValueFactory(new PropertyValueFactory("status"));
        valorOrdem.setCellValueFactory((TableColumn.CellDataFeatures<OrdemPagamento, Emprestimo> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getValorLiberado()));
        colunaBanco.setCellValueFactory(new PropertyValueFactory("banco"));
        tableOp.setItems(atualizaTabela);
    }

    public void selecionarItemTableViewOrdemPagamento(OrdemPagamento op) {
        idAgencia.setText(op.getAgencia());
        UFCidade.setText(op.getEstado());
        campoCidade.setText(op.getCidade());
        campoCidade.setText(op.getCidade());
        dataSaque.setText(formater.format(op.getDataSaque()));
        numeroContrato.setText(op.getEmprestimo().getNumeroContrato() + "");
        comboOrdem.setValue(op.getStatus());
        comboBancos.setValue(op.getBanco());
        emprestimoId.setText(op.getEmprestimo().getId_Emprestimo() + "");
        codigoOrdemPagamento.setText(op.getId() + "");
        emp = op.getEmprestimo();
    }

    public void listarOPcpf() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        List<OrdemPagamento> listaIdOP = ordemService.buscarPorId(Long.parseLong(fieldPesquisa.getText()));

        if (listaIdOP.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Cliente Não Cadastrado!");
            alert.show();
            return;
        }

        for (OrdemPagamento ordemPagamento : listaIdOP) {
            atualizaTabela.add(ordemPagamento);
        }
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory((TableColumn.CellDataFeatures<OrdemPagamento, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getCliente().getNome()));
        colunaCpf.setCellValueFactory((TableColumn.CellDataFeatures<OrdemPagamento, Cliente> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getCliente().getCpf()));
        colunaStatus.setCellValueFactory(new PropertyValueFactory("status"));
        valorOrdem.setCellValueFactory((TableColumn.CellDataFeatures<OrdemPagamento, Emprestimo> p) -> new ReadOnlyObjectWrapper(p.getValue().getEmprestimo().getValorLiberado()));
        colunaBanco.setCellValueFactory(new PropertyValueFactory("banco"));
        tableOp.setItems(atualizaTabela);
    }

    @FXML
    public void excluirOrdemPagamento() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("OK");
        ButtonType btnNaoResponder = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("DESEJA REALMENTE APAGAR DADOS? ");
        alert.getButtonTypes().setAll(btnSim, btnNaoResponder);
        alert.showAndWait().ifPresent((ButtonType b) -> {
            if (b == btnSim) {
                OrdemPagamento ordP = new OrdemPagamento();
                ordP.setId(Long.parseLong(emprestimoId.getText()));
                ordemService.excluirOrdem(ordP.getId());
                idAgencia.setText(op.getAgencia());
                UFCidade.setText("");
                campoCidade.setText("");
                campoCidade.setText("");
                dataSaque.setText("");
                numeroContrato.setText("");
                comboOrdem.setValue("");
                comboBancos.setValue("");
                emprestimoId.setText("");
                codigoOrdemPagamento.setText("");
                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("INFORMAÇÃO");
                dialogoResultado.setContentText("DADOS DELETADOS COM SUCESSO!");
                dialogoResultado.showAndWait();
            } else {
                alert.close();

            }

        });
    }

    public void limparCampos() {
        UFCidade.setText("");
        campoCidade.setText("");
        campoCidade.setText("");
        dataSaque.setText("");
        numeroContrato.setText("");
        comboOrdem.setValue("");
        comboBancos.setValue("");
        emprestimoId.setText("");
        idAgencia.setText("");
        codigoOrdemPagamento.setText("");
    }

    public void atualizaTabela() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (OrdemPagamento ordemPagamento : ordemService.buscaOrdemPagamento()) {
            atualizaTabela.add(ordemPagamento);
        }
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
    public void formatAgencia() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(idAgencia);
        formata.formatter();
        positionCaret(idAgencia);
    }

    @FXML
    private void validaDataNotificacao() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("##/##/#### ##:##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(dataSaque);
        formata.formatter();
        positionCaret(dataSaque);

    }
}
