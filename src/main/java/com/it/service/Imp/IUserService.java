package com.it.service.Imp;

import com.it.entity.User;
import com.it.mapper.UserMapper;
import com.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserService implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByEmail(String email) {
        User userEmail=userMapper.getUserByEmail(email);
        return userEmail;
    }
}
