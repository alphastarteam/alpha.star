package com.example.demoarticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.serviceclient"})
public class DemoArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoArticleApplication.class, args);
    }
}
