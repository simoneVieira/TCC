/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.service;

import com.bettercoding.jfx.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bettercoding.jfx.repository.UsuarioRepository;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository logRepository;

    public Usuario buscaUsuarioESenha(String usuario, String senha) {
        return logRepository.findByUsuarioAndSenha(usuario, senha);
    }

    public List<Usuario> user() {
        return logRepository.findAll();
    }

    public Usuario salvaUsuario(Usuario usu) {
        return logRepository.save(usu);
    }
    public Usuario buscaPermisao(String email){
        return logRepository.findByEmail(email);
    }
}
