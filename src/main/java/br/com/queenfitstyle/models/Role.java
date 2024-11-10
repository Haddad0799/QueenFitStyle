package br.com.queenfitstyle.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENTE,
    ROLE_ADMIN
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
