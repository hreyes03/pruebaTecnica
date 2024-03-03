package com.quipux.pruebaTecnica.controller;

import com.quipux.pruebaTecnica.entity.DTO.ListaReproduccionDTO;
import com.quipux.pruebaTecnica.entity.ListaReproduccion;
import com.quipux.pruebaTecnica.service.ListaReproduccionesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("lists")
public class ListaCancionesController {

    @Autowired
    private ListaReproduccionesService listaReproduccionesService;

    @PostMapping()
    public ResponseEntity<ListaReproduccionDTO> guardarListaReproducciones(@RequestBody ListaReproduccionDTO listaReproduccionDTO){
        ListaReproduccionDTO listaReproduccionCreada = listaReproduccionesService.guardarListaReproducciones(listaReproduccionDTO);
        if(listaReproduccionCreada == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(listaReproduccionCreada, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ListaReproduccion>> ListarListasReproducciones(){
        return new ResponseEntity<>(listaReproduccionesService.ListarListasReproducciones(), HttpStatus.OK);
    }

    @GetMapping("/{listsName}")
    public ResponseEntity<String> buscarListaReproduccionPorNombre(@PathVariable("listsName") String nombre){
        Optional<ListaReproduccion> listaReproduccion = listaReproduccionesService.buscarListaReproduccionPorNombre(nombre);
        return listaReproduccion.map(reproduccion -> new ResponseEntity<>(reproduccion.getDescripcion(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{listsName}")
    public ResponseEntity eliminarListaReproduccionPorNombre(@PathVariable("listsName") String nombre){
        if(listaReproduccionesService.eliminarListaReproduccionPorNombre(nombre))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
