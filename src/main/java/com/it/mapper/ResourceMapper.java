package com.it.mapper;

import com.it.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    /**
     * 禁用和启用
     */
    public int disableAndEnable(Resource resource);
    /**
     * 通过Id查询
     */
    public Resource findResourceById(Integer id);
    /**
     * 条件查询菜单
     *
     */
    public List<Resource> findResourceByOrder(Integer order);

    /**
     * 查询菜单无条件
     */
    public List<Resource> findResourceByOrder2(Integer order);
}
