package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.Credentials;
import org.acme.services.AuthService;
import org.acme.services.TokenService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @Inject
    TokenService tokenService;

    @POST
    @Path("/login")
    public Response login(Credentials credentials) {
        try {
            //Chama o AuthService para autenticar
            String token = authService.authenticate(credentials.getUsername(), credentials.getPassword());
            tokenService.setToken(token);
            return Response.ok().entity(token).build();
            // return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Authentication failed: " + e.getMessage()).build();
        }
    }
}
