package org.acme.filters;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.acme.services.TokenService;

@Provider
public class BearerTokenFilter implements ClientRequestFilter {

    @Inject
    TokenService tokenService;

    @Override
    public void filter(ClientRequestContext requestContext) {
        String token = tokenService.getToken();
        if (token != null) {
            requestContext.getHeaders().add("Authorization", "Bearer " + token);
        }
    }
}
