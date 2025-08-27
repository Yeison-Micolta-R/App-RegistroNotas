package com.example.App_RegNotas.controller;

import com.example.App_RegNotas.domain.profesor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;


    // Crear Profesor
    @PostMapping
    public ResponseEntity<DatosRespuestaProfesor> registrarProfesor(@RequestBody @Valid DatosRegistroProfesor datosRegistroProfesor, UriComponentsBuilder uriComponentsBuilder) {

        Profesor profesor = profesorRepository.save(new Profesor(datosRegistroProfesor));
        DatosRespuestaProfesor datosRespuestaProfesor = new DatosRespuestaProfesor(profesor.getId(), profesor.getNumeroIdentificacion(), profesor.getNombre(),
                profesor.getApellido(), profesor.getTelefono(), profesor.getCorreoInstitucional());
        URI url = uriComponentsBuilder.path("/profesores/{id}").buildAndExpand(profesor.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProfesor);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<Page<DatosListadoProfesor>> listarProfesores(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(profesorRepository.findByActivoTrue(paginacion).map(DatosListadoProfesor::new));
    }

    // Buscar por identificación
    /*@GetMapping("/{numeroIdentificacion}")
    public ResponseEntity<Profesor> obtenerPorIdentificacion(@PathVariable String numeroIdentificacion) {
        return profesorRepository.findByNumeroIdentificacion(numeroIdentificacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaProfesor> retornaDatosProfesor(@PathVariable Long id) {
        Profesor profesor = profesorRepository.getReferenceById(id);
        var datosProfesor = new DatosRespuestaProfesor(profesor.getId(), profesor.getNumeroIdentificacion(), profesor.getNombre(),
                profesor.getApellido(), profesor.getTelefono(), profesor.getCorreoInstitucional()
                );
        return ResponseEntity.ok(datosProfesor);
    }

    // Eliminar profesor
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Profesor profesor = profesorRepository.getReferenceById(id);
        profesor.desactivarProfesor();
        return ResponseEntity.noContent().build();
    }
}
