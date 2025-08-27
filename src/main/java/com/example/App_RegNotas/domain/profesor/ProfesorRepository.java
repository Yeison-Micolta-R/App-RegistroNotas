package com.example.App_RegNotas.domain.profesor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByNumeroIdentificacion(String numeroIdentificacion);
    boolean existsByCorreoInstitucional(String correoInstitucional);
    Page<Profesor> findByActivoTrue(Pageable paginacion);
}
