package com.javarush.springbootdouble.service.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundRuntimeException extends RuntimeException {

    private final EntityType entityType;
    public EntityNotFoundRuntimeException(String message, EntityType entityType) {
        super(message);
        this.entityType = entityType;
    }
}
