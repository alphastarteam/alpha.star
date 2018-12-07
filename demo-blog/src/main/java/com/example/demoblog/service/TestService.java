package com.example.demoblog.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "springcloud-server-user")
public interface TestService {

    @RequestMapping("/test")
    String test();
}
