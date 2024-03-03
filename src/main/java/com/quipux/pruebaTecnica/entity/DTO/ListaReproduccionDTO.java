package com.quipux.pruebaTecnica.entity.DTO;

import com.quipux.pruebaTecnica.entity.Canciones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaReproduccionDTO {

    private String nombre;
    private String descripcion;
    private List<Canciones> canciones;
}
