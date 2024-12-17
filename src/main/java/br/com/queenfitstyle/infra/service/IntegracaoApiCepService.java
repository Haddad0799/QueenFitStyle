package br.com.queenfitstyle.infra.service;

import br.com.queenfitstyle.dtos.DadoCepApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class IntegracaoApiCepService {

    private final ObjectMapper mapper = new ObjectMapper();

    public DadoCepApi buscarCep (String endpoint) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint)).build();

        HttpResponse<String> response;
        try {
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            return mapper.readValue(response.body(), DadoCepApi.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
