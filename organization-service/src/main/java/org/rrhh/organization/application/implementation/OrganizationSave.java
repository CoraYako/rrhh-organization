package org.rrhh.organization.application.implementation;

import org.rrhh.organization.application.usecase.OrganizationExistsByCodeUseCase;
import org.rrhh.organization.application.usecase.OrganizationExistsByNameUseCase;
import org.rrhh.organization.application.usecase.OrganizationSaveUseCase;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrganizationSave implements OrganizationSaveUseCase {

    private final OrganizationRepository organizationRepository;
    private final OrganizationExistsByNameUseCase organizationExistsByName;
    private final OrganizationExistsByCodeUseCase organizationExistsByCode;

    public OrganizationSave(OrganizationRepository organizationRepository,
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
        organizationExistsByName.existsByName(organizationName);
        organizationExistsByCode.existsByCode(organizationCode);

        return organizationRepository.save(organization);
    }
}
