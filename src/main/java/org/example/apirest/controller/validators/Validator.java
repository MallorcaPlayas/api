package org.example.apirest.controller.validators;

public interface Validator<T> {
    void validate(T t);
}
