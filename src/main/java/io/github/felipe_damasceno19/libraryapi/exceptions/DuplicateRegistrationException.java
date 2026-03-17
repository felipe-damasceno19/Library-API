package io.github.felipe_damasceno19.libraryapi.exceptions;

public class DuplicateRegistrationException extends RuntimeException {
    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
