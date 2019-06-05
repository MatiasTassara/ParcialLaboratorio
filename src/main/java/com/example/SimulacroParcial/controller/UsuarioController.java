package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Publicacion;
import com.example.SimulacroParcial.model.Usuario;
import com.example.SimulacroParcial.service.PublicacionRepository;
import com.example.SimulacroParcial.service.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @PostMapping("")
    public void add(@RequestBody Usuario u, @RequestHeader(value="User-Agent") String request){
        u.setBrowser(request);
        usuarioRepository.save(u);
    }


    @GetMapping("")
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario get(@PathVariable ("id") Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));
        return usuario;
    }
    @PutMapping("/{id}")
    public void modify(@RequestBody Usuario u, @PathVariable("id") Integer id){
        u.setId(id);
        usuarioRepository.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));
        usuarioRepository.deleteById(usuario.getId());
    }

    @GetMapping("/user-agent")
    public String getHeadersInfo(@RequestHeader(value="User-Agent") String request) {
        return request;
    }
}
