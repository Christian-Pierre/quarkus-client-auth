package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenService {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
