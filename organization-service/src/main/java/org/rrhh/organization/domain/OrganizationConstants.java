package org.rrhh.organization.domain;

import java.time.format.DateTimeFormatter;

public class OrganizationConstants {

    public static final String BASE_URI = "/api/v1/organizations";
    public static final String COLLECTION_NAME = "organizations";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
}
