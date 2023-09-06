package org.rrhh.employee.domain.document;

import org.rrhh.department.domain.document.Department;
import org.rrhh.organization.domain.document.Organization;

public class Employee {

    private final EmployeeID id;
    private final EmployeeFirstName firstName;
    private final EmployeeLastName lastName;
    private final EmployeeEmail email;
    private final EmployeeDepartmentCode departmentCode;
    private final EmployeeOrganizationCode organizationCode;
    private final EmployeeDepartment department;
    private final EmployeeOrganization organization;

    public Employee(EmployeeID id,
                    EmployeeFirstName firstName,
                    EmployeeLastName lastName,
                    EmployeeEmail email,
                    EmployeeDepartmentCode departmentCode,
                    EmployeeOrganizationCode organizationCode,
                    EmployeeDepartment department,
                    EmployeeOrganization organization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentCode = departmentCode;
        this.organizationCode = organizationCode;
        this.department = department;
        this.organization = organization;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public EmployeeID getId() {
        return id;
    }

    public EmployeeFirstName getFirstName() {
        return firstName;
    }

    public EmployeeLastName getLastName() {
        return lastName;
    }

    public EmployeeEmail getEmail() {
        return email;
    }

    public EmployeeDepartmentCode getDepartmentCode() {
        return departmentCode;
    }

    public EmployeeOrganizationCode getOrganizationCode() {
        return organizationCode;
    }

    public EmployeeDepartment getDepartment() {
        return department;
    }

    public EmployeeOrganization getOrganization() {
        return organization;
    }

    public static class EmployeeBuilder {

        private EmployeeID id;
        private EmployeeFirstName firstName;
        private EmployeeLastName lastName;
        private EmployeeEmail email;
        private EmployeeDepartmentCode departmentCode;
        private EmployeeOrganizationCode organizationCode;
        private EmployeeDepartment department;
        private EmployeeOrganization organization;

        public EmployeeBuilder id(String id) {
            this.id = new EmployeeID(id);
            return this;
        }

        public EmployeeBuilder firstName(String firstName) {
            this.firstName = new EmployeeFirstName(firstName);
            return this;
        }

        public EmployeeBuilder lastName(String lastName) {
            this.lastName = new EmployeeLastName(lastName);
            return this;
        }

        public EmployeeBuilder email(String email) {
            this.email = new EmployeeEmail(email);
            return this;
        }

        public EmployeeBuilder departmentCode(String departmentCode) {
            this.departmentCode = new EmployeeDepartmentCode(departmentCode);
            return this;
        }

        public EmployeeBuilder organizationCode(String organizationCode) {
            this.organizationCode = new EmployeeOrganizationCode(organizationCode);
            return this;
        }

        public EmployeeBuilder department(Department department) {
            this.department = new EmployeeDepartment(department);
            return this;
        }

        public EmployeeBuilder organization(Organization organization) {
            this.organization = new EmployeeOrganization(organization);
            return this;
        }

        public Employee build() {
            return new Employee(id,
                    firstName,
                    lastName,
                    email,
                    departmentCode,
                    organizationCode,
                    department,
                    organization);
        }
    }
}
