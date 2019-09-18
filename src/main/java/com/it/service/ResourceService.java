package com.it.service;

import com.it.entity.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ResourceService {
    /**
     * 禁用资源并记录操作
     * @param id
     * @param admin
     * @return
     */
    int disable(Integer id, HttpServletRequest admin);
    /**
     * 启用资源并记录操作
     */
    int enable(Integer id, HttpServletRequest admin);

    /**
     * 获得一级菜单
     * @return
     */
    List<Resource> getMenu();

    /**
     * 根据一级菜单id,获得二级菜单
     * @param authorityResourceId 一级菜单Id
     * @return
     */
    Resource getTwoMenuById(int authorityResourceId);
    /**
     * 获得资源列表
     */
    Map<String, List<Resource>> geListResource();

}
