package com.github.andercamargo.apiusers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EnderecoDTO {

    @NotBlank
    private String logradouro;

    @Positive
    private int numero;

    @NotBlank
    private String cidade;

    @NotBlank
    private String bairro;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;
}
