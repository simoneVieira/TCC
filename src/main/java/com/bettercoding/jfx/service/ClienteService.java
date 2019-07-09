/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.service;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCli(Cliente c) {
        return clienteRepository.save(c);
    }

    public List<Cliente> clie() {
        return clienteRepository.findAll();
    }
     public Cliente buscaCli(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
      
     public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

//    public void excluirCliente(Cliente cli) {
//       
//    }

}
