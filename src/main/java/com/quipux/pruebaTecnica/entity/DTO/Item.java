package com.quipux.pruebaTecnica.entity.DTO;

import java.util.List;

public record Item(String href, List<Icon> icons, String id, String name) {
}
