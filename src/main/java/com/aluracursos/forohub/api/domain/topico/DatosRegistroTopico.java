package com.aluracursos.forohub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        String titulo,
        String mensaje,
        Long idAutor,
        Long idCurso

) {
}
