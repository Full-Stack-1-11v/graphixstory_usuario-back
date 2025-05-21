package com.graphixstory.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphixstory.usuarios.repository.UsuarioRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Usuario> findAll() {
        return usuarioRepo.findAll();

    }

}
