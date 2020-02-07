/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.service;

import com.pacote.jfx.controller.AdminstradorController;
import com.pacote.jfx.model.Usuario;
import javafx.scene.control.Alert;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class EnviaEmailOpService {
    
    public void enviarEmail() {
        String enviarEmail = "emaildetestejavatcc@gmail.com";
        String minhaSenha = "simone123@";
      //  Usuario usuario = usuarioService.buscaPorEmail(fieldEmail.getText());
      String emailCliente = "mariadasilvamany@gmail.com";

//        if (usuario == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("AVISO");
//            alert.setHeaderText("Email não Cadastrado");
//            alert.show();
//            return;
//        }
//        String senha = AdminstradorController.geraNumeroAleatorio();
//        String s = Criptografar.criptoSenha(senha);
//        usuario.setSenha(s);
//        usuario = usuarioService.salvaUsuario(usuario);

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(enviarEmail, minhaSenha));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(enviarEmail);
            email.setSubject("Sua nova senha");
            email.setMsg(" vc tem uma ordem de pagamento para sacar" );
            email.addTo(emailCliente);
            email.send();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Email enviado com sucesso !");
            alert.show();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
