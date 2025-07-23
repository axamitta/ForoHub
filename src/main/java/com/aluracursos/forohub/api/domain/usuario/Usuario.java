package com.aluracursos.forohub.api.domain.usuario;


import com.aluracursos.forohub.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "autor")
    private List<Topico> topicos = new ArrayList<>();
    private String nombre;
    private String correoElectronico;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;


    public Usuario(DatosRegistroUsuario datos) {
        this.nombre = datos.nombre();
        this.correoElectronico = datos.correoElectronico();
        this.contrasena = datos.contrasena();
        this.perfil = datos.perfil();
    }

    public Usuario(String nombre, String correoElectronico, String contrasena, Perfil perfil) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.perfil = perfil;
    }
}
