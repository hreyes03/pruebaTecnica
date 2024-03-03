package com.quipux.pruebaTecnica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaReproduccion {
    @Id
    private String nombre;
    private String descripcion;
    @ManyToOne(optional = false)
    private Canciones canciones;
}
