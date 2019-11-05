package com.it.service.Imp;

import com.it.dto.AdminParam;
import com.it.entity.Admin;
import com.it.entity.Authority;
import com.it.entity.Resource;
import com.it.mapper.AdminMapper;
import com.it.mapper.AuthorityMapper;
import com.it.mapper.ResourceMapper;
import com.it.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
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
        //这里需要验证二级菜单是否可用
        for (int i = authorities.size() - 1; i >= 0; i--) {
            Resource resource = resourceMapper.findResourceById(authorities.get(i).getAuthorityResourceId());
            if (resource.getResourceStatus() == 0) {
                authorities.remove(i);
            }
        }
        Map<String, List> map = new HashMap<>();
        //获得一级菜单
        List<Resource> list = resourceMapper.findResourceByOrder(0);
        //把二级菜单放入一级菜单
        for (Resource value : list) {
            List<Resource> resourceList = new ArrayList<>();
            for (Authority authority : authorities) {
                //根据权限获取资源
                Resource resource = resourceMapper.findResourceById(authority.getAuthorityResourceId());
                if (resource != null && resource.getResourceOrder() == value.getResourceId()) {
                    resourceList.add(resource);
                }
            }
            //如果没有二级菜单，就无一级菜单
            if (resourceList.size() != 0) {
                map.put(value.getResourceName(), resourceList);
            }
        }
        return map;
    }

    @Override
    public void enAdmin(Integer adminId) {
        Admin admin = adminMapper.getAdminById(adminId);
        //0为禁用，1为启用
        admin.setAdminStatus(1);
        adminMapper.update(admin);
    }

    @Override
    public void unAdmin(Integer adminId) {
        Admin admin = adminMapper.getAdminById(adminId);
        //0为禁用，1为启用
        admin.setAdminStatus(0);
        adminMapper.update(admin);
    }

    @Override
    public void saveAdmin(AdminParam adminParam) {
        Admin admin = new Admin();
        //获得当前时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        admin.setAdminEmail(adminParam.getEmail());
        admin.setAdminNickName(adminParam.getAdminName());
        admin.setAdminPass(adminParam.getPassword());
        admin.setAdminRegisterTime(timestamp);
        admin.setAdminClass(adminParam.getAdminClass());
        admin.setAdminLastLoginIp(adminParam.getIp());
        int adminStatus = 1;
        admin.setAdminStatus(adminStatus);
        admin.setAdminLastLoginTime(timestamp);
        System.out.println("admin=" + admin);
        adminMapper.insert(admin);
    }

    @Override
    public void update(Admin admin) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        admin.setAdminLastLoginTime(timestamp);
        adminMapper.update(admin);
    }
}
