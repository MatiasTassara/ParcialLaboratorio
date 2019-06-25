package com.example.SimulacroParcial.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PublicacionDTO {
    @Id
    private Integer id;
    private String publicacion;
    private String nombreDueno;
    private Integer cantidadComentarios;

    public PublicacionDTO(){}

    public PublicacionDTO(Integer id, String publicacion, String nombreDueno, Integer cantidadComentarios) {
        this.id = id;
        this.publicacion = publicacion;
        this.nombreDueno = nombreDueno;
        this.cantidadComentarios = cantidadComentarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public Integer getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(Integer cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }
}
