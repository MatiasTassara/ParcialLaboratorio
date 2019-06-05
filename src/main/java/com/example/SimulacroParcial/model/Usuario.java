package com.example.SimulacroParcial.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String browser;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Publicacion> publicaciones;

    public Usuario(){}

    public Usuario(String nombre, String browser, List<Publicacion> publicaciones) {
        this.nombre = nombre;
        this.browser = browser;
        this.publicaciones = publicaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void addPublicacion(Publicacion p){
        publicaciones.add(p);
    }
}
