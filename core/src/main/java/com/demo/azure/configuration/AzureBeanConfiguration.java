package com.demo.azure.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwin;

/**
 * Factory of beans that require special configuration for Azure.
 */
@Configuration
public class AzureBeanConfiguration {

    private final DataLakeConfiguration dataLakeConfiguration;

    @Autowired
    public AzureBeanConfiguration(final DataLakeConfiguration dataLakeConfiguration) {
        this.dataLakeConfiguration = dataLakeConfiguration;
    }

    @Bean
    public DeviceTwin getDeviceTwin(@Value("${gate-twinReaderAccessToken}") final String twinReaderAccessToken) throws IOException {
        return DeviceTwin.createFromConnectionString(twinReaderAccessToken);
    }

    @Bean
    public ADLStoreClient getDataLakeClient() {
        final AccessTokenProvider provider = new ClientCredsTokenProvider(dataLakeConfiguration.getDataLakeAuthEndpoint(),
                dataLakeConfiguration.getDataLakeClientId(), dataLakeConfiguration.getDataLakeClientSecret());
        return ADLStoreClient.createClient(dataLakeConfiguration.getDataLakeFqdn(), provider);
    }
}
