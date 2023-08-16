package org.rrhh.organization.domain.repository;

import org.rrhh.organization.domain.document.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {

    Organization save(Organization organization);

    Optional<Organization> findByCode(String code);

    List<Organization> findAll();

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
