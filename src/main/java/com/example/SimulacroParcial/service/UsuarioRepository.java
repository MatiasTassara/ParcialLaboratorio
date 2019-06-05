package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
