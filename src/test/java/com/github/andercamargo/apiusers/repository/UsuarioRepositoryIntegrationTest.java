package com.github.andercamargo.apiusers.repository;

import com.github.andercamargo.apiusers.entity.Usuario;
import com.github.andercamargo.apiusers.enumeration.StatusUsuarioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UsuarioRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setup() {
        usuario = new Usuario(
            "Teste",
                "teste@teste.com",
                "{}",
                LocalDate.of(1993, Month.MAY, 20),
                StatusUsuarioEnum.INATIVO.getValor()
        );
    }

    @Test
    void listarUsuario() {
        var usuarioinserido =  entityManager.persist(usuario);
        entityManager.flush();

        usuario.setId(usuarioinserido.getId());

        var usuarioTeste = usuarioRepository.findById(Long.valueOf(usuarioinserido.getId()));

        assertFalse(usuarioTeste.isEmpty());
        assert (usuarioTeste.get().equals(usuario));

        clear(usuario);
    }


    @Test
    void listarTodosUsuarios() {
        var usuarios = usuarioRepository.findAll();
        var lista = new ArrayList<Usuario>();

        assertFalse(usuarios.isEmpty());
        assertEquals(usuarios.getClass(), lista.getClass());
    }

    private void clear(Usuario usuario) {
        entityManager.detach(usuario);
        entityManager.flush();
    }
}
