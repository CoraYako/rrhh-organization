package org.rrhh.organization.application;

import org.rrhh.organization.domain.OrganizationConstants;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.rrhh.organization.domain.usecase.OrganizationCreateUseCase;
import org.rrhh.organization.domain.usecase.OrganizationExistsByCodeUseCase;
import org.rrhh.organization.domain.usecase.OrganizationExistsByNameUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class OrganizationCreateImpl implements OrganizationCreateUseCase {

    private final OrganizationRepository organizationRepository;
    private final OrganizationExistsByNameUseCase organizationExistsByName;
    private final OrganizationExistsByCodeUseCase organizationExistsByCode;

    public OrganizationCreateImpl(OrganizationRepository organizationRepository,
                                  OrganizationExistsByNameUseCase organizationExistsByName,
                                  OrganizationExistsByCodeUseCase organizationExistsByCode) {
        this.organizationRepository = organizationRepository;
        this.organizationExistsByName = organizationExistsByName;
        this.organizationExistsByCode = organizationExistsByCode;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        if (Objects.isNull(organization))
            throw new NullParameterException("Organization");

        String organizationName = organization.getName().getValue();
        String organizationCode = organization.getCode().getValue();
        organizationExistsByName.existsOrganizationByName(organizationName);
        organizationExistsByCode.existsOrganizationByCode(organizationCode);

        String creationDate = OrganizationConstants.FORMATTER.format(LocalDateTime.now());

        Organization organizationToSave = Organization.builder()
                .name(organizationName)
                .description(organization.getDescription().getValue())
                .code(organizationCode)
                .creationDate(creationDate)
                .build();

        return organizationRepository.save(organizationToSave);
    }
}
