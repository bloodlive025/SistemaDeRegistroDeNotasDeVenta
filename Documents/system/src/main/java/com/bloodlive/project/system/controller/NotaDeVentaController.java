package com.bloodlive.project.system.controller;

import com.bloodlive.project.system.ValidacionException;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVentaRepository;
import com.bloodlive.project.system.domain.notadeventa.*;
import com.bloodlive.project.system.domain.producto.DatosListadoProductos;
import com.bloodlive.project.system.domain.producto.Producto;
import com.bloodlive.project.system.domain.producto.ProductoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notadeventa")
@SecurityRequirement(name = "bearer-key")
public class NotaDeVentaController {
    @Autowired
    NotaDeVentaRepository notaDeVentaRepository;
    @Autowired
    ProductoRepository productoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaNotaDeVenta> registrarNotaDeVenta(
            @RequestBody @Valid DatosRegistroNotaDeVenta datosRegistroNotaDeVenta,
            UriComponentsBuilder uriComponentsBuilder){
        //SETEO DEL CLIENTE
        NotaDeVenta notaDeVenta=new NotaDeVenta();
        notaDeVenta.setCliente(datosRegistroNotaDeVenta.cliente());

        List<DetalleNotaDeVenta> detalles = datosRegistroNotaDeVenta.productos().stream().map(datosDetalleVenta->
                {
                    Producto producto = productoRepository.findById(datosDetalleVenta.productoId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + datosDetalleVenta.productoId()));

                    DetalleNotaDeVenta detalleNotaDeVenta = new DetalleNotaDeVenta();
                    detalleNotaDeVenta.setNotaDeVenta(notaDeVenta);
                    detalleNotaDeVenta.setProducto(producto);
                    detalleNotaDeVenta.setCantidad(datosDetalleVenta.cantidad());
                    detalleNotaDeVenta.setSubtotal(producto.getPrecio()*datosDetalleVenta.cantidad());
                    return detalleNotaDeVenta;
                }).collect(Collectors.toList());

        //notaDeVenta = notaDeVentaRepository.save(new NotaDeVenta(datosRegistroNotaDeVenta));
        notaDeVenta.setProductos(detalles);
        notaDeVenta.setTotal(detalles.stream().mapToDouble(DetalleNotaDeVenta::getSubtotal).sum());

        notaDeVentaRepository.save(notaDeVenta);



        DatosRespuestaNotaDeVenta datosRespuestaNotaDeVenta =  new DatosRespuestaNotaDeVenta(notaDeVenta.getId(),
                notaDeVenta.getCliente(),datosRegistroNotaDeVenta.productos(),notaDeVenta.getTotal());

        URI url = uriComponentsBuilder.path("notadeventa/{id}").buildAndExpand(notaDeVenta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaNotaDeVenta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoNotaDeVenta>> listadoProductos(@PageableDefault(size=2) Pageable paginacion){
        ResponseEntity<Page<DatosListadoNotaDeVenta>> respuesta=ResponseEntity.ok(notaDeVentaRepository.findAll(paginacion).map(DatosListadoNotaDeVenta::new));
        return respuesta;
    }

    @DeleteMapping
    @Transactional
    public void eliminarNotaDeVenta(@RequestBody @Valid DatosEliminarNotaDeVenta datos){

        if (!notaDeVentaRepository.existsById(datos.id())) {
            throw new ValidacionException("Nota de venta no encontrada: " + datos.id());
        }

        notaDeVentaRepository.deleteById(datos.id());


    }





}
