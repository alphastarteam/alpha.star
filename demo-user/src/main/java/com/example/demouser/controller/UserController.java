package com.example.demouser.controller;

import com.example.demouser.model.*;
import com.example.demouser.service.UserService;
import com.example.serviceclient.UserServiceClient;
import com.example.serviceclient.model.*;
import model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by hcmony on 2017/9/5.
 */
@RestController
public class UserController extends BaseController implements UserServiceClient {

    @Autowired
    UserService userService;

    @Override
    @RequestMapping(value = "/user/findByUsername", method = RequestMethod.GET)
    public Response<User> findByUsername(@RequestBody FindByUsername findByUsername) {
        var user = userService.findUserByUsername(findByUsername.getUsername());

        return getResponse(user);
    }

    private Response<User> getResponse(com.example.demouser.dataaccess.model.User user) {
        if (user == null) {
            return Success();
        }
        var responseUser = new User();
        responseUser.setId(user.getId());
        responseUser.setUsername(user.getUsername());
        return Success(responseUser);
    }

    @Override
    @RequestMapping(value = "/user/findById", method = RequestMethod.POST)
    public Response findById(@RequestBody FindByUserId findByUserId) {
        var user = userService.findUserById(findByUserId.getUserId());
        return getResponse(user);
    }

    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public Response reg(@Valid @RequestBody RegUser user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        userService.reg(user);
        return Success();
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Response login(@Valid @RequestBody LoginUser user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        user.setIp(request.getRemoteAddr());
        var token = userService.login(user);
        return Success("登录成功", token);
    }

    @Override
    @RequestMapping(value = "/user/getOnlineUserInfo", method = RequestMethod.POST)
    public Response getOnlineUserInfo(@RequestBody FindByToken userAuthToken) {
        var loginUser = userService.getOnlineUserInfo(userAuthToken.getToken());
        return Success(loginUser);
    }

    @Override
    @RequestMapping(value = "/user/findByIds", method = RequestMethod.POST)
    public Response<List<User>> findByIds(@RequestBody FindByIds findByIds) {
        var users = userService.findUserByIds(findByIds.getIds());
        var responseUsers = users.stream().map(x -> {
            var user = new User();
            user.setId(x.getId());
            user.setUsername(x.getUsername());
            return user;
        }).collect(Collectors.toList());
        return Success(responseUsers);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public Response logout(@RequestBody UserAuthToken userAuthToken) {
        if (userAuthToken.getToken() == null || userAuthToken.getToken().equals("")) {
            return Success();
        }
        userService.logout(userAuthToken.getToken());
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
