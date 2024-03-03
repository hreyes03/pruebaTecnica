package com.quipux.pruebaTecnica.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quipux.pruebaTecnica.entity.DTO.Categorie;
import com.quipux.pruebaTecnica.entity.DTO.Item;
import okhttp3.*;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpotifyService {

    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String CATEGORIES_URL = "https://api.spotify.com/v1/browse/categories";
    private static final String CLIENT_ID = "dcf92d9d16c949b198348c81f0d27d2e";
    private static final String CLIENT_SECRET = "a646cca18f614af994e081bebae99c3f";

    private ModelMapper mapper = new ModelMapper();

    public static String getToken() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();

        Request request = new Request.Builder()
                .url(TOKEN_URL)
                .post(body)
                .addHeader("Authorization", Credentials.basic(CLIENT_ID, CLIENT_SECRET))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            return jsonObject.getString("access_token");
        }
    }



    public List<Item> getCategories(String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url(CATEGORIES_URL)
                .get()
                .addHeader("Authorization", "Bearer " + token)
                .build();
        List<Item> items = new ArrayList<>();
        try (Response response = client.newCall(request).execute()) {
            JSONObject jsonObject = new JSONObject(response.body().string());
            String jsonResponse = jsonObject.get("categories").toString();
            Categorie categorie = objectMapper.readValue(jsonResponse, Categorie.class);

            System.out.println("categorie --->"+categorie);
            items = categorie.items();
        }
        return items;
    }
}
