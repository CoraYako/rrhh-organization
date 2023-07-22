package org.rrhh.employee.infrastructure.repository.data;

import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePersistence extends MongoRepository<EmployeeDocument, String> {

    boolean existsByEmail(String email);
}
