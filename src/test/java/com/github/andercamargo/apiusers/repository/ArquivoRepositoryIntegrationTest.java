package com.github.andercamargo.apiusers.repository;

import com.github.andercamargo.apiusers.entity.Arquivo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArquivoRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArquivoRepository arquivoRepository;

    private Long idUsuario;
    private Arquivo arquivo;

    @BeforeEach
    void setup() {
        idUsuario = 99L;
        arquivo = new Arquivo("Livro de Arquitetura de Software", idUsuario, "");
    }

    @Test
    void listarTodosArquivosUsuario() {

        entityManager.persist(arquivo);
        entityManager.flush();

        var arquivoTeste = arquivoRepository.findByIdUsuarioOwner(idUsuario);

        assertFalse(arquivoTeste.isEmpty());

        assert (arquivoTeste.getFirst().equals(arquivo));

        clear(arquivo);
    }

    @Test
    void listarTodosArquivosUsuarioNaoEncontrado() {
        var arquivoTeste = arquivoRepository.findByIdUsuarioOwner(null);
        assertTrue(arquivoTeste.isEmpty());
    }


    @Test
    void listarTodosArquivos() {
        var arquivos = arquivoRepository.findAll();
        var lista = new ArrayList<Arquivo>();

        assertFalse(arquivos.isEmpty());
        assertEquals(arquivos.getClass(), lista.getClass());

    }

    private void clear(Arquivo arquivo) {
        entityManager.detach(arquivo);
        entityManager.flush();
    }
}
