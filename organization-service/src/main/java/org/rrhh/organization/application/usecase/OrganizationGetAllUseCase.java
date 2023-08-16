package org.rrhh.organization.application.usecase;

import org.rrhh.organization.domain.document.Organization;

import java.util.List;

public interface OrganizationGetAllUseCase {

    List<Organization> getOrganizations();
}