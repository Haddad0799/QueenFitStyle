package br.com.queenfitstyle.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoEstadoIbge(

        @JsonAlias("sigla") String sigla,

        @JsonAlias("nome") String nome

) {
}
