package br.com.queenfitstyle.exceptions;

public class FalhaAoGerarTokenException extends RuntimeException{
    public FalhaAoGerarTokenException() {
        super("Falha ao gerar o token.");
    }
}
