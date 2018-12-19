package com.example.demoarticle.HandlerInterceptor;

import com.example.demoarticle.controller.BaseController;
import com.example.serviceclient.UserServiceClient;
import com.example.serviceclient.model.FindByToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    UserServiceClient userService;

    @Autowired
    ObjectMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var bean = ((HandlerMethod) handler).getBean();
        if (!(bean instanceof BaseController)) {
            return true;
        }
        var controller = (BaseController) bean;

        var tokenHeader = request.getHeader("token");
        if (tokenHeader != null) {
            var token = new FindByToken();
            token.setToken(tokenHeader);
            var userInfo = userService.getOnlineUserInfo(token);
            controller.setUser(userInfo.getContent());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        var bean = ((HandlerMethod) handler).getBean();
        if (!(bean instanceof BaseController)) {
            return;
        }
        var controller = (BaseController) bean;
        controller.setUser(null);
    }
}
