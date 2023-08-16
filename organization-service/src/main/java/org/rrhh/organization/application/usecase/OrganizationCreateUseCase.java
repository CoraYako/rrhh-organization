package org.rrhh.organization.application.usecase;

import org.rrhh.organization.domain.document.Organization;

public interface OrganizationCreateUseCase {

    Organization createOrganization(Organization organization);
}
