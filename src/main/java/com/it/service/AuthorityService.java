package com.it.service;

import com.it.dto.ResponseVo;
import com.it.entity.Admin;
import com.it.entity.Authority;
import com.it.entity.Resource;

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
    public List<Map<String,List<Resource>>> getEnAuthority(Integer adminId);

}
