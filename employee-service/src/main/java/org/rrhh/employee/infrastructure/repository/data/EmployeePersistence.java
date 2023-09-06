package org.rrhh.employee.infrastructure.repository.data;

import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeePersistence extends MongoRepository<EmployeeDocument, String> {

    boolean existsByEmail(String email);
}
