package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.clients.UserApiClient;
import org.acme.dto.UserRecordDto;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;

@ApplicationScoped
public class UserService {
    public UserRecordDto getUsers() {
        UserApiClient userApiClient = RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8080"))
                .register(org.acme.filters.BearerTokenFilter.class) // Registro do filtro
                .build(UserApiClient.class);

        return userApiClient.users();
    }
}
