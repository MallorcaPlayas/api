package org.example.apirest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeachNotFoundException extends RuntimeException{
    public BeachNotFoundException(Long id) {
        super("Beach with id " + id + " not found");
    }
}
