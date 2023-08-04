package org.rrhh.employee.domain.document;

import org.rrhh.department.domain.document.Department;

public class Employee {

    private final EmployeeID id;
    private final EmployeeFirstName firstName;
    private final EmployeeLastName lastName;
    private final EmployeeEmail email;
    private final EmployeeDepartmentCode departmentCode;
    private final EmployeeDepartment department;

    public Employee(EmployeeID id,
                    EmployeeFirstName firstName,
                    EmployeeLastName lastName,
                    EmployeeEmail email,
                    EmployeeDepartmentCode departmentCode,
                    EmployeeDepartment department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentCode = departmentCode;
        this.department = department;
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

    public EmployeeDepartment getDepartment() {
        return department;
    }

    public static class EmployeeBuilder {

        private EmployeeID id;
        private EmployeeFirstName firstName;
        private EmployeeLastName lastName;
        private EmployeeEmail email;
        private EmployeeDepartmentCode departmentCode;
        private EmployeeDepartment department;

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

        public EmployeeBuilder department(Department department) {
            this.department = new EmployeeDepartment(department);
            return this;
        }

        public Employee build() {
            return new Employee(id, firstName, lastName, email, departmentCode, department);
        }
    }
}
