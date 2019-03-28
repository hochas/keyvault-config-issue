package com.demo.infrastructure;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import com.microsoft.azure.keyvault.spring.Constants;
import com.microsoft.azure.keyvault.spring.KeyVaultEnvironmentPostProcessor;

/**
 * Verifies and loads required properties to access the Azure Key Vault.
 */
public class KeyVaultContextInitializer implements EnvironmentPostProcessor, Ordered {

    private static final String KEYVAULT_PRINCIPAL = "keyVaultPrincipal";
    private static final String KEYVAULT_SECRET = "keyVaultSecret";
    private static final String KEYVAULT_URI = "keyVaultUri";

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {
        verifyKeysPresentInEnvironment(KEYVAULT_PRINCIPAL, KEYVAULT_SECRET, KEYVAULT_URI);

        final Properties propertiesFromEnvironment = createPropertiesFromEnvironment();
        environment.getPropertySources().addFirst(new PropertiesPropertySource("properties", propertiesFromEnvironment));

        System.out.println(format("Successfully initiated context with properties for Azure Key Vault at %s",
                propertiesFromEnvironment.getProperty(Constants.AZURE_KEYVAULT_VAULT_URI)));
    }

    private void verifyKeysPresentInEnvironment(final String... keys) {
        Arrays.stream(keys).forEach(verifyKeyPresentInEnvironment());
    }

    private Consumer<String> verifyKeyPresentInEnvironment() {
        final Map<String, String> propertyMap = getPropertyMap();
        return key -> {
            final String value = propertyMap.get(key);
            if (value == null || value.isEmpty()) {
                throw new RuntimeException(format("Couldn't find required property '%s' in environment", key));
            }
        };
    }

    private Properties createPropertiesFromEnvironment() {
        final Properties props = new Properties();
        final Map<String, String> environmentProperties = getPropertyMap();
        props.put(Constants.AZURE_KEYVAULT_CLIENT_ID, environmentProperties.get(KEYVAULT_PRINCIPAL));
        props.put(Constants.AZURE_KEYVAULT_CLIENT_KEY, environmentProperties.get(KEYVAULT_SECRET));
        props.put(Constants.AZURE_KEYVAULT_VAULT_URI, environmentProperties.get(KEYVAULT_URI));
        return props;
    }

    protected Map<String, String> getPropertyMap() {
        return System.getenv();
    }

    @Override
    public int getOrder() {
        return KeyVaultEnvironmentPostProcessor.DEFAULT_ORDER - 1;
    }
}
