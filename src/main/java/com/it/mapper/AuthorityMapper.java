package com.it.mapper;

import com.it.entity.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper {
    /**
     * 获取管理员的权限
     */
    public List<Authority> getAuthority(Integer id);

    /**
     * 物理删除权限
     * @param resourceId 资源Id
     * @param adminId 管理员Id
     * @return
     */
    Integer deleteAuthority(@Param("resourceId") Integer resourceId, @Param("adminId") Integer adminId);

    /**
     * 启动权限
     * 这里我们一管理员id和资源id当作主键组
     * @param authority
     * @return
     */
    Integer addAuthority(Authority authority);
}
