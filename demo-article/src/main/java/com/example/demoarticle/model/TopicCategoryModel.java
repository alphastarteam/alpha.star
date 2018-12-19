package com.example.demoarticle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopicCategoryModel {
    private String name;
    private long id;

    @JsonProperty("isNavigation")
    private boolean isNavigation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNavigation() {
        return isNavigation;
    }

    public void setNavigation(boolean navigation) {
        isNavigation = navigation;
    }
}
