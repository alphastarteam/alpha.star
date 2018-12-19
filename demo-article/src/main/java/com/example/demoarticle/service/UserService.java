package com.example.demoarticle.service;

import com.example.serviceclient.UserServiceClient;
import com.example.serviceclient.model.FindByToken;
import com.example.serviceclient.model.FindByUserId;
import com.example.serviceclient.model.FindByUsername;
import model.Response;

public class UserService implements UserServiceClient {

    @Override
    public Response findByUsername(FindByUsername findByUsername) {
        return null;
    }

    @Override
    public Response findById(FindByUserId findByUserId) {
        return null;
    }

    @Override
    public Response getOnlineUserInfo(FindByToken userAuthToken) {
        return null;
    }
}
