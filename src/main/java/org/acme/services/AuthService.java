package org.acme.services;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.acme.clients.AuthApiClient;
import org.acme.models.Credentials;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@ApplicationScoped  // Adicione esta anotação para garantir que o AuthService seja um bean CDI
public class AuthService {

    public String authenticate(String username, String password) {
        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);

        // Crie o cliente REST manualmente com a URL configurada
        AuthApiClient authApiClient = RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8080"))
                .build(AuthApiClient.class);

        // Realiza o login
        Response response = authApiClient.login(credentials);

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Failed to authenticate: " + response.getStatus());
        }
    }
}
