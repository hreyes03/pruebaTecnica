package com.quipux.pruebaTecnica.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaReproduccion {
    private String nombre;
    private String artista;
    private Canciones canciones;
}
