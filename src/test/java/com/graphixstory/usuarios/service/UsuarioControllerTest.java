package com.graphixstory.usuarios.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.graphixstory.usuarios.controller.userController;
import com.graphixstory.usuarios.model.Usuario;

import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(userController.class)
public class UsuarioControllerTest {

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



}
