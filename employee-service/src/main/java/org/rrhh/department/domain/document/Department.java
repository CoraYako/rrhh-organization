package org.rrhh.department.domain.document;

public class Department {

    private final DepartmentID id;
    private final DepartmentName name;
    private final DepartmentDescription description;
    private final DepartmentCode code;

    public Department(DepartmentID id, DepartmentName name, DepartmentDescription description, DepartmentCode code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    public DepartmentID getId() {
        return id;
    }

    public DepartmentName getName() {
        return name;
    }

    public DepartmentDescription getDescription() {
        return description;
    }

    public DepartmentCode getCode() {
        return code;
    }

    public static class DepartmentBuilder {

        private DepartmentID id;
        private DepartmentName name;
        private DepartmentDescription description;
        private DepartmentCode code;

        public DepartmentBuilder id(String id) {
            this.id = new DepartmentID(id);
            return this;
        }

        public DepartmentBuilder name(String name) {
            this.name = new DepartmentName(name);
            return this;
        }

        public DepartmentBuilder description(String description) {
            this.description = new DepartmentDescription(description);
            return this;
        }

        public DepartmentBuilder code(String code) {
            this.code = new DepartmentCode(code);
            return this;
        }

        public Department build() {
            return new Department(id, name, description, code);
        }
    }
}
