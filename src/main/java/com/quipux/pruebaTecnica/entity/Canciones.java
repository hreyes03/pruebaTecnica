package com.quipux.pruebaTecnica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canciones {
    @Id
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
}
