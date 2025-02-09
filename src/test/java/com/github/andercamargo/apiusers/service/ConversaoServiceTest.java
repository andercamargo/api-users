package com.github.andercamargo.apiusers.service;

import com.github.andercamargo.apiusers.dto.ConteudoDTO;
import com.github.andercamargo.apiusers.entity.Arquivo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConversaoServiceTest {
    @Mock
    private ConversaoService<ConteudoDTO> conversaoService;

    @Test
    void conversaoConteudoComSucesso(){
        var  arquivo = new Arquivo("Livro de Arquitetura de Software", 1L, "titulo: teste");
        doReturn(ConteudoDTO.class).when(conversaoService).convert(arquivo.getConteudo(), ConteudoDTO.class);
    }
}
