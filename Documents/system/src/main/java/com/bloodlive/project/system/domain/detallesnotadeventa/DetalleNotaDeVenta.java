package com.bloodlive.project.system.domain.detallesnotadeventa;

import com.bloodlive.project.system.domain.notadeventa.NotaDeVenta;
import com.bloodlive.project.system.domain.producto.Producto;
import jakarta.persistence.*;

@Entity(name="DetalleNotaDeVenta")
@Table(name="detallenotasdeventa")
public class DetalleNotaDeVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="nota_de_venta_id",nullable = false)
    private NotaDeVenta notaDeVenta;

    @ManyToOne
    @JoinColumn(name = "producto_id",nullable = false)
    private Producto producto;

    private Integer cantidad;
    private Double subtotal;

    public DetalleNotaDeVenta(){
    }

    public DetalleNotaDeVenta(NotaDeVenta notaDeVenta,Producto producto, Integer cantidad){
        this.notaDeVenta = notaDeVenta;
        this.producto=producto;
        this.cantidad=cantidad;
        this.subtotal=calcularSubtotal();
    }

    public DetalleNotaDeVenta(DatosDetalleNotaDeVenta datosDetalleVenta){
        this.cantidad=datosDetalleVenta.cantidad();
        this.subtotal=getSubtotal();
    }

    public Double calcularSubtotal(){
        return producto.getPrecio()*cantidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNotaDeVenta(NotaDeVenta notaDeVenta) {
        this.notaDeVenta = notaDeVenta;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public NotaDeVenta getNotaDeVenta() {
        return notaDeVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getSubtotal() {
        return producto.getPrecio()*cantidad;
    }
}
