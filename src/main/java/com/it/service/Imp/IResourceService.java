package com.it.service.Imp;

import com.it.entity.Admin;
import com.it.entity.Record;
import com.it.entity.Resource;
import com.it.enums.ResourceStatus;
import com.it.mapper.RecordMapper;
import com.it.mapper.ResourceMapper;
import com.it.service.ResourceService;
import com.it.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IResourceService implements ResourceService {
    private final ResourceMapper resourceMapper;
    private final RecordMapper recordMapper;

    @Autowired
    public IResourceService(RecordMapper recordMapper, ResourceMapper resourceMapper) {
        this.recordMapper = recordMapper;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public void disable(Integer id) {
        Resource resource = resourceMapper.findResourceById(id);
        //设置禁用
        resource.setResourceStatus(ResourceStatus.UNABLE.getCode());
        resourceMapper.disableAndEnable(resource);

    }

    @Override
    public void enable(Integer id) {
        Resource resource = resourceMapper.findResourceById(id);
        resource.setResourceStatus(ResourceStatus.ENABLE.getCode());
        resourceMapper.disableAndEnable(resource);
    }

    @Override
    public List<Resource> getMenu() {
        return resourceMapper.findResourceByOrder(0);
    }

    @Override
    public Resource getTwoMenuById(int authorityResourceId) {
        return resourceMapper.findResourceById(authorityResourceId);
    }

    @Override
    public Map<String, List<Resource>> geListResource() {
        Map<String, List<Resource>> map = new HashMap<>();
        //寻找一级菜单，然后把二级菜单放到一级菜单内，通过一级菜单的id
        Integer menus = 0;
        List<Resource> resourceList = resourceMapper.findResourceByOrder(menus);
        for (Resource resource : resourceList) {
            List<Resource> resourceList1 = resourceMapper.findResourceByOrder(resource.getResourceId());
            map.put(resource.getResourceName(), resourceList1);
        }
        return map;
    }
}
