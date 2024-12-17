package br.com.queenfitstyle.infra.util;

import org.springframework.stereotype.Component;

@Component
public class EndpointBuilder {

    public static String buildCepSearchEndpoint(String cep) {
        String urlBase = "https://viacep.com.br/ws/";
        String dataFormat = "/json";
        return urlBase + cep + dataFormat;
    }
}
