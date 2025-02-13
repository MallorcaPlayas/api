package org.example.apirest.error.photo_exceptions;

public class PhotoException extends RuntimeException {
    public PhotoException(String message) {
        super("Not valid photo : " + message);
    }
}
