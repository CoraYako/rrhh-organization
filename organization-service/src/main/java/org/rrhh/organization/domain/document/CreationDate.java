package org.rrhh.organization.domain.document;

import java.time.LocalDateTime;

public class CreationDate {

    private LocalDateTime value;

    public CreationDate(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    public void setValue(LocalDateTime value) {
        this.value = value;
    }
}
