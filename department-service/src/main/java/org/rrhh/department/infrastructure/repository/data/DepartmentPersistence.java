package org.rrhh.department.infrastructure.repository.data;

import org.rrhh.department.infrastructure.repository.document.DepartmentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentPersistence extends MongoRepository<DepartmentDocument, String> {

    boolean existsByName(String name);

    Optional<DepartmentDocument> findByCode(String code);

    boolean existsByCode(String code);
}
