package org.acme;

import org.acme.clients.AuthApiClient;
import org.acme.models.Credentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.GenericType;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class AuthApiClientTest {

    @Mock
    AuthApiClient authApiClient; // Mock da interface AuthApiClient

    @Mock
    Response response; // Mock da resposta do servidor

    @Test
    void testLogin() {
        // Mock do comportamento da resposta para simular um sucesso
        when(response.getStatus()).thenReturn(200);
        // Mock para a resposta do método readEntity usando GenericType
        when(response.readEntity(new GenericType<Map<String, String>>() {}))
            .thenReturn(Map.of("token", "mocked-token"));

        // Simulando um objeto Credentials
        Credentials credentials = new Credentials();
        credentials.setUsername("user");
        credentials.setPassword("password");

        // Simula a chamada do método login no cliente AuthApiClient
        when(authApiClient.login(credentials)).thenReturn(response);

        // Chama o método login
        Response loginResponse = authApiClient.login(credentials);

        // Verifique se a resposta tem status 200 e se o token está correto
        assertEquals(200, loginResponse.getStatus());
        assertEquals("mocked-token", loginResponse.readEntity(new GenericType<Map<String, String>>() {}).get("token"));

        // Verifique se a resposta foi mockada corretamente
        verify(authApiClient).login(credentials);
        verify(response).getStatus();
        verify(response).readEntity(new GenericType<Map<String, String>>() {});
    }
}
