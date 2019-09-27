package com.it.service;

import com.it.entity.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ResourceService {
    /**
     * 禁用资源并记录操作
     * @param id 资源Id
     */
    int disable(Integer id);
    /**
     * 启用资源并记录操作
     */
    int enable(Integer id);

    /**
     * 获得一级菜单
     * @return 一级菜单列表
     */
    List<Resource> getMenu();

    /**
     * 根据一级菜单id,获得二级菜单
     * @param authorityResourceId 一级菜单Id
     * @return 资源
     */
    Resource getTwoMenuById(int authorityResourceId);
    /**
     * 获得资源列表
     */
    Map<String, List<Resource>> geListResource();

}
