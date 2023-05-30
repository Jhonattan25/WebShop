package co.edu.uniquindio.webshop.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public String myString() {
        return "WebApp";
    }
}
