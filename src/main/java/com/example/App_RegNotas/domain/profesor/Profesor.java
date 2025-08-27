package com.example.App_RegNotas.domain.profesor;

import com.example.App_RegNotas.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profesores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correoInstitucional;
    private Boolean activo;

    // Relación con cursos
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Curso> cursos = new HashSet<>();

    public Profesor(DatosRegistroProfesor datosRegistroProfesor) {
        this.activo = true;
        this.nombre = datosRegistroProfesor.nombre();
        this.apellido = datosRegistroProfesor.apellido();
        this.numeroIdentificacion = datosRegistroProfesor.numeroIdentificacion();
        this.telefono = datosRegistroProfesor.telefono();
        this.correoInstitucional = datosRegistroProfesor.correoInstitucional();
    }

    public void desactivarProfesor() {
        this.activo = false;
    }
}
