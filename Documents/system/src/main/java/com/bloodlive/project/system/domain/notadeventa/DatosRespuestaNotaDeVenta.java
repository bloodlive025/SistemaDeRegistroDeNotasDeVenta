package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DatosDetalleVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;

import java.util.List;

public record DatosRespuestaNotaDeVenta(
        Long id,
        String cliente,
        List<DatosDetalleVenta> ListaDeProductos,
        Double total
) {
}
