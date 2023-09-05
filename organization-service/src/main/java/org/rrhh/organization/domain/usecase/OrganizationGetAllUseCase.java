package org.rrhh.organization.domain.usecase;

import org.rrhh.organization.domain.document.Organization;

import java.util.List;

public interface OrganizationGetAllUseCase {

    List<Organization> getOrganizations();
}
