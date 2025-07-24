package com.aluracursos.forohub.api.controller;


import com.aluracursos.forohub.api.domain.curso.Curso;
import com.aluracursos.forohub.api.domain.curso.CursoRepository;
import com.aluracursos.forohub.api.domain.topico.DatosDetalleTopico;
import com.aluracursos.forohub.api.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.api.domain.topico.Topico;
import com.aluracursos.forohub.api.domain.topico.TopicoRepository;
import com.aluracursos.forohub.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")

public class TopicoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        //control de negocio
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje");
        }

        //obtengo relaciones entre topico usuario y curso
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var curso = cursoRepository.getReferenceById(datos.idCurso());
        //creo objeto topico
        var topico = new Topico(datos, autor, curso);
        //guardo el objeto en la base
        topicoRepository.save(topico);

        //devuelvo 201 Created con la URI y JSON en Body
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

}
