package com.example.SimulacroParcial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


public class ClaseDeListasParaFuture {
    private List<Usuario> usuarios;
    private List<Publicacion> publicaciones;
    private List<Comentario> comentarios;

    public ClaseDeListasParaFuture(){}

    public ClaseDeListasParaFuture( List<Usuario> usuarios, List<Publicacion> publicaciones, List<Comentario> comentarios) {

        this.usuarios = usuarios;
        this.publicaciones = publicaciones;
        this.comentarios = comentarios;
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
