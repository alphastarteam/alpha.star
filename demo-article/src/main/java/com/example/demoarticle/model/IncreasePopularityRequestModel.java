package com.example.demoarticle.model;

import javax.validation.constraints.NotNull;

public class IncreasePopularityRequestModel {
    @NotNull
    private long topicId;

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}
