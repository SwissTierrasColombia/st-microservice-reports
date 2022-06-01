package com.ai.st.microservice.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StMicroserviceReportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StMicroserviceReportsApplication.class, args);
    }

}