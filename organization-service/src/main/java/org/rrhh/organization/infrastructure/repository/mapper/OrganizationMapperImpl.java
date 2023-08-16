package org.rrhh.organization.infrastructure.repository.mapper;

import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.infrastructure.repository.document.OrganizationDocument;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDocument toDocument(Organization domain) {
        return OrganizationDocument.builder()
                .name(domain.getName().getValue())
                .description(domain.getDescription().getValue())
                .code(domain.getCode().getValue())
                .creationDate(domain.getCreationDate().getValue())
                .build();
    }

    @Override
    public OrganizationDocument toDocumentComplete(Organization domain) {
        return OrganizationDocument.builder()
                .id(domain.getId().getValue())
                .name(domain.getName().getValue())
                .description(domain.getDescription().getValue())
                .code(domain.getCode().getValue())
                .creationDate(domain.getCreationDate().getValue())
                .build();
    }

    @Override
    public Organization toDomain(OrganizationDocument document) {
        return Organization.builder()
                .id(document.getId())
                .name(document.getName())
                .description(document.getDescription())
                .code(document.getCode())
                .creationDate(document.getCreationDate())
                .build();
    }
}
