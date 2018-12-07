package com.example.demouser.model;

import javax.validation.constraints.NotNull;

public class ModifyPassword {

    @NotNull(message = "旧密码不能为空")
    private String password;
    @NotNull(message = "新密码不能为空")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
