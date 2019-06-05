package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
}
