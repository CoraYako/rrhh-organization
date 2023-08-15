package org.rrhh.organization.application.usecase;

import org.rrhh.organization.domain.document.Organization;

public interface OrganizationFindByCodeUseCase {

    Organization getOrganizationByCode(String code);
}
