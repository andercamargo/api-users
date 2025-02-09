package com.github.andercamargo.apiusers.controller;

import com.github.andercamargo.apiusers.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsuarioControllerTest {
    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private Long idUsuario;

    @BeforeEach
    void setup() {
        idUsuario = 3L;
    }

    @Test
    void detalharUsuarioComSucesso() {
        doReturn(null).when(usuarioService).detalharUsuario(idUsuario);
        var usuario = usuarioController.detalharUsuario(idUsuario);

        assertNotNull(usuario);
        assert (usuario.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertFalse(usuario.hasBody());
    }


    @Test
    void listaVaziaAolistarTodosUsuariosComSucesso() {
        doReturn(List.of()).when(usuarioService).listarTodos();
        var usuarios = usuarioController.listarUsuarios();

        assertNotNull(usuarios);
        assertNotNull(usuarios.getBody());
        assert (usuarios.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    void detalharUsuarioSimplificadoComSucesso() {
        doReturn(null).when(usuarioService).detalharUsuarioSimplificado(idUsuario);
        var usuario = usuarioController.detalharUsuarioSimplificado(idUsuario);

        assertNotNull(usuario);
        assert (usuario.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertFalse(usuario.hasBody());
    }

    @Test
    void detalharUsuarioSimplificadoNotFound() {
        doReturn(null).when(usuarioService).detalharUsuarioSimplificado(null);
        var usuario = usuarioController.detalharUsuarioSimplificado(null);

        assertNotNull(usuario);
        assert (usuario.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertFalse(usuario.hasBody());
    }
}
