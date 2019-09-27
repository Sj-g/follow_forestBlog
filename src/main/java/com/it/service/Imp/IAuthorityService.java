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
import java.sql.SQLException;
import java.util.*;

@Service
@Slf4j
public class IAuthorityService implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Authority> getAuthority(Integer adminId) {

        return authorityMapper.getAuthority(adminId);
    }

    /**
     * 获得用户获得权限和未获得的权限
     *
     * @param adminId 管理员id
     * @return
     */
    @Override
    public List<Map<String, List<Resource>>> getEnAuthority(Integer adminId) {
        Map<String, List<Resource>> getMap = new HashMap<>();
        Map<String, List<Resource>> noGetMap = new HashMap<>();
        //获得二级菜单的权限
        List<Authority> authorities = authorityMapper.getAuthority(adminId);
        //获得一级菜单
        List<Resource> list = resourceMapper.findResourceByOrder(0);
        //把二级菜单放入一级菜单
        for (int i = 0; i < list.size(); i++) {
            List<Resource> enResourceList = new ArrayList<>();
            for (int j = 0; j < authorities.size(); j++) {
                //根据权限获取资源
                Resource resource = resourceMapper.findResourceById(authorities.get(j).getAuthorityResourceId());
                if (resource != null && resource.getResourceOrder() == list.get(i).getResourceId()) {
                    enResourceList.add(resource);
                }
            }
            getMap.put(list.get(i).getResourceName(), enResourceList);
        }
        //获得未获得的权限
        for (int i = 0; i < list.size(); i++) {
            //模块二级的总权限
            List<Resource> resourceList = resourceMapper.findResourceByOrder(list.get(i).getResourceId());
            //模块二级获得的权限
            List<Resource> resourceList1 = getMap.get(list.get(i).getResourceName());
            if (resourceList.size() != resourceList1.size()) {
                for (int j = 0; j < resourceList.size(); j++) {
                    for (int k = 0; k < resourceList1.size(); k++) {
                        if (resourceList1.get(k).getResourceId() == resourceList.get(j).getResourceId()) {
                            resourceList.remove(j);
                            break;
                        }
                    }

                }
            }
            noGetMap.put(list.get(i).getResourceName(), resourceList);
        }
        List<Map<String, List<Resource>>> mapList = new ArrayList<>();
        //获得的权限
        mapList.add(getMap);
        //未获得权限
        mapList.add(noGetMap);
        return mapList;
    }

    @Override
    public Integer unAble(Integer resourceId, Integer adminId) {
        Integer integer = null;
        try {
            integer = authorityMapper.deleteAuthority(resourceId, adminId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("禁止权限失败,case:{},resourceId{},adminId{}", e, resourceId, adminId);
        }
        return integer;
    }

    @Override
    public Integer enAble(Integer resourceId, Integer adminId, HttpServletRequest request) {
        Authority authority = new Authority();
        authority.setAuthorityAdminId(adminId);
        authority.setAuthorityResourceId(resourceId);
        authority.setAuthorityAddTime(new Date());
        authority.setAuthorityIp(MyUtils.getIpAddr(request));
        System.out.println(authority);
        Integer integer = null;
        try {
            integer = authorityMapper.addAuthority(authority);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加管理员权限错误,resourceId{},adminId{},case:{}", resourceId, adminId, e);
        }
        return integer;
    }
}
