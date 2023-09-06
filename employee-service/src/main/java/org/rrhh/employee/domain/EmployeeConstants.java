package org.rrhh.employee.domain;

public class EmployeeConstants {

    public static final String BASE_URI = "/api/v1/employees";
    public static final String COLLECTION_NAME = "employees";
    public static final String[] ALLOWED_ORIGINS = {
            "http://localhost:9191",
            "https://localhost:9191",
            "https://localhost",
            "http://localhost"
    };
    public static final String[] ALLOWED_METHODS = {
            "GET",
            "POST",
            "PUT",
            "PATCH",
            "DELETE"
    };
    public static final String[] ALLOWED_HEADERS = {
            "Origin",
            "Content-Type",
            "Accept"
    };
}
