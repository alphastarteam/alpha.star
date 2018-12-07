package com.example.demouser.controller;

import com.example.demouser.model.Response;

public class BaseController {
    public Response CreateResponse(Boolean success, String message, Object content) {
        var response = new Response();
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
