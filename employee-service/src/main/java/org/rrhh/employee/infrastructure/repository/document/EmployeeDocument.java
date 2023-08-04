package org.rrhh.employee.infrastructure.repository.document;

import lombok.Builder;
import lombok.Getter;
import org.rrhh.employee.domain.EmployeeConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = EmployeeConstants.COLLECTION_NAME)
@Builder
@Getter
public class EmployeeDocument {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
}
