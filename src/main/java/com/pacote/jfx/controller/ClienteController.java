/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import ch.qos.logback.core.CoreConstants;
import com.pacote.jfx.MyApp;
import com.pacote.jfx.model.Cliente;
import com.pacote.jfx.model.Usuario;
import com.pacote.jfx.service.ClienteService;
import com.pacote.jfx.service.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
public class ClienteController implements Initializable {

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
    private TextField primeiraData;

    @FXML
    private TextField segundaData;

    @FXML
    private Button botaoBuscar;
    
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
    private ImageView imageViewAtualiza;
    @FXML
    private Label labeldados;
    @FXML
    private Label labelAte;
    @FXML
    private ImageView viewData;
    @FXML
    private Button idSalvar;
    @FXML
    private Button idbtnCanccelar;
    @FXML
    private Button btPesquisa;
    @FXML
    private Button idAtualizar;
    @FXML
    private CheckBox chekBox;
    @FXML
    private ImageView imageSalvar;
    @FXML
    private TextField idCli;
    @FXML
    private ImageView pesquisaNome;
    @FXML
    private ImageView viewExcluir;
    @FXML
    private ImageView viewLupa;
    @FXML
    private TextField dataCadastro;
    @FXML
    private TextField campoPesquisa;
    @FXML
    private Label idLabelNovo;
    @FXML
    private Button adcionarCli;
    @FXML
    private ImageView viewAddCliente;
    @Value("${my.url}")
    private String myUrl;
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, String> idCollumNome;
    @FXML
    private TableColumn<Cliente, String> idCollumCPF;
    @FXML
    private TableColumn<Cliente, String> idCollumTelefone;
    @FXML
    private TableColumn<Cliente, Date> idCollumNascimento;
    @FXML
    private TableColumn<Cliente, String> idCollumEnd;
    @FXML
    private TableColumn<Cliente, String> idCollumCidade;
    @FXML
    private TableColumn<Cliente, Long> codCLIENTE;
    private ObservableList<Cliente> cliente = FXCollections.observableArrayList();
    @Autowired
    ClienteService clienteService;
    @FXML
    private Label labelAsteriscoN;
    @FXML
    private Label labAsteriscoT;
    @FXML
    private Label labAsteriscoD;
    @FXML
    private Label lbAsteriscoCPF;
    @FXML
    private Label labAstersicoRG;
    @FXML
    private Button idButtonVoltar;
    private static ReceptorCliente receptorCliente;


    @Autowired
    private UsuarioService loginService;
    

    @Autowired
    PrincipalController tp = new PrincipalController();
    @Autowired
    EmprestimoController tec = new EmprestimoController();
    Cliente c = new Cliente();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
  



    public static void setReceptor(ReceptorCliente rc) {
        receptorCliente = rc;

    }

    private ObjectProperty<Cliente> clienteObjProperty = new SimpleObjectProperty();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        adcionarCli.setDisable(receptorCliente == null);

