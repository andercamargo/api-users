package com.github.andercamargo.apiusers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ArquivoDTO{
    private String nomeArquivo;
    private Long idUsuarioOwner;
    private ConteudoDTO conteudo;
}