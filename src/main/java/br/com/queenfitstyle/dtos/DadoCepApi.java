package br.com.queenfitstyle.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoCepApi(

                String cep,
                String logradouro,
                String complemento,
                String bairro,
                String localidade,
                String uf,
                String estado,
                String regiao

) {
}
