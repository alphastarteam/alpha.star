package com.example.serviceclient.model;

import javax.validation.constraints.NotNull;

public class FindByUserId {
    @NotNull
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
