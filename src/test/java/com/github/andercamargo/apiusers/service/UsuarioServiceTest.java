package com.github.andercamargo.apiusers.service;

import com.github.andercamargo.apiusers.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsuarioServiceTest {
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    private Long idUsuario;


    @BeforeEach
    void setup(){
        idUsuario = 1L;
    }


    @Test
    void listarTodosUsuariosVazio(){

        doReturn(List.of()).when(usuarioService).listarTodos();
        var usuarios = usuarioService.listarTodos();

        assertTrue(usuarios.isEmpty());
    }


    @Test
    void listarUsuarioDetalhadoVazio(){
        doReturn(null).when(usuarioService).detalharUsuario(idUsuario);

        var usuarioTest = usuarioService.detalharUsuario(idUsuario);
        assertNull(usuarioTest);
    }

    @Test
    void listarUsuarioSimglicadoVazio(){
        doReturn(null).when(usuarioService).detalharUsuarioSimplificado(idUsuario);

        var usuarioTest = usuarioService.detalharUsuarioSimplificado(idUsuario);
        assertNull(usuarioTest);
    }
}
