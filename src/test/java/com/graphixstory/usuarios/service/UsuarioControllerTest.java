package com.graphixstory.usuarios.service;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.graphixstory.usuarios.controller.userController;
import com.graphixstory.usuarios.model.Usuario;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(userController.class)
public class UsuarioControllerTest {

    private ObjectMapper objectMapper; 

    @BeforeEach // Este método se ejecuta antes de cada test
    void setUp() {
        
        objectMapper = new ObjectMapper();
    }


    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private UsuarioService usuarioservice;

    @Test
    void lista_usuarios_debeRetornarListaVacia() throws Exception {

        Mockito.when(usuarioservice.findAll()).thenReturn(Collections.emptyList());

        mockmvc.perform(get("/api/usuarios"))
                .andExpect(status().is(204));

    }

    @Test 
    void obtener_un_usuario() throws Exception {

        Mockito.when(usuarioservice.findById(1)).thenReturn(new Usuario());

        mockmvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk());


    }

    @Test
    void guardar_un_usuario( ) throws Exception {

        Usuario usuario = new Usuario(1,"20.254.325-7","","", null,"fre.Aguallo@gmail.com", "Estudiante", null);

        Mockito.when(usuarioservice.guardarUser(any(Usuario.class))).thenReturn(usuario);

        mockmvc.perform(get("/api/usuarios/1"))
                .andExpect(status().is(200));


    }

    @Test
    void eliminar_un_usuario() throws Exception {

        Usuario usuario = new Usuario(1,"20.254.325-7","","", null,"fre.Aguallo@gmail.com", "Estudiante", null);

        doNothing().when(usuarioservice).borrarUser(1L);

        mockmvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().is(204));

    }

    @Test
    void lista_usuarios_por_tipo() throws Exception {


        Mockito.when(usuarioservice.findByTipoUser("Administrador")).thenReturn(Collections.emptyList());

        mockmvc.perform(get("/api/usuarios/Administrador"))
                .andExpect(status().is(   400));


    }

     @Test
    void testActualizarUsuarioExito() throws Exception {
        
        Integer usuarioId = 1;

        Usuario usuarioExistente = new Usuario(usuarioId,"20.254.325-7","Ana","Sol", null,"fre.Aguallo@gmail.com", "Estudiante", null);

        Usuario usuarioActualizadoRequest = new Usuario(usuarioId,"20.254.325-7","Ana","Sol", null,"fre.Aguallo@gmail.com", "Profesor", null);

        Usuario usuarioRetornadoPorServicio = new Usuario(usuarioId,"20.254.325-7","Ana","Sol", null,"fre.Aguallo@gmail.com", "Profesor", null);


         when(usuarioservice.findById(usuarioId)).thenReturn(usuarioExistente);
        // Cuando se llame a usuarioService.guardarUser con CUALQUIER Usuario, devolver usuarioRetornadoPorService
        when(usuarioservice.guardarUser(any(Usuario.class))).thenReturn(usuarioRetornadoPorServicio);

        // Realizar la petición PUT y verificar las expectativas
        mockmvc.perform(put("/api/usuarios/{id}", usuarioId) // Simula una petición PUT a /api/usuarios/1
                .contentType(MediaType.APPLICATION_JSON) // El tipo de contenido que envías
                .content(objectMapper.writeValueAsString(usuarioActualizadoRequest))) // El cuerpo de la petición en JSON
                .andExpect(status().isOk()) // Espera un código de estado 200 OK
                .andExpect(jsonPath("$.id").value(usuarioId)) // Verifica el ID en el JSON de respuesta
                .andExpect(jsonPath("$.nombre").value("Pedro")) // Verifica el nombre actualizado
                .andExpect(jsonPath("$.apellido").value("Gomez")) // Verifica el apellido actualizado
                .andExpect(jsonPath("$.correo").value("pedro.gomez@example.com")); // Verifica el correo actualizado
    }

}
