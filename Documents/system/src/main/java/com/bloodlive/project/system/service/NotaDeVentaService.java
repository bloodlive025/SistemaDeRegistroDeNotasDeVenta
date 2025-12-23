package com.bloodlive.project.system.service;

import com.bloodlive.project.system.ValidacionException;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVentaRepository;
import com.bloodlive.project.system.domain.notadeventa.*;
import com.bloodlive.project.system.domain.producto.Producto;
import com.bloodlive.project.system.domain.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotaDeVentaService {

    @Autowired
    private NotaDeVentaRepository notaDeVentaRepository;

    @Autowired
    private DetalleNotaDeVentaRepository detalleNotaDeVentaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public NotaDeVenta registrarNotaDeVenta(DatosRegistroNotaDeVenta datosRegistroNotaDeVenta){
        NotaDeVenta notaDeVenta = new NotaDeVenta(datosRegistroNotaDeVenta);

        List<DetalleNotaDeVenta> detalles = datosRegistroNotaDeVenta.productos().stream()
                .map(datosDetalleNotaDeVenta -> {
                    Producto producto = productoRepository.findById(datosDetalleNotaDeVenta.productoId())
                            .orElseThrow(() -> new ValidacionException("Producto no encontrado: " + datosDetalleNotaDeVenta.productoId()));

                    DetalleNotaDeVenta detalleNotaDeVenta = new DetalleNotaDeVenta();
                    detalleNotaDeVenta.setNotaDeVenta(notaDeVenta);
                    detalleNotaDeVenta.setProducto(producto);
                    detalleNotaDeVenta.setCantidad(datosDetalleNotaDeVenta.cantidad());
                    detalleNotaDeVenta.setSubtotal(producto.getPrecio()* detalleNotaDeVenta.getCantidad());
                    return detalleNotaDeVenta;

                })
                .toList();
        notaDeVenta.setCliente(datosRegistroNotaDeVenta.cliente());
        notaDeVenta.setProductos(detalles);
        notaDeVenta.setTotal();
        notaDeVentaRepository.save(notaDeVenta);

        return notaDeVenta;
    }

    @Transactional
    public NotaDeVenta modificarNotaDeVenta(DatosModificarNotaDeVenta datosModificarNotaDeVenta, NotaDeVenta notaDeVenta){

        System.out.println("Cliente recibido: " + datosModificarNotaDeVenta.cliente());

        List<DetalleNotaDeVenta> detalles;
        if(datosModificarNotaDeVenta.productos() != null && !datosModificarNotaDeVenta.productos().isEmpty()){
            detalles = datosModificarNotaDeVenta.productos().stream()
                    .map(datosDetalleNotaDeVenta -> {
                        Producto producto = productoRepository.findById(datosDetalleNotaDeVenta.productoId())
                                .orElseThrow(() -> new ValidacionException("Producto no encontrado: " + datosDetalleNotaDeVenta.productoId()));

                        DetalleNotaDeVenta detalleNotaDeVenta = new DetalleNotaDeVenta();
                        //detalleNotaDeVenta.setNotaDeVenta(notaDeVenta);      Se sincroniza el detalleNotaDVenta y la notaDVenta desde la clase NotaDeVents
                        detalleNotaDeVenta.setProducto(producto);
                        detalleNotaDeVenta.setCantidad(datosDetalleNotaDeVenta.cantidad());
                        detalleNotaDeVenta.setSubtotal(producto.getPrecio()* detalleNotaDeVenta.getCantidad());
                        return detalleNotaDeVenta;

                    })
                    .toList();
            notaDeVenta.addProductos(detalles);
            notaDeVenta.setTotal();
        }

        if(datosModificarNotaDeVenta.cliente() != null){
            notaDeVenta.setCliente(datosModificarNotaDeVenta.cliente());
        }


        return notaDeVenta;
    }



    public Page<DatosListadoNotaDeVenta> listarNotasDeVenta(Pageable paginacion){
        return notaDeVentaRepository.findAll(paginacion).map(DatosListadoNotaDeVenta::new);
    }

    @Transactional
    public void eliminarNotaDeVenta(Long id){

        if (notaDeVentaRepository.existsById(id)) {
            notaDeVentaRepository.deleteById(id);
        }
        else{
            throw new ValidacionException("Nota de Venta no encontrada: " + id);
        }

    }


    public DatosRespuestaNotaDeVenta obtenerPorId(Long id) {
        NotaDeVenta notaDeVenta = notaDeVentaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("Id no encontrado"));

        return new DatosRespuestaNotaDeVenta(notaDeVenta);
    }
}
