package org.example.apirest.error.photo_exceptions;

public class PhotoNotAssignedException extends PhotoException {
    public PhotoNotAssignedException() {
        super("Photo not assigned anywhere");
    }
}
