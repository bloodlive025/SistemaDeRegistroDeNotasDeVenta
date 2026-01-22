package com.bloodlive.project.system.domain.producto;

import jakarta.persistence.*;
import jakarta.validation.Valid;

@Entity(name="Producto")
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private Integer cantidad;
    private String url;


    public Producto(Long id, String nombre, Double precio, Integer cantidad,String url){
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
        this.url = url;
    }

    public Producto(){

    }

    public Producto(@Valid DatosRegistroProducto datosRegistroProducto) {
        this.nombre=datosRegistroProducto.nombre();
        this.precio=datosRegistroProducto.precio();
        this.cantidad=datosRegistroProducto.cantidad();
        this.url=datosRegistroProducto.url();
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
