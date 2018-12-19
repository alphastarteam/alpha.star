package com.example.serviceclient.model;

import javax.validation.constraints.NotNull;

public class FindByUsername {
    @NotNull(message = "用户名不能为空")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
