package br.com.queenfitstyle.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super("Token inválido ou expirado!");
    }
}
