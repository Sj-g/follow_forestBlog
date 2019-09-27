package com.it.service;

import com.it.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 查询用户Email
     * @param email 用户邮箱
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 查找用户列表
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 启用用户
     * @return 返回保存结果
     */
    Integer enUser(Integer userId);

    /**
     * 禁用用户
     * @return 返回保存结果
     */
    Integer unUser(Integer userId);

    /**
     * 更新用户
     * @param user 用户
     * @return
     */
    Integer updateUser(User user);

    /**
     * 增加用户
     * @param user 用户
     * @return
     */
    Integer addUser(User user);

    /**
     *通过id查询用户
     * @param integer id
     * @return
     */
    User findById(Integer integer);

}
