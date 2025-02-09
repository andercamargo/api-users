package com.github.andercamargo.apiusers.controller;

import com.github.andercamargo.apiusers.dto.ArquivoDTO;
import com.github.andercamargo.apiusers.dto.ConteudoDTO;
import com.github.andercamargo.apiusers.service.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/arquivos")
@RequiredArgsConstructor
public class ArquivoController {

    private final ArquivoService arquivoService;

    @Operation(summary = "Entrega uma lista com todos os arquivos existentes no sistema")
    @ApiResponse(responseCode = "200", description = "Lista dos arquivos existentes no sistema")
    @GetMapping
    public ResponseEntity<List<ArquivoDTO>> listarArquivos(@RequestParam(value = "idUsuario", required = false) Long idUsuario) {
        return idUsuario == null ?
                ResponseEntity.ok(arquivoService.listarTodosArquivos())
                : ResponseEntity.ok(arquivoService.listarArquivosUsuario(idUsuario));
    }
}
