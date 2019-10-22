package com.it.service;

import com.it.dto.AdminParam;
import com.it.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    /**
     * 通过email查新管理员
     * @param email 邮箱
     * @return 管理员
     */
    Admin getAdminByEmail(String email);
    /**
     * 查询权限低于某个管理员的管理员
     */
    List<Admin> getAdminList(Admin admin);
    /**
     * 获得管理员的权限
     */
    Map<String,List> getAdminAuthority(Integer adminId);

    /**
     * 启用管理员
     * @param adminId 管理员Id
     */
    void enAdmin(Integer adminId);

    /**
     * 禁用管理员
     * @param adminId 管理员Id
     */
    void unAdmin(Integer adminId);

    void saveAdmin(AdminParam adminParam);

    /**
     * 更新管理员
     * @param admin 管理员
     */
    void update(Admin admin);
}
