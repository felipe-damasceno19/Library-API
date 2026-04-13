package io.github.felipe_damasceno19.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDTO(
        @NotBlank(message = "Login obrigatório!")
        String login,
        @NotBlank(message = "Senha obrigatória!")
        String password,
        List<String> roles
) {
}
