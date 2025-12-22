package com.bloodlive.project.system.domain.detallesnotadeventa;

import jakarta.validation.constraints.NotNull;

public record DatosDetalleNotaDeVenta(
        @NotNull Long productoId,
        @NotNull Integer cantidad
) {
}
