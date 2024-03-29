package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Integer> {
    String QUERY = "select p.descripcion as nombre, u.nombre as nombreDueno, " +
            "count(c.publicacion_id) as cantidadComentarios " +
            " from usuario u inner join publicacion p on u.id = p.usuario_id " +
            "inner join comentario c on p.id = c.publicacion_id " +
            "group by nombre, nombreDueno";

    @Query(value = QUERY,nativeQuery = true)
    List<FormatoQuery> getFormatoPunto1();

}
