package org.example.apirest.error.file_exceptions;

public class InvalidFileSizeException extends InvalidFileException {
    public InvalidFileSizeException(String maxSizeAllowed ,String sizeProvided) {
        super("Maximum size allowed :" + maxSizeAllowed  + " , size provided" + sizeProvided);
    }
}
