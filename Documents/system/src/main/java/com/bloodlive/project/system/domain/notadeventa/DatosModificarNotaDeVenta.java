package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DatosDetalleNotaDeVenta;

import java.util.List;

public record DatosModificarNotaDeVenta(Long id,
                                        String cliente,
                                        List<DatosDetalleNotaDeVenta> productos) {
}
