package com.bloodlive.project.system.domain.producto;

public record DatosListadoProductos(Long id,
                                    String nombre,
                                    Double precio,
                                    Integer cantidad) {
    public DatosListadoProductos(Producto producto){
        this(producto.getId(),producto.getNombre(),producto.getPrecio(),producto.getCantidad());
    }
}
