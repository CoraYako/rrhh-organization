package org.rrhh.organization.domain.usecase;

import org.rrhh.organization.domain.document.Organization;

public interface OrganizationCreateUseCase {

    Organization createOrganization(Organization organization);
}
