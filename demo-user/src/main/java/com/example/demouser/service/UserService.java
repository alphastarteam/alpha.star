package com.example.demouser.service;

import com.example.demouser.model.LoginUser;
import com.example.demouser.model.RegUser;
import com.example.demouser.dataaccess.model.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);
    User findUserById(Long id);
    void reg(RegUser user) throws Exception;
    String login(LoginUser user) throws Exception;

    User getOnlineUserInfo(String token);

    void logout(String token);

    void modifyPassword(String token, String password, String newPassword) throws Exception;

    List<User> findUserByIds(List<Long> ids);
}
