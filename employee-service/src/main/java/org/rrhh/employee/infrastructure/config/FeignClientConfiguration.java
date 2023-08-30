package org.rrhh.employee.infrastructure.config;

import feign.Logger;
import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.organization.domain.OrganizationConstants;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(value = {DepartmentConstants.PACKAGE, OrganizationConstants.PACKAGE})
public class FeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
