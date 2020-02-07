/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pacote.jfx.repository;

import com.pacote.jfx.model.Usuario;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yuequan.jpa.soft.delete.repository.SoftDelete;

/**
 *
 * @author SimoneBarbosa
 */
@SoftDelete 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsuarioAndSenha(@Param("nome") String usuario, @Param("senha") String senha);
    
    public Usuario findBySenha(String senha);

    public Usuario findByEmail(String email);
    public List<Usuario>findByUsuarioContaining(String usuario);

}
