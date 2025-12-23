package com.bloodlive.project.system.domain.notadeventa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotaDeVentaRepository extends JpaRepository<NotaDeVenta,Long> {

    @Override
    Optional<NotaDeVenta> findById(Long notaVentaId);

}
