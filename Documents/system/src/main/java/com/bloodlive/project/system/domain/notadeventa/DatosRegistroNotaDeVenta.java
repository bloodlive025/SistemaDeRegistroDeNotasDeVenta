package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DatosDetalleNotaDeVenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroNotaDeVenta(
        @NotBlank(message =  "Debe ingresar el cliente")
        String cliente,
        @NotNull(message = "Debe ingresar al menos un producto")
        List<DatosDetalleNotaDeVenta> productos
        ) {
}
