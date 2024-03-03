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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ListaReproduccionesServiceImpl implements ListaReproduccionesService {

    @Autowired
    private ListaReproduccionesRepository listaReproduccionesRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ListaReproduccionDTO guardarListaReproducciones(ListaReproduccionDTO listaReproduccion) {
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


    public void generosSpotify() {
        String scope = "playlist-read-private";
        String token = "dcf92d9d16c949b198348c81f0d27d2e:a646cca18f614af994e081bebae99c3f";
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/recommendations/available-genre-seeds"))
                .header("Authorization", "Bearer " + token)
                .header("Scope", scope)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                String responseBody = response.body();
                String[] genresArray = responseBody.replaceAll("\"", "").replaceAll("\\s+", "").split(",");
//                return Arrays.asList(genresArray);
                System.out.println("Respuesta de la API: " + responseBody);
            } else {
                System.out.println("Error al consumir la API. Código de estado: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
