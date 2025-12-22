package com.bloodlive.project.system.controller;

import com.bloodlive.project.system.ValidacionException;
import com.bloodlive.project.system.domain.detallesnotadeventa.DatosModificarNotaDeVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;
import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVentaRepository;
import com.bloodlive.project.system.domain.notadeventa.*;
import com.bloodlive.project.system.domain.producto.DatosListadoProductos;
import com.bloodlive.project.system.domain.producto.Producto;
import com.bloodlive.project.system.domain.producto.ProductoRepository;
import com.bloodlive.project.system.service.NotaDeVentaService;
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
    @Autowired
    NotaDeVentaService notaDeVentaService;

    public NotaDeVentaController(NotaDeVentaService notaDeVentaService,NotaDeVentaRepository notaDeVentaRepository){
        this.notaDeVentaService = notaDeVentaService;
        this.notaDeVentaRepository = notaDeVentaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaNotaDeVenta> registrarNotaDeVenta(
            @RequestBody @Valid DatosRegistroNotaDeVenta datosRegistroNotaDeVenta,
            UriComponentsBuilder uriComponentsBuilder){
        NotaDeVenta notaDeVenta;
        notaDeVenta = notaDeVentaService.registrarNotaDeVenta(datosRegistroNotaDeVenta);

        DatosRespuestaNotaDeVenta datosRespuestaNotaDeVenta =  new DatosRespuestaNotaDeVenta(notaDeVenta.getId(),
                notaDeVenta.getCliente(),datosRegistroNotaDeVenta.productos(),notaDeVenta.getTotal());

        URI url = uriComponentsBuilder.path("notadeventa/{id}").buildAndExpand(notaDeVenta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaNotaDeVenta);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoNotaDeVenta>> listadoNotasDeVenta(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(notaDeVentaService.listarNotasDeVenta(paginacion));
    }



    @DeleteMapping
    @Transactional
    public ResponseEntity<String> eliminarNotaDeVenta(@RequestBody @Valid DatosEliminarNotaDeVenta datos){

        notaDeVentaService.eliminarNotaDeVenta(datos.id());

        return ResponseEntity.ok("Eliminado correctamente Nota de venta id: "+ datos.id());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaNotaDeVenta> modificarNotaDeVenta
            (DatosModificarNotaDeVenta datosModificarNotasDeVenta){

    }




}
