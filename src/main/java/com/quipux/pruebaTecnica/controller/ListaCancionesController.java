package com.quipux.pruebaTecnica.controller;

import com.quipux.pruebaTecnica.entity.DTO.ListaReproduccionDTO;
import com.quipux.pruebaTecnica.entity.ListaReproduccion;
import com.quipux.pruebaTecnica.service.Impl.SpotifyService;
import com.quipux.pruebaTecnica.service.ListaReproduccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("lists")
@CrossOrigin("**")
public class ListaCancionesController {

    @Autowired
    private ListaReproduccionesService listaReproduccionesService;

    @Autowired
    private SpotifyService spotifyService;

    @PostMapping()
        public ResponseEntity<ListaReproduccionDTO> guardarListaReproducciones(@RequestBody ListaReproduccionDTO listaReproduccionDTO){
        if (listaReproduccionDTO.getNombre() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(listaReproduccionesService.guardarListaReproducciones(listaReproduccionDTO));
    }

    @GetMapping()
    public ResponseEntity<List<ListaReproduccion>> ListarListasReproducciones(){
        return new ResponseEntity<>(listaReproduccionesService.ListarListasReproducciones(), HttpStatus.OK);
    }

    @GetMapping("/{listsName}")
    public ResponseEntity<String> buscarListaReproduccionPorNombre(@PathVariable String listsName){
        Optional<ListaReproduccion> listaReproduccion = listaReproduccionesService.buscarListaReproduccionPorNombre(listsName);
        return listaReproduccion.map(reproduccion -> new ResponseEntity<>(reproduccion.getDescripcion(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{listsName}")
    public ResponseEntity eliminarListaReproduccionPorNombre(@PathVariable String listsName){
        if(listaReproduccionesService.eliminarListaReproduccionPorNombre(listsName))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("categoias")
    public ResponseEntity categoriasSpotify() throws IOException {
        String token = SpotifyService.getToken();
        return new ResponseEntity<>(spotifyService.getCategories(token),HttpStatus.OK);
    }
}
