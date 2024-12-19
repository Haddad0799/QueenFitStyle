package br.com.queenfitstyle.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroUsuarioDto(


        @NotNull
        @Email(message = "Email inválido")
        String email,

        @NotNull
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.")
        String senha


) {
}
