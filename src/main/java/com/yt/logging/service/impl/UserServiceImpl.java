package com.yt.logging.service.impl;

import com.yt.logging.domain.User;
import com.yt.logging.mapper.UserMapper;
import com.yt.logging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        User user = userMapper.findByName(name);
        return user;
    }

    @Override
    public User fingById(int id) {
        User user = userMapper.fingById(id);
        return user;
    }
}
