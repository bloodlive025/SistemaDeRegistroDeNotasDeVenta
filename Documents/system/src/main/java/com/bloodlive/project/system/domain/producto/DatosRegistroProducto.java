package com.bloodlive.project.system.domain.producto;

import jakarta.validation.constraints.*;

public record DatosRegistroProducto(
        @NotBlank
        String nombre,
        @Positive
        @NotNull
        @Digits(integer = 4, fraction = 2)
        Double precio,
        @NotNull
        @PositiveOrZero
        Integer cantidad,
        String url
) {
}
