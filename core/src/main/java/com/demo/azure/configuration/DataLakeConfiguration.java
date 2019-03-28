package com.demo.azure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gate")
public class DataLakeConfiguration {

    private String dataLakeClientSecret;

    private String dataLakeClientId;

    private String dataLakeFqdn;

    private String dataLakeAuthEndpoint;

    public String getDataLakeClientSecret() {
        return dataLakeClientSecret;
    }

    public void setDataLakeClientSecret(final String dataLakeClientSecret) {
        this.dataLakeClientSecret = dataLakeClientSecret;
    }

    public String getDataLakeClientId() {
        return dataLakeClientId;
    }

    public void setDataLakeClientId(final String dataLakeClientId) {
        this.dataLakeClientId = dataLakeClientId;
    }

    public String getDataLakeFqdn() {
        return dataLakeFqdn;
    }

    public void setDataLakeFqdn(final String dataLakeFqdn) {
        this.dataLakeFqdn = dataLakeFqdn;
    }

    public String getDataLakeAuthEndpoint() {
        return dataLakeAuthEndpoint;
    }

    public void setDataLakeAuthEndpoint(final String dataLakeAuthEndpoint) {
        this.dataLakeAuthEndpoint = dataLakeAuthEndpoint;
    }

}
