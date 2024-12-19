package br.com.queenfitstyle.exceptions;

public class CepNotFoundException extends RuntimeException{

    public CepNotFoundException() {
        super("Cep não encontrado!");
    }
}
