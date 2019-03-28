package com.demo.azure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gate")
public class ProvisioningConfigurationWithConfigurationProperties {

    private String endpoint;

    private String idScope;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public String getIdScope() {
        return idScope;
    }

    public void setIdScope(final String idScope) {
        this.idScope = idScope;
    }

}
