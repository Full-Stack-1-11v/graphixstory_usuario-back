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
    public Usuario findById(long id) { 
        return usuarioRepo.findById(id).get();
    }

    public Usuario guardarUser(Usuario usuario){
        return usuarioRepo.save(usuario);

    }

    public void borrarUser(Long id) {
        usuarioRepo.deleteById(id);
    }
    
    public List<Usuario> findByTipoUser(String tipoUser) {
        return usuarioRepo.findByTipoUser(tipoUser);
    }

}
