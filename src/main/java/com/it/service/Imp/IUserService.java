package com.it.service.Imp;

import com.it.entity.User;
import com.it.mapper.UserMapper;
import com.it.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IUserService implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public IUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userMapper.listUser();
    }

    @Override
    public Integer enUser(Integer userId) {
        Integer integer = userMapper.setStatus(1, userId);
        return integer;
    }

    @Override
    public Integer unUser(Integer userId) {
        Integer integer = userMapper.setStatus(0, userId);
        return integer;
    }

    @Override
    public Integer updateUser(User user) {
        Integer integer = null;
        try {
            integer = userMapper.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户更新失败,case{},user{}", e, user);
        }
        return integer;
    }

    @Override
    public Integer addUser(User user) {
        Integer integer = null;
        user.setUserRegisterTime(new Date());
        user.setUserStatus(1);
        try {
            integer = userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return integer;
    }

    @Override
    public User findById(Integer integer) {
        return userMapper.getUserById(integer);

    }
}
