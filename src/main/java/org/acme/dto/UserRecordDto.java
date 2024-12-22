package org.acme.dto;

import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

public record UserRecordDto(
        @Schema(description = "Lista de usuários", example = "[{\"id\":1,\"username\":\"admin\",\"role\":\"admin\"}]")
        @NotNull(message = "A lista de usuários não pode ser nula")
        List<UserDTO> users
) {
        public  record UserDTO(
                @Schema(description = "ID do usuário", example = "1")
                Long id,

                @Schema(description = "Nome de usuário", example = "admin")
                String username,

                @Schema(description = "Role do usuário", example = "admin")
                String role,

                @Schema(description = "Token do usuário", example = "some-token")
                String token,

                @Schema(description = "Data e hora do token", example = "2024-12-20T15:30:00Z")
                Instant tokenTime
        ){}
}
