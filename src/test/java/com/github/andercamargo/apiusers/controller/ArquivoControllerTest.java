package com.github.andercamargo.apiusers.controller;

import com.github.andercamargo.apiusers.service.ArquivoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArquivoControllerTest {

    @InjectMocks
    private ArquivoController arquivoController;

    @Mock
    private ArquivoService arquivoService;

    @Test
    void listaVaziaAolistarTodosArquivosComSucesso() {
        doReturn(List.of()).when(arquivoService).listarTodosArquivos();
        var arquivos = arquivoController.listarArquivos(null);

        assertNotNull(arquivos.getBody());
        assertTrue(arquivos.getBody().isEmpty());

        assert (arquivos.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void listaVaziaAolistarArquivoUsuarioComSucesso() {
        doReturn(List.of()).when(arquivoService).listarArquivosUsuario(1L);
        var arquivos = arquivoController.listarArquivos(null);

        assertNotNull(arquivos.getBody());
        assertTrue(arquivos.getBody().isEmpty());

        assert (arquivos.getStatusCode().equals(HttpStatus.OK));
    }
}
