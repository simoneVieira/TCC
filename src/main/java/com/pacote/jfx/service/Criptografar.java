/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.service;

import com.pacote.jfx.model.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class Criptografar {

    public static String criptoSenha(String senha) {
        String retorno = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            retorno = hash.toString(16);

        } catch (Exception e) {
        }
        return retorno;
    }
}
