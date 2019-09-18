package com.it.controller;

import com.it.entity.Admin;
import com.it.entity.Resource;
import com.it.service.AdminService;
import com.it.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 这个关于管理员的权限的
 */
@Controller
public class BackAdminAuthorityController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 获得权限和未获得权限
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping("/authority")
    public ModelAndView adminAuthority(ModelAndView modelAndView, HttpServletRequest request) {
        //获取登陆管理员
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //获取比此管理员权限等级低的管理员
        List<Admin> adminList = adminService.getAdminList(admin);
        modelAndView.addObject("adminList", adminList);
        //获取管理员已有权限和未获得权限
        List<Map<String, List<Resource>>> mapList = authorityService.getEnAuthority(admin.getAdminId());
        //获得的权限
        modelAndView.addObject("haveRight", mapList.get(0));
        //没有的权限
        modelAndView.addObject("noRight", mapList.get(1));
        modelAndView.setViewName("");
        return modelAndView;
    }
    /**
     * 禁用权限
     */

    /**
     * 启用权限
     */


}
