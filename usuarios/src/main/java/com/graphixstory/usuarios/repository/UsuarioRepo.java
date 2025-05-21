package com.graphixstory.usuarios.repository;

import com.graphixstory.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseruarioInterface extends JpaRepository<Usuario, Long >{


}
