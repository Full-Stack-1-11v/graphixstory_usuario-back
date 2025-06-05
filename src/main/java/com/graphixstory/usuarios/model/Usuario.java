package com.graphixstory.usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.validation.constraints.*;

@Entity
@Table (name= "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUT no puede estar vacio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[kK\\d]$",message = "El formato de RUT es invalido. Ej= 12.345.678-K")
    @Column(unique= true, length = 13, nullable=false)
    private String run;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = true)
    private Date FechaNacimiento;

    @Email(message = "el formato del correo electronico es invalido")
    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String tipoUser;

    @Column(nullable = true)
    private String descripUser;

}


