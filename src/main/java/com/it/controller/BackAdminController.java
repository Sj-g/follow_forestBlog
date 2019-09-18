package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.dto.JsonResult;
import com.it.entity.*;
import com.it.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆
 */
@Controller
public class BackAdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login/signin";
    }

    /**
     * 验证（不好）
     *
     * @param email
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/verification")
    @ResponseBody
    public JsonResult verification(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        Admin admin = adminService.getAdminByEmail(email);
        if (admin == null) {
            return jsonResult.fail("用户不存在");
        }
        if (admin.getAdminStatus()==0){
            return jsonResult.fail("用户被禁用");
        }
        if (!admin.getAdminPass().equals(password)) {
            return jsonResult.fail("密码错误");
        }
        List<Authority> authorities = authorityService.getAuthority(admin.getAdminId());
        if (authorities.size() == 0) {
            return jsonResult.fail("用户账号无权限");
        }
        HttpSession session = request.getSession();
        session.setAttribute("admin", admin);
        return jsonResult.success();
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping("/exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "admin/login/signin";
    }

    /**
     * 获取主页数据
     */
    @RequestMapping("/homepage")
    public ModelAndView getHomePage(ModelAndView modelAndView, HttpServletRequest request) {
        //获取登陆管理员
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //获得二级菜单的权限,验证登陆，账号没有权限，无法登陆，避免了空指针
        List<Authority> authorities = authorityService.getAuthority(admin.getAdminId());
        //        List<Authority> authorities = authorityService.getAuthority(1);
        Map<String, List> map = new HashMap<>();
        //获得一级菜单
        List<Resource> list = resourceService.getMenu();
        //把二级菜单放入一级菜单
        for (int i = 0; i < list.size(); i++) {
            List<Resource> resourceList = new ArrayList<>();
            for (int j = 0; j < authorities.size(); j++) {
                //根据权限获取资源
                Resource resource = resourceService.getTwoMenuById(authorities.get(j).getAuthorityResourceId());
                if (resource != null && resource.getResourceOrder() == list.get(i).getResourceId()) {
                    resourceList.add(resource);
                }
            }
            //如果没有二级菜单，就无一级菜单
            if (resourceList.size() != 0) {
                map.put(list.get(i).getResourceName(), resourceList);
            }
        }
        modelAndView.addObject("map", map);
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }
}
