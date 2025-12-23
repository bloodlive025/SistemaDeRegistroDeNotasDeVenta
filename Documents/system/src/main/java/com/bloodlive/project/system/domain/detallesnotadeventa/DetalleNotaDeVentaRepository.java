package com.bloodlive.project.system.domain.detallesnotadeventa;

import com.bloodlive.project.system.domain.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetalleNotaDeVentaRepository extends JpaRepository<DetalleNotaDeVenta,Long> {
    Optional<DetalleNotaDeVenta> findById(Long detalleid);

}
