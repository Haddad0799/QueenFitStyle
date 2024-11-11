package br.com.queenfitstyle.exceptions;

public class TokenNotProvidedException extends RuntimeException{
    public TokenNotProvidedException() {
        super("Nenhum token enviado no cabeçalho authorization!");
    }
}
