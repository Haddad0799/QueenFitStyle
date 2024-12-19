package br.com.queenfitstyle.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroEnderecoDto(

        @NotBlank
        String cep,
        @NotBlank
        String logradouro,
        String complemento,
        @NotBlank
        String bairro,
        Integer numero,
        @NotBlank
        String localidade,
        @NotBlank
        String uf,
        @NotBlank
        String estado

) {
}
