package com.graphixstory.usuarios.service;

import com.graphixstory.usuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphixstory.usuarios.repository.UsuarioRepo;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Usuario> findAll() { 
        return usuarioRepo.findAll();
    }
    public Usuario findById(Long id) { 
        return usuarioRepo.findById(id).get();
    }


}
