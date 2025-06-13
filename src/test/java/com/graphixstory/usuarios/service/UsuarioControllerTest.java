package com.graphixstory.usuarios.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.graphixstory.usuarios.controller.userController;

import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

        mockmvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    

}
