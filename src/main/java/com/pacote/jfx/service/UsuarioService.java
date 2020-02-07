/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.service;

import com.pacote.jfx.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pacote.jfx.repository.UsuarioRepository;

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
    public Usuario buscaId(Long id) {
        return logRepository.findById(id).get();
    }
    public Usuario buscaPorEmail(String email) {
        return logRepository.findByEmail(email);
    }
     public void desativarUsuario(Long id) {
        logRepository.deleteById(id);
    }
      public List<Usuario> buscaPorUsuario(String usuario) {
       return logRepository.findByUsuarioContaining(usuario);
    }
    
    public Usuario buscaSenha(String senha){
     return logRepository.findBySenha(senha);
    }  
     
}
