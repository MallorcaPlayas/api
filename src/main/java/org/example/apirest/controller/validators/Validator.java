package org.example.apirest.controller.validators;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Predicate;

public interface Validator {
    public boolean validate(Object... objects);
    public boolean validate(Predicate<Object[]> callBack, Object... object);
}
