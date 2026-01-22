package com.bloodlive.project.system.domain.producto;

public record DatosModificarProducto(Long id,
                                     String nombre,
                                     Integer cantidad,
                                     Double precio,
                                     String url
                                     ) {
}
