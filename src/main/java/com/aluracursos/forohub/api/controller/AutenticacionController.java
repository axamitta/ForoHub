package com.aluracursos.forohub.api.controller;


import com.aluracursos.forohub.api.domain.usuario.DatosAutenticacion;
import com.aluracursos.forohub.api.domain.usuario.Usuario;
import com.aluracursos.forohub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());
        try {
            var authentication = authenticationManager.authenticate(authToken);
            var usuario = (Usuario) authentication.getPrincipal();

            // Aquí puedes buscar la entidad Usuario si necesitas pasarla completa al TokenService
            // Pero para simplificar, suponemos que el correo electrónico es suficiente
            var tokenJWT = tokenService.generarToken(usuario);

            return ResponseEntity.ok(Map.of("token", tokenJWT));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Correo o contraseña inválidos"));
        }
    }

}
