package com.quipux.pruebaTecnica.entity.DTO;

import java.util.List;

public record Categorie(String href, int limit, String next, int offset, String previous, String total, List<Item> items) {

    public Categorie() {
        this(null, 0, null, 0, null, null, null);
    }
}
