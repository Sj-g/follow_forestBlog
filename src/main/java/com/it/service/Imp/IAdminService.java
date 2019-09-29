package com.it.service.Imp;

import com.it.entity.Admin;
import com.it.entity.Authority;
import com.it.entity.Resource;
import com.it.mapper.AdminMapper;
import com.it.mapper.AuthorityMapper;
import com.it.mapper.ResourceMapper;
import com.it.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IAdminService implements AdminService {
    private final AdminMapper adminMapper;
    private final AuthorityMapper authorityMapper;
    private final ResourceMapper resourceMapper;

    @Autowired
    public IAdminService(AdminMapper adminMapper, AuthorityMapper authorityMapper, ResourceMapper resourceMapper) {
        this.adminMapper = adminMapper;
        this.authorityMapper = authorityMapper;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminMapper.getAdminByEmail(email);
    }

    @Override
    public List<Admin> getAdminList(Admin admin) {
        List<Admin> adminList = adminMapper.listAdmin();
        Integer adminClass = admin.getAdminClass();
        Iterator<Admin> adminIterator = adminList.iterator();
        while (adminIterator.hasNext()) {
            Admin admin1 = adminIterator.next();
            Integer adminClass1 = admin1.getAdminClass();
            if (adminClass >= adminClass1) {
                adminIterator.remove();
            }
        }
        return adminList;
    }

    @Override
    public Map<String, List> getAdminAuthority(Integer adminId) {
        //获得二级菜单的权限,验证登陆，账号没有权限，无法登陆，避免了空指针
        List<Authority> authorities = authorityMapper.getAuthority(adminId);
        Map<String, List> map = new HashMap<>();
        //获得一级菜单
        List<Resource> list = resourceMapper.findResourceByOrder(0);
        //把二级菜单放入一级菜单
        for (int i = 0; i < list.size(); i++) {
            List<Resource> resourceList = new ArrayList<>();
            for (int j = 0; j < authorities.size(); j++) {
                //根据权限获取资源
                Resource resource = resourceMapper.findResourceById(authorities.get(j).getAuthorityResourceId());
                if (resource != null && resource.getResourceOrder() == list.get(i).getResourceId()) {
                    resourceList.add(resource);
                }
            }
            //如果没有二级菜单，就无一级菜单
            if (resourceList.size() != 0) {
                map.put(list.get(i).getResourceName(), resourceList);
            }
        }
        return map;
    }
}
