package com.gazo.gymsystem.security;

import com.gazo.gymsystem.entity.Usuario;
import com.gazo.gymsystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                usuario.getUsuario(),
                usuario.getContrasena(),
                usuario.getActivo(),
                true,
                true,
                true,
                List.of(
                        new SimpleGrantedAuthority("ROLE_" + usuario.getRol())
                )
        );
    }
}