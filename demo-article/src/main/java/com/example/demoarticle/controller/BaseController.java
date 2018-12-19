package com.example.demoarticle.controller;

import com.example.serviceclient.UserServiceClient;
import com.example.serviceclient.model.User;
import model.Response;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    private User user;

    @Autowired
    UserServiceClient userService;

    @Autowired
    HttpServletRequest request;

    public <T> Response<T> CreateResponse(Boolean success, String message, T content) {
        var response = new Response<T>();
        response.setContent(content);
        response.setMessage(message);
        response.setSuccess(success);
        return response;
    }

    public <T> Response<T> Success() {
        return CreateResponse(true, null, null);
    }

    public Response Success(String message) {
        return CreateResponse(true, message, null);
    }

    public <T> Response<T> Success(T content) {
        return CreateResponse(true, null, content);
    }

    public <T> Response<T> Success(String message, T content) {
        return CreateResponse(true, message, content);
    }

    public Response Error() {
        return CreateResponse(false, "操作失败", null);
    }

    public <T> Response<T> Error(String message) {
        return CreateResponse(false, message, null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
