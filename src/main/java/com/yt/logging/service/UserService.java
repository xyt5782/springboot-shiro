package com.yt.logging.service;


import com.yt.logging.domain.User;

public interface UserService {

    public User findByName(String name);

    public User fingById(int id);
}
