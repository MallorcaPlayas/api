package org.example.apirest.error.file_exceptions;

public class InvalidFileMimeTypeException extends InvalidFileException {
    public InvalidFileMimeTypeException(String providedMime) {
        super("File MIME type not allowed : " + providedMime);
    }
}
