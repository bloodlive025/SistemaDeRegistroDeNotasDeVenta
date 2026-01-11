package com.bloodlive.project.system.service;

import com.bloodlive.project.system.domain.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService{

    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }

    public Usuario agregarUsuario(DatosRegistroUsuario datosRegistroUsuario
                                    ){


        Usuario usuario = new Usuario(datosRegistroUsuario);
        String clave = passwordEncoder.encode(datosRegistroUsuario.clave());
        usuario.setClave(clave);
        usuarioRepository.save(usuario);

        return usuario;


    }

    public Usuario agregarManager(@RequestBody DatosRegistroUsuario datosRegistroUsuario){
        Usuario manager = new Usuario(datosRegistroUsuario, Rol.ROLE_MANAGER);
        String clave = passwordEncoder.encode(datosRegistroUsuario.clave());
        manager.setClave(clave);
        usuarioRepository.save(manager);
        return manager;
    }


}
