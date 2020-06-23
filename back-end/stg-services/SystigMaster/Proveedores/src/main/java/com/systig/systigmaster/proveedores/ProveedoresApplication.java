package com.systig.systigmaster.proveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.systig.base","com.systig.systigmaster.proveedores"})
@EnableJpaRepositories(basePackages = "com.systig.base")
public class ProveedoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProveedoresApplication.class, args);
    }

}
