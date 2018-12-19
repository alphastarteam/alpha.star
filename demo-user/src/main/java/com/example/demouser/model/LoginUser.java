package com.example.demouser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUser {

    @NotNull(message = "用户名不能为空")
    @Size(min = 6, max = 30, message = "用户名应在6到30个字符之间")
    @JsonProperty("username")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 30, message = "密码应在6到30个字符之间")
    @JsonProperty("password")
    private String password;

    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
