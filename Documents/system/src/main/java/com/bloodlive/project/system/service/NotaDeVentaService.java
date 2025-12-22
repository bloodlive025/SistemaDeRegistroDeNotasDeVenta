package com.bloodlive.project.system.service;

import com.bloodlive.project.system.ValidacionException;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVentaRepository;
import com.bloodlive.project.system.domain.notadeventa.DatosListadoNotaDeVenta;
import com.bloodlive.project.system.domain.notadeventa.DatosRegistroNotaDeVenta;
import com.bloodlive.project.system.domain.notadeventa.NotaDeVenta;
import com.bloodlive.project.system.domain.notadeventa.NotaDeVentaRepository;
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
                    detalleNotaDeVenta.setSubtotal(producto.getPrecio()* producto.getCantidad());
                    return detalleNotaDeVenta;

                })
                .toList();
        notaDeVenta.setCliente(datosRegistroNotaDeVenta.cliente());
        notaDeVenta.setProductos(detalles);
        notaDeVenta.setTotal(detalles.stream().mapToDouble(DetalleNotaDeVenta::getSubtotal).sum());
        notaDeVentaRepository.save(notaDeVenta);

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





}