        Image imageVoltar = new Image("/imagem/voltar.png");
        imageViewVolt.setImage(imageVoltar);
        Image image = new Image("/imagem/salvar.png");
        imageSalvar.setImage(image);
        Image imageCancelar = new Image("/imagem/Delete-icon-2_1.png");
        viewExcluir.setImage(imageCancelar);
        Image imagePesquisar = new Image("/imagem/lupa.png");
        viewLupa.setImage(imagePesquisar);
        Image imgadd = new Image("/imagem/cliente.png");
        viewAddCliente.setImage(imgadd);
        Image imga = new Image("/imagem/atualizacao.png");
        imageViewAtualiza.setImage(imga);
        Image imglupa = new Image("/imagem/pesquisa4.png");
        viewData.setImage(imglupa);
        Image imglupaCli = new Image("/imagem/lupa.png");
        pesquisaNome.setImage(imglupaCli);
        initTable();
        listarClientes();

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));

    }

    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
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

    @FXML
    private void validaTelefone2() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("(##)-#####-####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldTelefone2);
        formata.formatter();
        positionCaret(fieldTelefone2);

    }

    @FXML
    private void validaRg() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("#######");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldRG);
        formata.formatter();
        positionCaret(fieldRG);

    }

    @FXML
    private void validaTelefone1() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("(##)-#####-####");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fielTelefone1);
        formata.formatter();
        positionCaret(fielTelefone1);
        

    }

    @FXML
    private void validaCPF() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("###.###.###-##");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldCPF);
        formata.formatter();
         positionCaret(fieldCPF);
    }

    @FXML
    private void validaCep() {
        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("#####-###");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldCep);
        formata.formatter();
        positionCaret(fieldCep);
    }

    @FXML
    private void validaNumero() {

        TextFieldFormatter formata = new TextFieldFormatter();
        formata.setMask("########");
        formata.setCaracteresValidos("0123456789");
        formata.setTf(fieldNumero);
        formata.formatter();
        MaskFormatter mask = new MaskFormatter();
        mask.setValueContainsLiteralCharacters(false);
         positionCaret(fieldNumero);

    }
    
    @FXML
    public void validaDataNascimento() {
        maxField(fieldNasciemento, 10);

        fieldNasciemento.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = fieldNasciemento.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3"
                            + ""
                            + ""
                            + "");
                    fieldNasciemento.setText(value);
                    positionCaret(fieldNasciemento);
                }
            }
        });
    }

    @FXML
    public void validaDataS() {
        maxField(segundaData, 10);

        segundaData.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = segundaData.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
                    segundaData.setText(value);
                    positionCaret(segundaData);
                }
            }
        });
    }

    @FXML
    public void validaDataP() {
        maxField(primeiraData, 10);
        primeiraData.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = primeiraData.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
                    primeiraData.setText(value);
                    positionCaret(primeiraData);
                }
            }
        });
    }

    @FXML
    private void validaDataCadastro() {
        maxField(dataCadastro, 10);

       dataCadastro.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = dataCadastro.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3"
                            + ""
                            + ""
                            + "");
                    dataCadastro.setText(value);
                    positionCaret(dataCadastro);
                }
            }
        });
    }
    

    @FXML
    public void salvarCli() {
               
        if (fieldNome.getText().equals("") || fielTelefone1.getText().equals("")
                || fieldCPF.getText().equals("") || fieldRG.getText().equals("")||fieldTelefone2.getText().equals(" ")||fieldNasciemento.getText().equals(" ")
             || fieldSetor.getText().equals("") ||fieldCidade.getText().equals("") ||fieldCep.getText().equals("")||
                fieldEndereco.getText().equals("")||fieldNumero.getText().equals("")||dataCadastro.getText().equals("")||fieldComplemento.getText().equals(" ")  ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CONFIRMAÇÃO");
            alert.setHeaderText("Favor preencher todos os campos!"
                   );
            alert.show();
        } else {

            Cliente cli = new Cliente();

            cli.setNome(fieldNome.getText());
            cli.setCidade(fieldCidade.getText());
            cli.setCep(Integer.parseInt(fieldCep.getText().replaceAll("[^0-9]", "")));
            cli.setNumero(Integer.parseInt(fieldNumero.getText().replaceAll("[^0-9]", "")));
            cli.setCpf(fieldCPF.getText());

            try {
              
                cli.setDataNascimento(sdf.parse(fieldNasciemento.getText()));
                cli.setDataCadastro(sdf.parse(dataCadastro.getText()));
                 sdf.setLenient(false);
            } catch (ParseException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AVISO");
                alert.setHeaderText("informar uma data válida");
                alert.show();
                
            }
            cli.setComplemento(fieldComplemento.getText());
            cli.setTelefone1(Long.parseLong(fielTelefone1.getText().replaceAll("[^0-9]", "")));
            cli.setTelefone2(Long.parseLong(fieldTelefone2.getText().replaceAll("[^0-9]", "")));
            cli.setSetor(fieldSetor.getText());
            cli.setRg(Integer.parseInt(fieldRG.getText().replaceAll("[^0-9]", "")));
            cli.setEndereco(fieldEndereco.getText());
            if (idCli.getText().equals("")) {
                try {
                    Cliente salvarCli = clienteService.salvarCli(cli);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("SALVO COM SUCESSO!");
                    alert.show();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("AVISO! CPF JÀ CADASTRADO");
                    alert.show();

                }

            } else {
                cli.setId(Long.parseLong(idCli.getText()));
                Cliente salvarCli = clienteService.salvarCli(cli);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("DADOS ALTERADOS COM SUCESSO!");
                alert.show();

            }

        }
    }

    public void initTable() {
        idCollumNome.setCellValueFactory(new PropertyValueFactory("nome"));
        idCollumCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        idCollumTelefone.setCellValueFactory(new PropertyValueFactory("telefone1"));
        idCollumNascimento.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        idCollumEnd.setCellValueFactory(new PropertyValueFactory("endereco"));
        idCollumCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        codCLIENTE.setCellValueFactory(new PropertyValueFactory("id"));

    }

    public ObservableList<Cliente> atualizaTabela = FXCollections.observableArrayList();

    public void listarClientes() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Cliente cliente : clienteService.clie()) {
            atualizaTabela.add(cliente);
        }

        idCollumNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        idCollumCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        idCollumTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone1"));
        idCollumNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        idCollumEnd.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        idCollumCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        codCLIENTE.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabela.setItems(atualizaTabela);
    }

    public void selecionarItemTableViewClientes(Cliente cliente) {
        fieldNome.setText(cliente.getNome());
        fieldCPF.setText(cliente.getCpf());
        fieldRG.setText(String.valueOf(cliente.getRg()));
        fielTelefone1.setText(String.valueOf(cliente.getTelefone1()));
        fieldTelefone2.setText(String.valueOf(cliente.getTelefone2()));
        fieldNasciemento.setText(sdf.format(cliente.getDataNascimento()));
        fieldComplemento.setText(cliente.getComplemento());
        fieldSetor.setText(cliente.getSetor());
        fieldCidade.setText(cliente.getCidade());
        fieldCep.setText(String.valueOf(cliente.getCep()));
        fieldEndereco.setText(cliente.getEndereco());
        fieldNumero.setText(String.valueOf(cliente.getNumero()));
        dataCadastro.setText(sdf.format(cliente.getDataCadastro()));
        idCli.setText(String.valueOf(cliente.getId()));

    }

    @FXML
    public void excluirCliente() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("OK");
        ButtonType btnNaoResponder = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("Ao clicar em OK,"
                + "\n o cliente e todos os seus empréstimos serão excluídos");
        alert.getButtonTypes().setAll(btnSim, btnNaoResponder);
        alert.showAndWait().ifPresent((ButtonType b) -> {
            if (b == btnSim) {
                Cliente cli = new Cliente();
                cli.setId(Long.parseLong(idCli.getText()));
                clienteService.excluirCliente(cli.getId());
                fieldNome.setText("");
                fieldCPF.setText("");
                fieldRG.setText("");
                fielTelefone1.setText("");
                fieldTelefone2.setText("");
                fieldNasciemento.setText("");
                fieldComplemento.setText("");
                fieldSetor.setText("");
                fieldCidade.setText("");
                fieldCep.setText("");
                fieldEndereco.setText("");
                fieldNumero.setText("");
                idCli.setText("");
                dataCadastro.setText("");

                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("INFORMAÇÂO");
                dialogoResultado.setContentText("DADOS DELETADOS COM SUCESSO!");
                dialogoResultado.showAndWait();
            } else {
                alert.close();

            }

        });
    }

    public void LimpaCampos() {
        fieldNome.setText("");
        fieldCPF.setText("");
        fieldRG.setText("");
        fielTelefone1.setText("");
        fieldTelefone2.setText("");
        fieldComplemento.setText("");
        fieldSetor.setText("");
        fieldCidade.setText("");
        fieldCep.setText("");
        fieldEndereco.setText("");
        fieldNumero.setText("");
        idCli.setText("");
        fieldNasciemento.setText(" ");
        dataCadastro.setText(" ");

    }

    public void fechaTelaCliente() {
        PrincipalController.fechaCliente().close();
    }

    @FXML
    public void abreTelaPrincipal() {

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
    }

    @FXML
    public void adcionarCliente() {
        fechaTelaCliente();
        receptorCliente.receberCliente(tabela.getSelectionModel().getSelectedItem());
        receptorCliente = null;

    }

    public void inativaButon(boolean tetes) {
        tetes = true;
        if (tetes == true) {
            adcionarCli.setDisable(true);
        } else {
            adcionarCli.setDisable(false);
        }

    }

    @FXML
    private void botaoP() {

        String x = campoPesquisa.getText();

        try {

            long cpf = Long.parseLong(x);

            listarClientesByCPF();

        } catch (NumberFormatException ignore) {

            listarClientesByNome();

        }

    }

    public void listarClientesByCPF() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        List<Cliente> listaCpfCliente = clienteService.buscaCli("" + campoPesquisa.getText());

        if (listaCpfCliente.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Cliente Não Cadastrado!");
            alert.show();
            return;
        }

        for (Cliente cliente : listaCpfCliente) {
            atualizaTabela.add(cliente);
        }

        idCollumNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        idCollumCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        idCollumTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone1"));
        idCollumNascimento.setCellValueFactory(new PropertyValueFactory<Cliente, Date>("dataNascimento"));
        idCollumEnd.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
        idCollumCidade.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cidade"));
        codCLIENTE.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("id"));
        tabela.setItems(atualizaTabela);
    }

    public void listarClientesByNome() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        List<Cliente> listaNome = clienteService.buscaCliNome("" + campoPesquisa.getText());

        if (listaNome.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Cliente Não Cadastrado!");
            alert.show();
            return;
        }

        for (Cliente cliente : listaNome) {
            atualizaTabela.add(cliente);
        }

        idCollumNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        idCollumCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        idCollumTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone1"));
        idCollumNascimento.setCellValueFactory(new PropertyValueFactory<Cliente, Date>("dataNascimento"));
        idCollumEnd.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
        idCollumCidade.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cidade"));
        codCLIENTE.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("id"));
        tabela.setItems(atualizaTabela);
    }

    public void atualiza() {
        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();

        }

        for (Cliente cliente : clienteService.clie()) {
            atualizaTabela.add(cliente);
        }

    }

    @FXML
    public void buscaClientePorData() {

        if (!atualizaTabela.isEmpty()) {
            atualizaTabela.clear();
            List<Cliente> listaData = null;
            try {
                listaData = clienteService.buscaData(sd.parse(primeiraData.getText()), (sd.parse(segundaData.getText())));

                if (listaData.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("AVISO");
                    alert.setHeaderText("Não foi encontrado cadastro de cliente nessa data!");
                    alert.show();

                    return;
                }

                for (Cliente cliente : listaData) {
                    atualizaTabela.add(cliente);
                }

                idCollumNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
                idCollumCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
                idCollumTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone1"));
                idCollumNascimento.setCellValueFactory(new PropertyValueFactory<Cliente, Date>("dataNascimento"));
                idCollumEnd.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
                idCollumCidade.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cidade"));
                codCLIENTE.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("id"));
                tabela.setItems(atualizaTabela);

            } catch (ParseException ex) {
                System.out.println("ERRORRR!!!" + ex);
            }

        }
    }

    public void mostraCamposDatas() {
        if (chekBox.isSelected()) {
            primeiraData.setVisible(true);
            segundaData.setVisible(true);
            labelAte.setVisible(true);
            botaoBuscar.setVisible(true);
            pesquisaNome.setVisible(true);
        }
    }
}
