package com.bloodlive.project.system.controller;


import com.bloodlive.project.system.Exceptions.ValidacionException;
import com.bloodlive.project.system.Exceptions.ValidacionExceptionToken;
import com.bloodlive.project.system.domain.usuario.DatosAutenticacionUsuario;
import com.bloodlive.project.system.domain.usuario.Usuario;
import com.bloodlive.project.system.infra.security.DatosJWTToken;
import com.bloodlive.project.system.infra.security.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(
        origins = "http://localhost:4200/login",
        allowedHeaders = "*",
        methods = {RequestMethod.OPTIONS,RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());

        try {
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (AuthenticationException e) {
            throw new ValidacionExceptionToken();
        }
    }


}
