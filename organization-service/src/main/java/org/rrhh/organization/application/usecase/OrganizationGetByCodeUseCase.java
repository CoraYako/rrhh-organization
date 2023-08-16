package org.rrhh.organization.application.usecase;

import org.rrhh.organization.domain.document.Organization;

public interface OrganizationGetByCodeUseCase {

    Organization getOrganizationByCode(String code);
}
