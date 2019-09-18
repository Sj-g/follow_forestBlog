package com.it.mapper;

import com.it.entity.Admin;

import java.util.List;

public interface AdminMapper {
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
     * @param admin 用户
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 根据ID查询
     *
     * @param adminId 用户ID
     * @return 用户
     */
    Admin getAdminById(Integer adminId);

    /**
     * 更新
     *
     * @param admin 用户
     * @return 影响行数
     */
    int update(Admin admin);


    /**
     * 获得管理员列表
     * @return  管理员列表
     */
    List<Admin> listAdmin() ;


    /**
     * 根据Email查询管理员
     *
     * @param email 邮箱
     * @return 管理员
     */
    Admin getAdminByEmail(String email) ;

}
