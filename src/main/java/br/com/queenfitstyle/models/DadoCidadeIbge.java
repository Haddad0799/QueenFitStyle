package br.com.queenfitstyle.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadoCidadeIbge(

        @JsonAlias("nome") String nome,
                           String nomeEstado

) {
}
