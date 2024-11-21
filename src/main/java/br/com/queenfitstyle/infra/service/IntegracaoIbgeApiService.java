package br.com.queenfitstyle.infra.service;

import br.com.queenfitstyle.models.DadoEstadoIbge;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class IntegracaoIbgeApiService {

    public <T> List<T> buscarDados(Class<DadoEstadoIbge[]> tipoDeElemento, String endpoint) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Criando um CollectionType com TypeFactory para List<T>
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, tipoDeElemento);

            // Deserializa o JSON diretamente em uma List<T>
            return objectMapper.readValue(response.body(), listType);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao buscar dados da API: " + e.getMessage());
        }
    }

}
