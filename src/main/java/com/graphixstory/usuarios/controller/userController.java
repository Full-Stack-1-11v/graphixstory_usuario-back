package com.graphixstory.usuarios.controller;

import com.graphixstory.usuarios.model.Usuario;
import com.graphixstory.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;



@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Api encargada de la gestion de usuarios")
public class userController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "obtiene todos los usuarios de registrados", description="entrega una lista de todos los usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll(); 
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(usuarios); 
    }
    
    @PostMapping
    @Operation(summary = "")
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario usuario2 = usuarioService.guardarUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario2);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario user = usuarioService.findById(id);
            user.setId(id);
            user.setRun(user.getRun());
            user.setNombre(user.getNombre());
            user.setApellido(user.getApellido());
            user.setFechaNacimiento(user.getFechaNacimiento());
            user.setCorreo(user.getCorreo());
            user.setTipoUser(user.getTipoUser());
            
            usuarioService.guardarUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.borrarUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/tipo") 
    public ResponseEntity<List<Usuario>> getUsuariosByTipoUser(@RequestParam String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        List<Usuario> usuarios = usuarioService.findByTipoUser(tipo);
        if (usuarios.isEmpty()) {
           
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK); 
    }


}
