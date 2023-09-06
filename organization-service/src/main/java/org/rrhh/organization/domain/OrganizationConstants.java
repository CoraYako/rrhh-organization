package org.rrhh.organization.domain;

import java.time.format.DateTimeFormatter;

public class OrganizationConstants {

    public static final String BASE_URI = "/api/v1/organizations";
    public static final String COLLECTION_NAME = "organizations";
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
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
}
