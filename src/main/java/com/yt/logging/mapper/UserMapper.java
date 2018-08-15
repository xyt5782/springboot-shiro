package com.yt.logging.mapper;

import com.yt.logging.domain.User;

/**
 * 接口
 */

public interface UserMapper {

    public User findByName(String name);

    public User fingById(int id);
}
