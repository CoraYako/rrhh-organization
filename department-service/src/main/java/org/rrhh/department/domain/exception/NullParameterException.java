package org.rrhh.department.domain.exception;

public class NullParameterException extends RuntimeException{

    private String resourceName;

    public NullParameterException(String resourceName) {
        super(String.format("%s must not be null", resourceName));
        this.resourceName = resourceName;
    }
}
