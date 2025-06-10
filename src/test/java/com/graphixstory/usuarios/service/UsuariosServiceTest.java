package com.graphixstory.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.graphixstory.usuarios.model.Usuario;
import com.graphixstory.usuarios.repository.UsuarioRepo;
import com.graphixstory.usuarios.service.UsuarioService;

@SpringBootTest
@ActiveProfiles("test")
public class UsuariosServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepo usuariorepositorio;

    @Test
    public void testGetUsuario() {

        //given
        List<Usuario> Listusuario = null;

        when(usuariorepositorio.findAll()).thenReturn(Listusuario);

        //than
        List<Usuario> usuarios = usuarioService.findAll();

        assertNull(usuarios);
    }

    @Test
    public void testFindById(){
        Integer codigo = 1;
        long id = codigo;
        Usuario usuario = new Usuario (codigo, "20.358.565-5", "Ana", "fio",  null,"ana.fio@gmail.com","Estudiante",null);

        when(usuariorepositorio.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(codigo);

        assertNull(found);
        assertEquals(codigo, found.getId());
    }
    @Test
    public void testSave(){
        Usuario usuario = new Usuario (1,"20.358.565-5", "Ana", "fio",  null,"ana.fio@gmail.com","Estudiante",null);

        when(usuariorepositorio.save(usuario)).thenReturn(usuario);

        Usuario save = usuarioService.guardarUser(usuario);

        assertNotNull(save);
        assertEquals("20.358.565-5", save.getRun());
    }

    @Test
    public void testDeleteByID(){
        Integer codigo = 1;
        long id = codigo;

        doNothing().when(usuariorepositorio).deleteById(id);

       /*  usuarioService.deleteById(id);

        verify(usuariorepositorio, times(1).deleteById(id));*/

    }

    @Test
    public void testGetTipoUSer() {

        List<Usuario> Listusuario = new ArrayList<>();
        Listusuario.add(new Usuario (1, "20.358.565-5", "Ana", "fio",  null,"ana.fio@gmail.com","Estudiante",null));
        Listusuario.add(new Usuario (2, "20.358.445-5", "Ava", "mia",  null,"ana.dao@gmail.com","Estudiante",null));

       /*  when(usuariorepositorio.findByTipoUser("Estudiante").thenReturn(Listusuario));*/

        List<Usuario> usuarios = usuarioService.findByTipoUser("Estudiante");

        assertNull(usuarios);
    }


}
