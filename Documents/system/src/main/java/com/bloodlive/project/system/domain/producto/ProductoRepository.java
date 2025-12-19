package com.bloodlive.project.system.domain.producto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Boolean existsByNombre(String nombre);
    Optional<Producto> findById(Long producto_id);
    Optional<Producto> findByNombre(String nombre);

}
