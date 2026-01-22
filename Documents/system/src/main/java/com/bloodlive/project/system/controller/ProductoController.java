package com.bloodlive.project.system.controller;

import com.bloodlive.project.system.Exceptions.ValidacionException;
import com.bloodlive.project.system.domain.producto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/productos")
@SecurityRequirement(name = "bearer-key")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<DatosRespuestaProducto> registrarProducto(@RequestBody @Valid DatosRegistroProducto datosRegistroProducto,
                                                                    UriComponentsBuilder uriComponentsBuilder){

        if(productoRepository.existsByNombre(datosRegistroProducto.nombre())){
            throw new ValidacionException("El nombre ya existe");
        }

        Producto producto = productoRepository.save(new Producto(datosRegistroProducto));
        DatosRespuestaProducto datosRespuestaProducto =new DatosRespuestaProducto(producto.getId(),
                producto.getNombre(),producto.getPrecio(),producto.getCantidad(), producto.getUrl());

        URI url = uriComponentsBuilder.path("productos/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);

    }


    @DeleteMapping
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    public ResponseEntity<String>  eliminarProducto(@RequestBody @Valid DatosEliminarProducto datosEliminarProducto){

        Producto producto = productoRepository.findById(datosEliminarProducto.id())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoRepository.delete(producto);

        return ResponseEntity.ok("Producto '" + datosEliminarProducto.id() + "' eliminado");

    }

    @PutMapping
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @Transactional
    public ResponseEntity<DatosRespuestaProducto> modificarProducto(@RequestBody @Valid DatosModificarProducto datosModificarProducto,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        Producto producto = productoRepository.findById(datosModificarProducto.id())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if(datosModificarProducto.nombre() != null){
            producto.setNombre(datosModificarProducto.nombre());
        }

        if(datosModificarProducto.cantidad() != null){
            producto.setCantidad(datosModificarProducto.cantidad());
        }

        if(datosModificarProducto.precio() != null){
            producto.setPrecio(datosModificarProducto.precio());
        }

        if(datosModificarProducto.url() != null){
            producto.setUrl(datosModificarProducto.url());
        }

        DatosRespuestaProducto datosRespuestaProducto =new DatosRespuestaProducto(producto.getId(),
                producto.getNombre(),producto.getPrecio(),producto.getCantidad(),
                producto.getUrl());

        URI url = uriComponentsBuilder.path("productos/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);

    }




    @GetMapping
    public ResponseEntity<Page<DatosListadoProductos>> listadoProductos(@PageableDefault(size=20) Pageable paginacion){
        return ResponseEntity.ok(productoRepository.findAll(paginacion).map(DatosListadoProductos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoProductos> obtenerProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(()->new ValidacionException("Producto no encontrado"));
        DatosListadoProductos datosListadoProducto = new DatosListadoProductos(producto);
        return ResponseEntity.ok(datosListadoProducto);
    }
    
}
