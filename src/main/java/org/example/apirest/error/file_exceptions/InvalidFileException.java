package org.example.apirest.error.file_exceptions;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String moreInformation) {
        super("Not valid file : " + moreInformation);
    }
}
