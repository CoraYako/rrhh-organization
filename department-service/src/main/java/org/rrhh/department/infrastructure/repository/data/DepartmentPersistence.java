package org.rrhh.department.infrastructure.repository.data;

import org.rrhh.department.infrastructure.repository.document.DepartmentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentPersistence extends MongoRepository<DepartmentDocument, String> {

    boolean existsByName(String name);

    Optional<DepartmentDocument> findByCode(String code);
}
