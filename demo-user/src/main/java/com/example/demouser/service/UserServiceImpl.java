package com.example.demouser.service;

import com.example.demouser.dataaccess.model.UserLogin;
import com.example.demouser.dataaccess.repository.UserLoginRepository;
import com.example.demouser.model.LoginUser;
import com.example.demouser.model.RegUser;
import com.example.demouser.dataaccess.model.User;
import com.example.demouser.dataaccess.repository.UserRepository;
import com.example.demouser.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void create(RegUser regUser) throws OperationException, NoSuchAlgorithmException {
        var existsUser = findUserByUsername(regUser.getUsername().trim());
        if (existsUser != null) {
            throw new OperationException("用户名已被别人使用");
        }
        var user = new User();
        user.setUsername(regUser.getUsername());
        var password = getMD5(regUser.getUsername() + regUser.getPassword());
        user.setPassword(password);
        userRepository.saveAndFlush(user);
    }

    private User getUser(String username, String password) throws Exception {
        var user = userRepository.findByUsernameAndPassword(username, getMD5(username + password));
        return user;
    }

    @Override
    public String login(LoginUser loginUser) throws Exception {
        var user = getUser(loginUser.getUsername(), loginUser.getPassword());
        if (user == null) {
            throw new OperationException("用户名或密码错误");
        }
        var userLogin = userLoginRepository.findByUserId(user.getId());
        if (userLogin != null) {
            return userLogin.getToken();
        }
        userLogin = new UserLogin();
        userLogin.setIp(loginUser.getIp());
        userLogin.setUserId(user.getId());
        var uuid = UUID.randomUUID().toString();
        userLogin.setToken(uuid);
        userLoginRepository.saveAndFlush(userLogin);
        return uuid;
    }

    @Override
    public User getOnlineUserInfo(String token) {
        var userLogin = userLoginRepository.findByToken(token);
        if (userLogin == null || userLogin.isLogout()) {
            return null;
        }
        var seconds = (Timestamp.valueOf(LocalDateTime.now()).getTime() - (userLogin.getUpdateTime().getTime())) / 1000;
        if (seconds >= 1200) {
            userLoginRepository.logout(token);
            return null;
        }
        return findUserById(userLogin.getUserId());
    }

    @Override
    public void logout(String token) {
        userLoginRepository.logout(token);
    }

    @Override
    public void modifyPassword(String token, String password, String newPassword) throws Exception {
        var currentUser = getOnlineUserInfo(token);
        var validateCurrentUser = getUser(currentUser.getUsername(), password);
        if (validateCurrentUser == null) {
            throw new OperationException("出错错误，修改失败");
        }

        var newPwd = getMD5(currentUser.getUsername() + newPassword);
        userRepository.modifyPassword(currentUser.getId(), newPwd);
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);
    }
}
