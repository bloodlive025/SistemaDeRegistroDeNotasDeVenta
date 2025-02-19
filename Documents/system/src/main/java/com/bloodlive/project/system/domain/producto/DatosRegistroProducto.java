package com.bloodlive.project.system.domain.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record DatosRegistroProducto(
        @NotBlank
        String nombre,
        @Positive
        @NotNull
        Double precio,
        @NotNull
        @PositiveOrZero
        Integer cantidad
) {
}
