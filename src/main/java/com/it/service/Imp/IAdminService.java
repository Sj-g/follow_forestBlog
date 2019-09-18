package com.it.service.Imp;

import com.github.pagehelper.PageInfo;
import com.it.entity.Admin;
import com.it.mapper.AdminMapper;
import com.it.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class IAdminService implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin getAdminByEmail(String email) {
       return adminMapper.getAdminByEmail(email);
    }

    @Override
    public List<Admin> getAdminList(Admin admin) {
        List<Admin> adminList=adminMapper.listAdmin();
        Integer adminClass=admin.getAdminClass();
        Iterator<Admin> adminIterator=adminList.iterator();
        while (adminIterator.hasNext()){
            Admin admin1=adminIterator.next();
            Integer adminClass1=admin1.getAdminClass();
            if(adminClass>=adminClass1){
                adminIterator.remove();
            }
        }
        return adminList;
    }
}
