package com.quipux.pruebaTecnica.service;

import com.quipux.pruebaTecnica.entity.DTO.ListaReproduccionDTO;
import com.quipux.pruebaTecnica.entity.ListaReproduccion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ListaReproduccionesService {

    ListaReproduccionDTO guardarListaReproducciones(ListaReproduccionDTO listaReproduccionDTO);

    List<ListaReproduccion> ListarListasReproducciones();

    Optional<ListaReproduccion> buscarListaReproduccionPorNombre(String nombre);


    Boolean eliminarListaReproduccionPorNombre(String nombre);
}
