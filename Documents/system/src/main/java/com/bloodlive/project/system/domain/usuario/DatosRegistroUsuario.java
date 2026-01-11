package com.bloodlive.project.system.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(@NotBlank String login,
                                   @NotBlank String nombre,
                                   @NotBlank String clave) {
}
