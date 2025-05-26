package com.graphixstory.usuarios.repository;

import com.graphixstory.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long >{

    List<Usuario> findByTipoUser(String tipoUser);

}
