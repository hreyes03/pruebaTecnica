package com.quipux.pruebaTecnica.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canciones {
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
}
