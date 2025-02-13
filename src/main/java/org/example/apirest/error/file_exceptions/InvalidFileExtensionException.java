package org.example.apirest.error.file_exceptions;

public class InvalidFileExtensionException extends InvalidFileException {
    public InvalidFileExtensionException(String providedExtension) {
        super("File extension not allowed : " + providedExtension);
    }
}
