package com.it.mapper;

import com.it.base.BaseTest;
import com.it.entity.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

public class ResourceMapperTest extends BaseTest {
    @Autowired
    private ResourceMapper resourceMapper;
    @Test
    @Rollback(value = true)
    public void disableAndEnable() {
        Resource resource = resourceMapper.findResourceById(1);
        resource.setResourceStatus(0);
        int a=resourceMapper.disableAndEnable(resource);
        System.out.println(a);
    }

    @Test
    public void findResourceById() {
        Resource resource=resourceMapper.findResourceById(1);
        System.out.println(resource);
    }

    @Test
    public void findResourceByOrder() {
        List<Resource> resource=resourceMapper.findResourceByOrder(0);
        for (Resource resource1:resource){
            System.out.println(resource1);
        }
    }
}