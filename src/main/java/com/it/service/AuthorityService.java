package com.it.service;

import com.it.entity.Authority;
import com.it.entity.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AuthorityService {
    /**
     * 获取权限
     */
    public List<Authority> getAuthority(Integer adminId);
    /**
     * 获得已获得权限并放入一级菜单
     */
    public List<List<Resource>> getEnAuthority(Integer adminId);

    /**
     * 禁用权限
     * @param resourceId 权限Id
     * @param adminId 管理员Id
     * @return
     */
    void unAble(Integer resourceId, Integer adminId);

    /**
     * 启用权限
     * @param resourceId 权限id
     * @param adminId 管理员Id
     * @return
     */

    void enAble(Integer resourceId, Integer adminId, HttpServletRequest request);
}
