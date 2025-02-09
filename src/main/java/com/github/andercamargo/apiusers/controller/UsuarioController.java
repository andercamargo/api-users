package com.github.andercamargo.apiusers.controller;

import com.github.andercamargo.apiusers.dto.UsuarioDTO;
import com.github.andercamargo.apiusers.entity.Usuario;
import com.github.andercamargo.apiusers.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Operation(summary = "Entrega uma lista com todos os usuários presentes na base de dados")
    @ApiResponse(responseCode = "200", description = "Lista de usuários existentes na base de dados")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Operation(summary = "Retorna as informações completas de um usuário específico")
    @ApiResponse(responseCode = "200", description = "Informações completas do usuário solicitado")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> detalharUsuario(@PathVariable Long idUsuario) {
        var usuario = usuarioService.detalharUsuario(idUsuario);
        return usuario == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Retorna as informações simplificadas de um usuário específico")
    @ApiResponse(responseCode = "200", description = "Informações simplificadas do usuário solicitado")
    @GetMapping("/{idUsuario}/simplificado")
    public ResponseEntity<UsuarioDTO> detalharUsuarioSimplificado(@PathVariable Long idUsuario) {
        var usuario = usuarioService.detalharUsuarioSimplificado(idUsuario);
        return usuario == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(usuario);
    }
}
