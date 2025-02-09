package com.github.andercamargo.apiusers.service;

import com.github.andercamargo.apiusers.entity.Arquivo;
import com.github.andercamargo.apiusers.repository.ArquivoRepository;
import com.github.andercamargo.apiusers.dto.ArquivoDTO;
import com.github.andercamargo.apiusers.dto.ConteudoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArquivoService {

    private final ArquivoRepository arquivoRepository;
    private final ConversaoService<ConteudoDTO> conversaoService;

    @Cacheable("arquivos")
    @Transactional
    public List<ArquivoDTO> listarTodosArquivos() {
        return arquivoRepository.findAll().stream().map(arquivo ->
                toDTO(arquivo, false))
                .toList();
    }

    @Cacheable(value="arquivosUsuario", key="#idUsuario")
    public List<ArquivoDTO> listarArquivosUsuario(Long idUsuario) {
        return arquivoRepository.findByIdUsuarioOwner(idUsuario).stream().map(arquivo ->
                toDTO(arquivo, true)).toList();
    }

    @SneakyThrows
    private ArquivoDTO toDTO(Arquivo arquivo, boolean comConteudo) {
        return new ArquivoDTO(
                arquivo.getNomeArquivo(),
                arquivo.getIdUsuarioOwner(),
                comConteudo ?
                        conversaoService.convert(arquivo.getConteudo(), ConteudoDTO.class)
                        : null
        );
    }
}
