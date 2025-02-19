package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DatosDetalleVenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroNotaDeVenta(
        @NotBlank
        String cliente,
        @NotNull
        List<DatosDetalleVenta> productos
        ) {
}
