package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DatosDetalleNotaDeVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DatosRespuestaDetalleNotaVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;

import java.util.List;
import java.util.stream.Collectors;

public record DatosRespuestaNotaDeVenta(
        Long id,
        String cliente,
        List<DatosRespuestaDetalleNotaVenta> ListaDeProductos,
        Double total
) {
    public DatosRespuestaNotaDeVenta(NotaDeVenta notaDeVenta){
        this(notaDeVenta.getId(),notaDeVenta.getCliente(),
                notaDeVenta.getProductos().stream().map(
                        Productos->new DatosRespuestaDetalleNotaVenta(Productos.getProducto().getId()
                                ,Productos.getProducto().getNombre()
                                ,Productos.getProducto().getPrecio()
                                ,Productos.getCantidad(),Productos.getSubtotal()
                        )).collect(Collectors.toList()),
                notaDeVenta.getTotal());

    }
}
