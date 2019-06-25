package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.ClaseDeListasParaFuture;
import com.example.SimulacroParcial.model.Comentario;
import com.example.SimulacroParcial.model.Publicacion;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.service.ComentarioRepository;
import com.example.SimulacroParcial.service.PublicacionRepository;
import com.example.SimulacroParcial.service.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/allContent")
public class AsyncController {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PublicacionRepository publicacionRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllContent(){
        CompletableFuture<List<Comentario>> comentFuture = getComents();
        CompletableFuture<List<Usuario>> userFuture = getUsuarios();
        CompletableFuture<List<Publicacion>> publiFuture = getPublicaciones();
        ClaseDeListasParaFuture c = new ClaseDeListasParaFuture();
        c.setUsuarios(userFuture.join());
        c.setComentarios(comentFuture.join());
        c.setPublicaciones(publiFuture.join());
        return ResponseEntity.ok(c);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Comentario>> getComents(){
        List<Comentario> comentarios = comentarioRepository.findAll();
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(comentarios);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(usuarios);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Publicacion>> getPublicaciones(){
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(publicaciones);
    }
}
