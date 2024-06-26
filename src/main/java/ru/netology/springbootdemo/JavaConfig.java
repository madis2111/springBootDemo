package ru.netology.springbootdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springbootdemo.service.DevProfile;
import ru.netology.springbootdemo.service.ProductionProfile;
import ru.netology.springbootdemo.service.SystemProfile;

@Configuration
public class JavaConfig {
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false")
    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
