package com.code.challenge.socialnetwork.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IdGenerator {
    private final static Logger LOGGER = LoggerFactory.getLogger(IdGenerator.class);

    private Map<String, Long> idByEntity = new HashMap<>();

    public Long getIdByEntity(String entity) {
        Long nextId = idByEntity.get(entity);

        if (nextId == null) {
            nextId = 1L;
        }

        idByEntity.put(entity, nextId + 1);

        LOGGER.info("For entity - \"" + entity + "\" the current id: " + nextId);

        return nextId;
    }
}
