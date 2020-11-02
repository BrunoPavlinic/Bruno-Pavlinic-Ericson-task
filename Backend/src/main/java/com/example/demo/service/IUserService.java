package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.response.Response;

public interface IUserService {
    Response login(User user);
    Response createUser(User user);
    User getByUsername(String username);
}