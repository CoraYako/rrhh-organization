package org.rrhh.department.domain;

public class DepartmentConstants {

    public static final String BASE_URI = "/api/v1/departments";
    public static final String COLLECTION_NAME = "departments";
    public static final String[] ALLOWED_ORIGINS = {
            "http://localhost:9191",
            "https://localhost:9191",
            "https://localhost",
            "http://localhost",
            "http://api-gateway:9191",
            "http://api-gateway",
            "http://172.17.0.2:9191",
            "http://172.17.0.2"
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
