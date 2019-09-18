package com.it.service;

import com.it.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 通过email查新管理员
     * @param email
     * @return
     */
    Admin getAdminByEmail(String email);
    /**
     * 查询权限低于某个管理员的管理员
     */
    List<Admin> getAdminList(Admin admin);
}
