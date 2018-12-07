package com.example.demouser.controller;

import com.example.demouser.model.*;
import com.example.demouser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by hcmony on 2017/9/5.
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String count() {
        return "0";
    }

    @RequestMapping(value = "/user/findByUsername", method = RequestMethod.GET)
    public Response findByUsername(String username) {
        var user = userService.findUserByUsername(username);

        return getResponse(user);
    }

    private Response getResponse(com.example.demouser.dataaccess.model.User user) {
        if (user == null) {
            return Success();
        }
        var responseUser = new User();
        responseUser.setId(user.getId());
        responseUser.setUsername(user.getUsername());
        return Success(responseUser);
    }

    @RequestMapping(value = "/user/findById", method = RequestMethod.GET)
    public Response findById(Long userId) {
        var user = userService.findUserById(userId);

        return getResponse(user);
    }

    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public Response reg(@Valid RegUser user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        userService.create(user);
        return Success();
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Response login(@Valid LoginUser user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        user.setIp(request.getRemoteAddr());
        var token = userService.login(user);
        var cookie = new Cookie("token", token);
        cookie.setMaxAge(200);
        response.addCookie(cookie);
        return Success("登录成功", token);
    }

    @RequestMapping(value = "/user/getOnlineUserInfo", method = RequestMethod.POST)
    public Response getOnlineUserInfo(String token, HttpServletResponse response) {
        if (token == null || token.equals("")) {
            return Success();
        }
        var loginUser = userService.getOnlineUserInfo(token);
        if (loginUser == null) {
            var cookie = new Cookie("token", null);
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
        return Success(loginUser);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public Response logout(String token, HttpServletResponse response) {
        if (token == null || token.equals("")) {
            return Success();
        }
        userService.logout(token);
        var cookie = new Cookie("token", null);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return Success();
    }

    @RequestMapping(value = "/user/modifypassword", method = RequestMethod.POST)
    public Response ModifyPassword(String token, @Valid ModifyPassword modifyPassword, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        userService.modifyPassword(token, modifyPassword.getPassword(), modifyPassword.getNewPassword());
        return Success();
    }
}
