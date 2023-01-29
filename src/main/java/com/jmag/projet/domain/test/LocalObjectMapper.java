package com.jmag.projet.domain.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmag.projet.domain.exceptions.PlanerTreeServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalObjectMapper {

    private final ObjectMapper mapper;

    public <T> T convertObject(String jsonString, Class<T> objectClass) {

        try {
            JsonNode jsonObj = mapper.readTree(jsonString);
            return mapper.convertValue(jsonObj, objectClass);
        } catch (JsonProcessingException e) {
            throw new PlanerTreeServerException(PlanerTreeServerException.DEFAULT_ERROR);
        }
    }

    public <T> String convertToString(T obj) {

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new PlanerTreeServerException(PlanerTreeServerException.DEFAULT_ERROR);
        }
    }
}
