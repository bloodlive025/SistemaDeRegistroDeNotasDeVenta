package com.bloodlive.project.system.domain.notadeventa;

import com.bloodlive.project.system.domain.detallesnotadeventa.DetalleNotaDeVenta;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="NotaDeVenta")
@Table(name="notasdeventa")
public class NotaDeVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    @OneToMany(mappedBy = "notaDeVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleNotaDeVenta> productos;
    private Double total;

    public NotaDeVenta(){

    }

    public NotaDeVenta(DatosRegistroNotaDeVenta datosRegistroNotaDeVenta){
        this.cliente=datosRegistroNotaDeVenta.cliente();
    }

    public Long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public List<DetalleNotaDeVenta> getProductos() {
        return productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setProductos(List<DetalleNotaDeVenta> productos) {
        this.productos = productos;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
