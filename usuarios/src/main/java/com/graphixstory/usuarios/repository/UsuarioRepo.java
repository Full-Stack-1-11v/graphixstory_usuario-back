package com.graphixstory.usuarios.repository;

import com.graphixstory.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long >{

    List<Usuario> findByApellido(String apellido);

    List<Usuario> findByNombreAndApellidoList(String nombre, String apellido);

    List<Usuario> findByTipoUser(String tipoUser);

    List<Usuario> findById(Integer id);
}
