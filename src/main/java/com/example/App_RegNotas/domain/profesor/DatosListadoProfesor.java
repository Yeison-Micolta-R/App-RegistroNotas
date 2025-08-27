package com.example.App_RegNotas.domain.profesor;

public record DatosListadoProfesor(
        Long id,
        String numeroIdentificacion,
        String nombre,
        String apellido,
        String telefono,
        String correoInstitucional
) {
    public DatosListadoProfesor(Profesor profesor){
        this(profesor.getId(), profesor.getNumeroIdentificacion(), profesor.getNombre(),
                profesor.getApellido(), profesor.getTelefono(), profesor.getCorreoInstitucional());
    }
}
