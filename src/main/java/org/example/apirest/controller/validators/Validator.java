package org.example.apirest.controller.validators;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Predicate;

public interface Validator<T extends Object> {
//    public boolean validate(List<T> t);
//    public boolean validate(Predicate<List<T>> callBack, List<T> t);
//
//---------------------------------------------------------------------------------------------------------------------------

    public boolean validate(Object... objects);
    public boolean validate(Predicate<List<Object>> callBack, List<Object> t);
}
