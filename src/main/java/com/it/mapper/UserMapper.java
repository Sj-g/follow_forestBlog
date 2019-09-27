package com.it.mapper;

import com.it.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据ID删除
     *
     * @param userId 用户ID
     * @return 影响行数
     */
    int deleteById(Integer userId);

    /**
     * 添加
     *
     * @param user 用户
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 根据ID查询
     *
     * @param userId 用户ID
     * @return 用户
     */
    User getUserById(Integer userId);

    /**
     * 更新
     *
     * @param user 用户
     * @return 影响行数
     */
    int update(User user);


    /**
     * 获得用户列表
     *
     * @return  用户列表
     */
    List<User> listUser() ;


    /**
     * 根据Email查询用户
     *
     * @param email 邮箱
     * @return 用户
     */
    User getUserByEmail(String email) ;
    /**
     * 设置status
     * status 状态码
     * @return
     */
    Integer setStatus(@Param(value = "Status") Integer Status,@Param(value = "userId") Integer userId);

}
