package org.rrhh.department.domain.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String path, HttpCodeResponse errorCode) {

    public static ErrorDetailsBuilder builder() {
        return new ErrorDetailsBuilder();
    }

    public static class ErrorDetailsBuilder {

        private LocalDateTime timestamp;
        private String message;
        private String path;
        private HttpCodeResponse errorCode;

        public ErrorDetailsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDetailsBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorDetailsBuilder errorCode(HttpCodeResponse errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(timestamp, message, path, errorCode);
        }
    }
}
