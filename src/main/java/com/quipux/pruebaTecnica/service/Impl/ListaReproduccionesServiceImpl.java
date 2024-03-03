package com.quipux.pruebaTecnica.service.Impl;

import com.quipux.pruebaTecnica.entity.DTO.ListaReproduccionDTO;
import com.quipux.pruebaTecnica.entity.ListaReproduccion;
import com.quipux.pruebaTecnica.repository.ListaReproduccionesRepository;
import com.quipux.pruebaTecnica.service.ListaReproduccionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class ListaReproduccionesServiceImpl implements ListaReproduccionesService {

    @Autowired
    private ListaReproduccionesRepository listaReproduccionesRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ListaReproduccionDTO guardarListaReproducciones(ListaReproduccionDTO listaReproduccion) {
        Optional<ListaReproduccion> listaReproduccionExistente = listaReproduccionesRepository.findById(listaReproduccion.getNombre());
        if (listaReproduccionExistente.isPresent() || listaReproduccion.getNombre() == null)
            return null;

        return mapper.map(listaReproduccionesRepository.save(mapper.map(listaReproduccion, ListaReproduccion.class)), ListaReproduccionDTO.class);
    }

    @Override
    public List<ListaReproduccion> ListarListasReproducciones() {
        return listaReproduccionesRepository.findAll();
    }

    @Override
    public Optional<ListaReproduccion> buscarListaReproduccionPorNombre(String nombre) {
        return listaReproduccionesRepository.findById(nombre);
    }

    @Override
    public Boolean eliminarListaReproduccionPorNombre(String nombre) {
        Optional<ListaReproduccion> listaReproduccion = listaReproduccionesRepository.findById(nombre);
        if (listaReproduccion.isPresent()) {
            listaReproduccionesRepository.delete(listaReproduccion.get());
            return true;
        }
        return false;
    }



}
