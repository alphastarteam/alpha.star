package com.example.demospringzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class DemoSpringzuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringzuulApplication.class, args);
    }
}
