package org.rrhh.department.infrastructure.repository.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
@Builder
@Getter
public class DepartmentDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private String code;
}
