package com.quipux.pruebaTecnica.repository;

import com.quipux.pruebaTecnica.entity.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaReproduccionesRepository extends JpaRepository<ListaReproduccion, String> {
}
