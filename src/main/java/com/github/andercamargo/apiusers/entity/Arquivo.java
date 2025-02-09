package com.github.andercamargo.apiusers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ARQUIVO")
@Data
@NoArgsConstructor
public class Arquivo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nomeArquivo;

    @Column(name = "ID_USUARIO")
    private Long idUsuarioOwner;

    @Column(name = "CONTEUDO")
    private String conteudo;

    public Arquivo(String nomeArquivo, Long idUsuarioOwner, String conteudo) {
        this.nomeArquivo = nomeArquivo;
        this.idUsuarioOwner = idUsuarioOwner;
        this.conteudo = conteudo;
    }
}