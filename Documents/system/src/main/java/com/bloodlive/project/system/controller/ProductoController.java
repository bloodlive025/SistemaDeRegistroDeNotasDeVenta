package com.bloodlive.project.system.controller;

import com.bloodlive.project.system.ValidacionException;
import com.bloodlive.project.system.domain.producto.*;
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
@RequestMapping("/productos")
@SecurityRequirement(name = "bearer-key")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaProducto> registrarProducto(@RequestBody @Valid DatosRegistroProducto datosRegistroProducto,
                                                                    UriComponentsBuilder uriComponentsBuilder){

        if(productoRepository.existsByNombre(datosRegistroProducto.nombre())){
            throw new ValidacionException("El nombre ya existe");
        }

        Producto producto = productoRepository.save(new Producto(datosRegistroProducto));
        DatosRespuestaProducto datosRespuestaProducto =new DatosRespuestaProducto(producto.getId(),
                producto.getNombre(),producto.getPrecio(),producto.getCantidad(),"NO ME ABANDONES");

        URI url = uriComponentsBuilder.path("productos/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);

    }


    @DeleteMapping
    public ResponseEntity<String>  eliminarProducto(@RequestBody @Valid DatosEliminarProducto datosEliminarProducto){

        Producto producto = productoRepository.findByNombre(datosEliminarProducto.nombre())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoRepository.delete(producto);

        return ResponseEntity.ok("Producto '" + datosEliminarProducto.nombre() + "' eliminado");

    }

    @PutMapping
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



        DatosRespuestaProducto datosRespuestaProducto =new DatosRespuestaProducto(producto.getId(),
                producto.getNombre(),producto.getPrecio(),producto.getCantidad(),"NO ME ABANDONES");

        URI url = uriComponentsBuilder.path("productos/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);

    }




    @GetMapping
    public ResponseEntity<Page<DatosListadoProductos>> listadoProductos(@PageableDefault(size=5) Pageable paginacion){
        return ResponseEntity.ok(productoRepository.findAll(paginacion).map(DatosListadoProductos::new));
    }
    
}
