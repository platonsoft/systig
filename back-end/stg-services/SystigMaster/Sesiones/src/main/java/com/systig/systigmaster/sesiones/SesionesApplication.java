package com.systig.systigmaster.sesiones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"com.systig.base","com.systig.systigmaster.sesiones"})
@EnableJpaRepositories(basePackages = "com.systig.base")
public class SesionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SesionesApplication.class, args);
    }

}
