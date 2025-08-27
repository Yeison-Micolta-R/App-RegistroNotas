package com.example.App_RegNotas.domain.profesor;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record DatosRespuestaProfesor(
        Long id,
        String numeroIdentificacion,
        String nombre,
        String apellido,
        String telefono,
        String correoInstitucional
) {
}
