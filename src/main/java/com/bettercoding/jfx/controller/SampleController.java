package com.bettercoding.jfx.controller;

import com.bettercoding.jfx.MyApp;
import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.service.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class SampleController {
    
    @Autowired
    ClienteService  clienteService ;
    
   @FXML
    private Button idEntrar;
    
    @FXML
    private Button bt;

   @Value("${my.url}")
   private String myUrl;

    
   @FXML
    public void salvarCli(){
        Cliente c = new Cliente();
       c.setNome("João");
        
        clienteService.salvarCli(c);
        
    }
    
    /**
     *
     * @param e
     */
    @FXML
    protected void entrar(ActionEvent e) {
        MyApp.changescren("cenaLogin");
        System.out.println("chega a qui");
    }
}
