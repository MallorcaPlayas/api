package org.example.apirest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(Class<?> entityClass,Long id) {
        super(entityClass.getSimpleName() + " with id " + id + " not found");
    }
}
