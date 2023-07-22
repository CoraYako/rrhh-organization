package org.rrhh.employee.domain.exception;

public class EmptyListException extends RuntimeException {

    private String message;

    public EmptyListException(String message) {
        super(String.format(message));
        this.message = message;
    }
}
