package com.bloodlive.project.system.controller;

import com.bloodlive.project.system.domain.usuario.*;
import com.bloodlive.project.system.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jdk.javadoc.doclet.Reporter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioRepository usuarioRepository;
    UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService){
        this.usuarioRepository=usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody DatosRegistroUsuario datosRegistroUsuario
    , UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioService.agregarUsuario(datosRegistroUsuario);


        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(),usuario.getLogin(),usuario.getNombre());
        URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/manager")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DatosRespuestaUsuario> registroUsuarioManager(@RequestBody DatosRegistroUsuario datosRegistroUsuario,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Usuario manager = usuarioService.agregarManager(datosRegistroUsuario);

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(manager.getId(),manager.getLogin(),manager.getNombre());


        URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(manager.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }



}
