package com.github.andercamargo.apiusers.service;

import com.github.andercamargo.apiusers.repository.ArquivoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArquivoServiceTest {
    @Mock
    private ArquivoService arquivoService;

    @Mock
    private ArquivoRepository arquivoRepository;

    @Test
    void listarTodosArquivosVazio(){

        doReturn(List.of()).when(arquivoService).listarTodosArquivos();
        var arquivos = arquivoService.listarTodosArquivos();

        assertTrue(arquivos.isEmpty());
    }

    @Test
    void listarArquivosUsuarioVazio(){

        doReturn(List.of()).when(arquivoService).listarArquivosUsuario(0L);
        var arquivos = arquivoService.listarArquivosUsuario(0L);

        assertTrue(arquivos.isEmpty());
    }
}
