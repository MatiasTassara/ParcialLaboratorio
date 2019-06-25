package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
}
