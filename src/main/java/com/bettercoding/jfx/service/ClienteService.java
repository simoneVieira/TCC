/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettercoding.jfx.service;

import com.bettercoding.jfx.model.Cliente;
import com.bettercoding.jfx.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

     
    
    public Cliente salvarCli(Cliente c) {
        return clienteRepository.save(c);
    }

    public void miau() {
        System.out.println("miau");
    }

}
