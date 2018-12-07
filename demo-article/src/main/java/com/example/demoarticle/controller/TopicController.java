package com.example.demoarticle.controller;

import com.example.demoarticle.dataaccess.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hcmony on 2017/9/5.
 */
@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Value("${server.port}")
    String port;

    @RequestMapping("/test")
    public String test(){
        var count=topicRepository.count();
        return "有"+count+"条数据";
    }
}
