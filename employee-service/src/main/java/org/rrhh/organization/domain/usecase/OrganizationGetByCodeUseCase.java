package org.rrhh.organization.domain.usecase;

import org.rrhh.organization.domain.document.Organization;

public interface OrganizationGetByCodeUseCase {

    Organization getOrganizationByCode(String code);
}
