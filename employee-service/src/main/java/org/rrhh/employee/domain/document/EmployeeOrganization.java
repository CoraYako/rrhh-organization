package org.rrhh.employee.domain.document;

import org.rrhh.organization.domain.document.Organization;

public class EmployeeOrganization {

    private Organization value;

    public EmployeeOrganization(Organization value) {
        this.value = value;
    }

    public Organization getValue() {
        return value;
    }

    public void setValue(Organization value) {
        this.value = value;
    }
}
