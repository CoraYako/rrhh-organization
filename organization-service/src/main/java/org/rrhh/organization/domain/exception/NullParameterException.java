package org.rrhh.organization.domain.exception;

public class NullParameterException extends RuntimeException{

    private String resourceName;

    public NullParameterException(String resourceName) {
        super(String.format("%s must not be null or empty.", resourceName));
        this.resourceName = resourceName;
    }
}
