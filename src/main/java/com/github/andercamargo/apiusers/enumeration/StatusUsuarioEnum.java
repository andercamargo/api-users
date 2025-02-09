package com.github.andercamargo.apiusers.enumeration;

import lombok.Getter;

@Getter
public enum StatusUsuarioEnum {
    ATIVO ("ATIVO"),
    INATIVO ("INATIVO");

    private final String valor;

    StatusUsuarioEnum(String valor){
        this.valor = valor;
    }

}
