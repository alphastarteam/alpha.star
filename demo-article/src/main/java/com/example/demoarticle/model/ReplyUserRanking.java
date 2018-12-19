package com.example.demoarticle.model;

public class ReplyUserRanking implements Comparable {
    private Long userId;
    private String username;
    private Long replyCount;

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(Object o) {
        return (int)(this.replyCount - ((ReplyUserRanking) o).replyCount);

    }
}
