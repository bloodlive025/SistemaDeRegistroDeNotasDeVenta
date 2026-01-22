package com.bloodlive.project.system.controller;

import com.bloodlive.project.system.Exceptions.ValidacionException;
import com.bloodlive.project.system.domain.notadeventa.DatosModificarNotaDeVenta;
import com.bloodlive.project.system.domain.notadeventa.*;
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

        DatosRespuestaNotaDeVenta datosRespuestaNotaDeVenta =  new DatosRespuestaNotaDeVenta(notaDeVenta);

        URI url = uriComponentsBuilder.path("notadeventa/{id}").buildAndExpand(notaDeVenta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaNotaDeVenta);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoNotaDeVenta>> listadoNotasDeVenta(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(notaDeVentaService.listarNotasDeVenta(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaNotaDeVenta> obtenerNotaDeVenta(@PathVariable Long id) {
        return ResponseEntity.ok(notaDeVentaService.obtenerPorId(id));
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
            (@RequestBody @Valid DatosModificarNotaDeVenta datosModificarNotasDeVenta,
             UriComponentsBuilder uriComponentsBuilder){

        NotaDeVenta notaDeVenta = notaDeVentaRepository.findById(datosModificarNotasDeVenta.id())
                .orElseThrow(() -> new ValidacionException("Nota de Venta no Encontrada"));
        notaDeVentaService.modificarNotaDeVenta(datosModificarNotasDeVenta,notaDeVenta);

        DatosRespuestaNotaDeVenta datosRespuestaNotaDeVenta = new DatosRespuestaNotaDeVenta(notaDeVenta);

        URI url = uriComponentsBuilder.path("notadeventa/{id}").buildAndExpand(notaDeVenta.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaNotaDeVenta);
    }




}
