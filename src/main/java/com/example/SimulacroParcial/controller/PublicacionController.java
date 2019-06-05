package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Publicacion;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.service.PublicacionRepository;
import com.example.SimulacroParcial.service.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}")
    public void add(@RequestBody Publicacion p, @PathVariable Integer id){
        Usuario usu = usuarioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Para publicar debe existir el usuario"));
        if(!isNull(usu)) {
            p.setFecha(LocalDateTime.now());
            publicacionRepository.save(p);
            usu.addPublicacion(p);
            usuarioRepository.save(usu);
        }
    }

    @GetMapping("")
    public List<Publicacion> getAll(){
        return publicacionRepository.findAll();
    }

}
