package com.example.App_RegNotas.domain.profesor;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroProfesor(
        @NotBlank
        String numeroIdentificacion,
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        String telefono,
        @NotBlank
        String correoInstitucional
) {
}