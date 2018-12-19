package com.example.demoarticle.model;

import javax.validation.constraints.NotNull;

public class TopRequestModel {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
