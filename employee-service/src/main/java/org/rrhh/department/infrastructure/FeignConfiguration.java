package org.rrhh.department.infrastructure;

import org.rrhh.department.domain.DepartmentConstants;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(value = DepartmentConstants.PACKAGE)
public class FeignConfiguration {
}
