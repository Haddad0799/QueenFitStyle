package br.com.queenfitstyle.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenNotProvidedException extends AuthenticationException {
    public TokenNotProvidedException() {
        super("Nenhum token enviado no cabeçalho authorization!");
    }

}
