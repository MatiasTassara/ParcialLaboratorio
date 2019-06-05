package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Comentario;
import com.example.SimulacroParcial.model.Publicacion;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.service.ComentarioRepository;
import com.example.SimulacroParcial.service.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @PostMapping("/{id}")
    public void add(@RequestBody Comentario c, @PathVariable Integer id){
        Publicacion pub = publicacionRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "no hay donde comentar..."));
        if(!isNull(pub)) {
            pub.setFecha(LocalDateTime.now());
            comentarioRepository.save(c);
            pub.addComentario(c);
            publicacionRepository.save(pub);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));
        comentarioRepository.deleteById(comentario.getId());
    }

    @Scheduled(cron = "${minutes}")
    public void deleteComents(@PathVariable ("minutes") Integer minu) {
        List<Comentario> lista = comentarioRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for(Comentario c : lista) {
            Duration duration = Duration.between(now,c.getFecha());
            long diff = Math.abs(duration.toMinutes());

            if(diff >= minu){
                comentarioRepository.deleteById(c.getId());
            }
        }
    }
}
