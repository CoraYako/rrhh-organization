package org.rrhh.organization.infrastructure.repository.document;

import lombok.Builder;
import lombok.Getter;
import org.rrhh.organization.domain.OrganizationConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = OrganizationConstants.COLLECTION_NAME)
@Builder
@Getter
public class OrganizationDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private String code;
    private LocalDateTime creationDate;
}
