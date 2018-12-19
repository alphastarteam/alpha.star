package com.example.demouser.dataaccess.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_login",schema = "public")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private  long userId;

    @Column(name = "is_logout")
    private  boolean isLogout;

    @Column(name = "ip")
    private  String ip;

    @Column(name = "token")
    private  String token;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "creation_time")
    private  Timestamp creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isLogout() {
        return isLogout;
    }

    public void setLogout(boolean logout) {
        isLogout = logout;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
