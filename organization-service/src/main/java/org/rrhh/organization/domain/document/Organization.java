package org.rrhh.organization.domain.document;

public class Organization {

    private final OrganizationID id;
    private final OrganizationName name;
    private final OrganizationDescription description;
    private final OrganizationCode code;
    private final CreationDate creationDate;

    public Organization(OrganizationID id,
                        OrganizationName name,
                        OrganizationDescription description,
                        OrganizationCode code,
                        CreationDate creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.creationDate = creationDate;
    }

    public static OrganizationBuilder builder() {
        return new OrganizationBuilder();
    }

    public OrganizationID getId() {
        return id;
    }

    public OrganizationName getName() {
        return name;
    }

    public OrganizationDescription getDescription() {
        return description;
    }

    public OrganizationCode getCode() {
        return code;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public static class OrganizationBuilder {

        private OrganizationID id;
        private OrganizationName name;
        private OrganizationDescription description;
        private OrganizationCode code;
        private CreationDate creationDate;

        public OrganizationBuilder id(String id) {
            this.id = new OrganizationID(id);
            return this;
        }

        public OrganizationBuilder name(String name) {
            this.name = new OrganizationName(name);
            return this;
        }

        public OrganizationBuilder description(String description) {
            this.description = new OrganizationDescription(description);
            return this;
        }

        public OrganizationBuilder code(String code) {
            this.code = new OrganizationCode(code);
            return this;
        }

        public OrganizationBuilder creationDate(String creationDate) {
            this.creationDate = new CreationDate(creationDate);
            return this;
        }

        public Organization build() {
            return new Organization(id, name, description, code, creationDate);
        }
    }
}
