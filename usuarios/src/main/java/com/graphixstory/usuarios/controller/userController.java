package com.graphixstory.usuarios.controller;

import com.graphixstory.usuarios.model.Usuario;
import com.graphixstory.usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/usuarios")

public class userController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll(); 
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(usuarios); 
    }
    

}
