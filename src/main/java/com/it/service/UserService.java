package com.it.service;

import com.it.entity.User;

public interface UserService {
    /**
     * 查询用户Email
     * @param email
     * @return
     */
    User getUserByEmail(String email);
}
