package com.example.demoarticle.dataaccess.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "topic",schema = "public")
public class Topic {
    public Topic(){
        this.creationTime=new Timestamp(System.currentTimeMillis());
        this.updateTime=new Timestamp(System.currentTimeMillis());
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private int reward;
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "user_id")
    private long userId;
    private long popularity;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "is_top")
    private boolean isTop;

    @Column(name = "is_essence")
    private boolean isEssence;

    @Column(name = "is_complete")
    private boolean isComplete;

    @Column(name = "reply_count")
    private long replyCount;

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean isEssence() {
        return isEssence;
    }

    public void setEssence(boolean essence) {
        isEssence = essence;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(long replyCount) {
        this.replyCount = replyCount;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
