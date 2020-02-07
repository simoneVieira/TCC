/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.controller;

import com.pacote.jfx.service.EmprestimoService;
import java.io.File;
import java.io.Serializable;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author SimoneBarbosa
 */

public class RelatorioControle implements Serializable {
//    @Autowired
//    EmprestimoService emprestimoService;
//  
//  List list = emprestimoService.emprestimos();
//@FXML 
//public void chamaRelatorio(){
//    String caminho = new File("/relatorio/relEmp.jasper").getAbsolutePath();
//    JasperReport relatorio = null;
//        try {
//            relatorio = JasperCompileManager.compileReport(caminho);
//        } catch (JRException ex) {
//           System.out.println("Error"+ ex);
//        }
//    JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(list, false);
//    JasperPrint print = null;
//        try {
//            print = JasperFillManager.fillReport(relatorio, null, dados);
//        } catch (JRException ex) {
//            System.out.println("Error"+ ex);
//        }
//    JasperViewer viw = new JasperViewer(print, false);
//
//    viw.setVisible (
//
//true);

}
