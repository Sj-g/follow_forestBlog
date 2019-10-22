package com.it.service.Imp;

import com.it.entity.Authority;
import com.it.entity.Resource;
import com.it.mapper.AuthorityMapper;
import com.it.mapper.ResourceMapper;
import com.it.service.AuthorityService;
import com.it.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class IAuthorityService implements AuthorityService {
    private final AuthorityMapper authorityMapper;

    private final ResourceMapper resourceMapper;

    @Autowired
    public IAuthorityService(AuthorityMapper authorityMapper, ResourceMapper resourceMapper) {
        this.authorityMapper = authorityMapper;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public List<Authority> getAuthority(Integer adminId) {

        return authorityMapper.getAuthority(adminId);
    }

    /**
     * 获得用户获得权限和未获得的权限
     *
     * @param adminId 管理员id
     * @return 资源数据
     */
    @Override
    public List<List<Resource>> getEnAuthority(Integer adminId) {
        List<Resource> get = new ArrayList<>();
        //获得二级菜单的权限
        List<Authority> authorities = authorityMapper.getAuthority(adminId);
        //获得一级菜单
        List<Resource> list = resourceMapper.findResourceByOrder(0);
        for (Resource value : list) {
            List<Resource> enResourceList = new ArrayList<>();
            for (Authority authority : authorities) {
                //根据权限获取资源
                Resource resource = resourceMapper.findResourceById(authority.getAuthorityResourceId());
                if (resource != null && resource.getResourceOrder() == value.getResourceId()) {
                    enResourceList.add(resource);
                }
            }
            get.addAll(enResourceList);
        }
        //获得未获得的权限
        List<Resource> resourceList = new ArrayList<>();
        for (Resource resource : list) {
            //模块二级的总权限
            resourceList.addAll(resourceMapper.findResourceByOrder2(resource.getResourceId()));
        }
        for (Resource resource : get) {
            for (int j = resourceList.size()-1; j>=0; j--) {
                if (resource.getResourceId() == resourceList.get(j).getResourceId()) {
                    resourceList.remove(j);
                }
            }
        }
        List<Resource> no = new ArrayList<>(resourceList);
        List<List<Resource>> mapList = new ArrayList<>();
        //获得的权限
        mapList.add(get);
        //未获得权限
        mapList.add(no);
        return mapList;
    }

    @Override
    public void unAble(Integer resourceId, Integer adminId) {

        try {
            authorityMapper.deleteAuthority(resourceId, adminId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("禁止权限失败,case:{},resourceId{},adminId{}", e, resourceId, adminId);
        }

    }

    @Override
    public void enAble(Integer resourceId, Integer adminId, HttpServletRequest request) {
        Authority authority = new Authority();
        authority.setAuthorityAdminId(adminId);
        authority.setAuthorityResourceId(resourceId);
        authority.setAuthorityAddTime(new Date());
        authority.setAuthorityIp(MyUtils.getIpAddr(request));
        System.out.println(authority);
        try {
            authorityMapper.addAuthority(authority);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加管理员权限错误,resourceId{},adminId{},case:{}", resourceId, adminId, e);
        }

    }
}
