package com.demo;

import java.util.Arrays;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import com.demo.azure.configuration.DataLakeConfiguration;
import com.demo.azure.configuration.ProvisioningConfiguration;
import com.demo.azure.configuration.ProvisioningConfigurationWithConfigurationProperties;
import com.demo.azure.configuration.SpringProperties;

/**
 * Starts the module.
 */
@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Autowired
    public DataLakeConfiguration config;

    @Autowired
    public ProvisioningConfiguration config2;

    @Autowired
    public ProvisioningConfigurationWithConfigurationProperties config3;

    @Autowired
    public SpringProperties config4;

    @Autowired
    public Environment environment;

    @PostConstruct
    public void post() {
        final DataLakeConfiguration asdf = config;
        final ProvisioningConfiguration qwerty = config2;
        final ProvisioningConfigurationWithConfigurationProperties foo = config3;
        final SpringProperties bar = config4;

        for(final Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
            final PropertySource propertySource = (PropertySource) it.next();
            System.out.println(propertySource.getClass());
            if (propertySource instanceof EnumerablePropertySource) {
                Arrays.stream(((EnumerablePropertySource) propertySource).getPropertyNames()).forEach(n -> {
                    System.out.println(n + "=" + propertySource.getProperty(n));
                });
            }
        }
    }
}
