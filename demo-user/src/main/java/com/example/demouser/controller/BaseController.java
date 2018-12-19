package com.example.demouser.controller;

import model.Response;

public class BaseController {
    public <T> Response<T> CreateResponse(Boolean success, String message, T content) {
        var response = new Response<T>();
        response.setContent(content);
        response.setMessage(message);
        response.setSuccess(success);
        return response;
    }

    public Response Success() {
        return CreateResponse(true, null, null);
    }

    public Response Success(String message) {
        return CreateResponse(true, message, null);
    }

    public Response Success(Object content) {
        return CreateResponse(true, null, content);
    }

    public Response Success(String message, Object content) {
        return CreateResponse(true, message, content);
    }

    public Response Error() {
        return CreateResponse(false, "操作失败", null);
    }

    public Response Error(String message) {
        return CreateResponse(false, message, null);
    }
}
