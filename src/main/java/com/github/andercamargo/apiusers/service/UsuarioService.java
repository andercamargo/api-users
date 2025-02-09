package com.github.andercamargo.apiusers.service;


import com.github.andercamargo.apiusers.entity.Usuario;
import com.github.andercamargo.apiusers.enumeration.StatusUsuarioEnum;
import com.github.andercamargo.apiusers.repository.UsuarioRepository;
import com.github.andercamargo.apiusers.dto.EnderecoDTO;
import com.github.andercamargo.apiusers.dto.UsuarioDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ConversaoService<EnderecoDTO> conversaoService;

    @Cacheable(value = "usuarioDetalhado", key = "#idUsuario")
    @Transactional
    public UsuarioDTO detalharUsuario(Long idUsuario) {
        return  usuarioRepository.findById(idUsuario).
                map(usuario -> this.toDTO(usuario, false,false))
        .orElse(null);
    }


    @Cacheable(value = "usuarioSimplicado", key = "#idUsuario")
    @Transactional
    public UsuarioDTO detalharUsuarioSimplificado(Long idUsuario) {
        return  usuarioRepository.findById(idUsuario).
                map(usuario -> this.toDTO(usuario, true,false))
                .orElse(null);
    }

    @Cacheable("usuarios")
    @Transactional
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream().map(
                usuario -> toDTO(usuario, false, true)
        ).toList();
    }


    @SneakyThrows
    private UsuarioDTO toDTO(Usuario usuario, boolean simplificado, boolean exibirId) {
        return new UsuarioDTO(
                exibirId ? usuario.getId() : null,
                usuario.getNome(),
                usuario.getEmail(),
                simplificado ? null : usuario.getDataNascimento(),
                simplificado? null : conversaoService.convert(usuario.getEndereco(), EnderecoDTO.class),
                simplificado? null : usuario.getStatus().equals(StatusUsuarioEnum.ATIVO.getValor())
        );
    }
}