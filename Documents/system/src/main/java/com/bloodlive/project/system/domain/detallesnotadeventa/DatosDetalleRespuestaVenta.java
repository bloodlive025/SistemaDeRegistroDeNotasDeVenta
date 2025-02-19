package com.bloodlive.project.system.domain.detallesnotadeventa;

public record DatosDetalleRespuestaVenta(Long id,
                                         String nombreProducto,
                                         Double precio,
                                         Integer cantidad,
                                         Double subtotal
) {
}
