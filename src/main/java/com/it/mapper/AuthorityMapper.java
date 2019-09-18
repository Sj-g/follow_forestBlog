package com.it.mapper;

import com.it.entity.Authority;

import java.util.List;

public interface AuthorityMapper {
    /**
     * 获取管理员的权限
     */
    public List<Authority> getAuthority(Integer id);
}
