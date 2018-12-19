package com.example.demouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServerApplication.class, args);
    }
}
