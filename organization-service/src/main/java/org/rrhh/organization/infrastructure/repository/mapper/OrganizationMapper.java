package org.rrhh.organization.infrastructure.repository.mapper;

import jakarta.validation.constraints.NotNull;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.infrastructure.repository.document.OrganizationDocument;

public interface OrganizationMapper {

    OrganizationDocument toDocument(@NotNull Organization domain);

    OrganizationDocument toDocumentComplete(@NotNull Organization domain);

    Organization toDomain(@NotNull OrganizationDocument document);
}
