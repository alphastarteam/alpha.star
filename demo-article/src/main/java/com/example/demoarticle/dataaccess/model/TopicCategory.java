package com.example.demoarticle.dataaccess.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "topic_category", schema = "public")
public class TopicCategory {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Column(name = "is_navigation")
    private boolean isNavigation;

    @Column(name = "parent_id")
    private Long pareintId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPareintId() {
        return pareintId;
    }

    public void setPareintId(Long pareintId) {
        this.pareintId = pareintId;
    }

    public boolean isNavigation() {
        return isNavigation;
    }

    public void setNavigation(boolean navigation) {
        isNavigation = navigation;
    }
}
