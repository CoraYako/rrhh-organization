package org.rrhh.employee.domain;

public class Employee {

    private final EmployeeID id;
    private final EmployeeFirstName firstName;
    private final EmployeeLastName lastName;
    private final EmployeeEmail email;

    public Employee(EmployeeID id, EmployeeFirstName firstName, EmployeeLastName lastName, EmployeeEmail email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public static class EmployeeBuilder {

        private EmployeeID id;
        private EmployeeFirstName firstName;
        private EmployeeLastName lastName;
        private EmployeeEmail email;

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

        public Employee build() {
            return new Employee(id, firstName, lastName, email);
        }
    }
}
