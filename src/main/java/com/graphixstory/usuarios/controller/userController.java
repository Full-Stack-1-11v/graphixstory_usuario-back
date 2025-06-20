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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


/**
 * Controlador REST para gestionar las operaciones relacionadas con los 
 * usuarios.
 * Proporciona endpoints para listar.
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Api encargada de la gestion de usuarios")
public class userController {

    /**
     * Logger de la clase para registrar eventos y errores.
     */
    private static final  Logger logger = LoggerFactory.getLogger(userController.class);

    /**
     * Servicio para gestionar las operaciones relacionadas con los Usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;
    
     /**
     * Obtiene una lista de todos los usuarios.
     */
    @GetMapping
    @Operation(summary = "obtiene todos los usuarios de registrados", description="entrega una lista de todos los usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll(); 
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(usuarios); 
    }
   /**
     * Actualiza completamente la información de un paciente existente.
     * 
     * @param id       ID del paciente a actualizar.
     * @param paciente Objeto {@link Paciente} con la nueva información.
     * @return Objeto {@link Paciente} actualizado.
     */
    @PostMapping
    @Operation(summary = "Guardar usuario", 
    description = "almacena todos los datos correspondientes al usuario")
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario usuario2 = usuarioService.guardarUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario2);
    }

   /**
     * Obtiene un usuario por su ID.
     * 
     * @param id ID del Usuario a buscar.
     * @return Objeto {@link Usuario} correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "obtiene un usuario", description = " Su filtro de busqueda es por {id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualiza parcialmente la información de un usuario existente.
     * 
     * @param id       ID del usuario a actualizar.
     * @param usuario Objeto {@link Usuario} con la información parcial a
     *                 actualizar.
     * @return Objeto {@link Usuario} actualizado parcialmente.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un usuario", description = "Se solicita la id para ejecucion")
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

    /**
     * Elimina un usuario del sistema por su ID.
     * 
     * @param id ID del usuario a eliminar.
     * @return Respuesta con código de estado HTTP.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario", description = "Filtro por ID")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.borrarUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

     /**
     * Obtiene una lista que filtra por tipo de usuario.
     * 
     * @param TipoUser Categoria de los tipos de usuarios 
     * @return entrega una lista con la categoria entregada
     */
    @GetMapping("/tipo")
    @Operation(summary = "Obtener usuarios por categoria", description = "Entrega una lista de todos los usuarios que se encuentran en la misma categoria") 
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
