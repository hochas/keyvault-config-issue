package com.demo.azure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class ProvisioningConfiguration {

    @Value("${gate-provisioningEndpoint}")
    private String endpoint;

    @Value("${gate-provisioningIdScope}")
    private String idScope;
}
