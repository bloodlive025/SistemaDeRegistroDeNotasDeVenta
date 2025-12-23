package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DatosRespuestaDetalleNotaVenta;

import java.util.List;
import java.util.stream.Collectors;

public record DatosListadoNotaDeVenta(Long id,
                                      String cliente,
                                      List<DatosRespuestaDetalleNotaVenta> productos,
                                      Double precio) {


    public DatosListadoNotaDeVenta(NotaDeVenta notaDeVenta){

        /*List<DatosDetalleRespuestaVenta> productos = notaDeVenta.getProductos().stream().map(Productos->new DatosDetalleRespuestaVenta(Productos.getProducto().getId()
                ,Productos.getProducto().getNombre()
                ,Productos.getProducto().getPrecio()
                ,Productos.getCantidad(),Productos.getSubtotal()
                )).collect(Collectors.toList());
*/
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
