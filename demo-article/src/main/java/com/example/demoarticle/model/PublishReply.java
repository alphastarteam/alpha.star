package com.example.demoarticle.model;

import javax.validation.constraints.NotNull;

public class PublishReply {
    private long topicId;

    @NotNull
    private String content;

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
