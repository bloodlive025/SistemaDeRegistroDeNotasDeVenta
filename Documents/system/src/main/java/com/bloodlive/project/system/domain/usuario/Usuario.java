package com.bloodlive.project.system.domain.usuario;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.bloodlive.project.system.domain.usuario.Rol.ROLE_CLIENTE;
import static com.bloodlive.project.system.domain.usuario.Rol.ROLE_MANAGER;

@Entity(name="Usuario")
@Table(name="usuarios")
public class Usuario implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String  nombre;
    @Column(nullable=false)
    private String clave;

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    public Usuario(){
    }


    public Usuario(Long id, String login,String nombre, String clave){
        this.id=id;
        this.login=login;
        this.nombre=nombre;
        this.clave=clave;
    }

    public Usuario(DatosRegistroUsuario datosRegistroUsuario){
        this.login= datosRegistroUsuario.login();
        this.nombre= datosRegistroUsuario.nombre();
        this.clave= datosRegistroUsuario.clave();
        this.rol = ROLE_CLIENTE;
    }

    public Usuario(DatosRegistroUsuario datosRegistroUsuario,Rol rol){
        this.login= datosRegistroUsuario.login();
        this.nombre= datosRegistroUsuario.nombre();
        this.clave= datosRegistroUsuario.clave();
        this.rol = ROLE_MANAGER;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(clave, usuario.clave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, clave);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }


    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
