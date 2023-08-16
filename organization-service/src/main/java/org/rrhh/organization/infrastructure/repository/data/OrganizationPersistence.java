package org.rrhh.organization.infrastructure.repository.data;

import org.rrhh.organization.infrastructure.repository.document.OrganizationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganizationPersistence extends MongoRepository<OrganizationDocument, String> {

    Optional<OrganizationDocument> findByCode(String code);

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
