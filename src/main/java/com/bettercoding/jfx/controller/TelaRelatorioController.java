/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.model.Emprestimo;
import com.bettercoding.jfx.service.EmprestimoService;
import com.bettercoding.jfx.service.NotificacaoService;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author SimoneBarbosa
 */
@Controller
public class TelaRelatorioController implements Initializable {

    @FXML
    private BarChart<String, Double> graficoProducao;

    @FXML
    private CategoryAxis categoriaAxis;
    @FXML
    private NumberAxis numeroAxis;

    @FXML
    private Label somaComissao;
    private ObservableList<String> nomeBanco = FXCollections.observableArrayList();
    @Autowired
    EmprestimoService emprestimoService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
      XYChart.Series<String,Double> s = new XYChart.Series<>();
          s.setName("Bancos");
          s.getData().add(new XYChart.Data<>("PAN",15.00));
          s.getData().add(new XYChart.Data<>("BMG",20.00));
          s.getData().add(new XYChart.Data<>("ITAU",15.00));
          s.getData().add(new XYChart.Data<>("BV",4500.00));
          s.getData().add(new XYChart.Data<>("MERCANTIL",50.00));
          s.getData().add(new XYChart.Data<>("SAFRA",100.00));
          graficoProducao.getData().add(s);
      
    }

    
   public ObservableList<Double> valorComissao = FXCollections.observableArrayList();
  
    
}
