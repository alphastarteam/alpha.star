package com.example.demouser.exception;

import com.example.demouser.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = OperationException.class)
    @ResponseBody
    public Response runtimeExceptionHandler(OperationException oe) {
        var response = new Response();
        response.setSuccess(false);
        response.setMessage(oe.getMessage());
        return response;
    }
}
