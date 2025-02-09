package com.github.andercamargo.apiusers.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ConversaoService<T> {
    private final ObjectMapper objectMapper;

    @Autowired
    public ConversaoService(){
        this.objectMapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false).findAndRegisterModules();
    }

    public T convert(String value, Class<T> type)  {
        try {
            return this.objectMapper.readValue(value, type);
        } catch (Exception e) {
            log.error("Erro na conversao do objeto. Metodo convert. ",  e);
            throw new RuntimeException(e);
        }
    }
}
