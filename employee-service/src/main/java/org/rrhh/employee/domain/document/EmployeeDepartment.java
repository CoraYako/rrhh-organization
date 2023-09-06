package org.rrhh.employee.domain.document;

import org.rrhh.department.domain.document.Department;

public class EmployeeDepartment {

    private Department value;

    public EmployeeDepartment(Department value) {
        this.value = value;
    }

    public Department getValue() {
        return value;
    }

    public void setValue(Department value) {
        this.value = value;
    }
}
