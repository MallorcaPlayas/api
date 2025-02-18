package org.example.apirest.controller.validators;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Predicate;

public interface Validator {
    boolean validate(Object... objects);
    default boolean validate(Predicate<Object[]> callBack, Object... object){
        return this.validate(object) && callBack.test(object);
    }
}
