package br.com.queenfitstyle.dtos;

public record JwtDto(
        String token,
        String tipo
) {

    public JwtDto(String token){
        this(token, "bearer");
    }
}
