package com.example.serviceclient;

import com.example.serviceclient.model.*;
import model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "springcloud-server-user")
public interface UserServiceClient {

    @RequestMapping(value = "/user/findByUsername", method = RequestMethod.GET)
    Response<User> findByUsername(FindByUsername findByUsername);

    @RequestMapping(value = "/user/findById", method = RequestMethod.POST)
    Response<User> findById(@RequestBody FindByUserId findByUserId);

    @RequestMapping(value = "/user/getOnlineUserInfo", method = RequestMethod.POST)
    Response<User> getOnlineUserInfo(FindByToken userAuthToken);

    @RequestMapping(value = "/user/findByIds",method = RequestMethod.POST)
    Response<List<User>> findByIds(FindByIds findByIds);
}
