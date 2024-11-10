package br.com.queenfitstyle.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthDto(

        @NotNull
        String email,
        @NotNull
        String senha
) {
}
