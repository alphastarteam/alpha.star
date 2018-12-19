package com.example.demoarticle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class GetTopics {
    @NotNull
    private int page;
    @NotNull
    private int limit;
    private Long categoryId;
    @JsonProperty("isEssence")
    private Boolean isEssence;

    @JsonProperty("isTop")
    private Boolean isTop;
    @JsonProperty("isComplete")
    private Boolean isComplete;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getEssence() {
        return isEssence;
    }

    public void setEssence(Boolean essence) {
        isEssence = essence;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
