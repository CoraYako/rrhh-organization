package org.rrhh.organization.domain.repository;

import org.rrhh.organization.domain.document.Organization;

public interface OrganizationRepository {

    Organization findByCode(String code);
}
