package br.com.queenfitstyle.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Token inválido ou expirado!");
    }
}
