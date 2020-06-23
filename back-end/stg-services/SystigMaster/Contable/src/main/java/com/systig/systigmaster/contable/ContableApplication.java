package com.systig.systigmaster.contable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.systig.base","com.systig.systigmaster.contable"})
@EnableJpaRepositories(basePackages = "com.systig.base")
public class ContableApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContableApplication.class, args);
    }

}
