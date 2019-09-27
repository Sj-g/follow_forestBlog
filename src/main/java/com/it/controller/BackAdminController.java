package com.it.controller;

import com.it.dto.JsonResult;
import com.it.entity.*;
import com.it.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 关于用户的登陆和注册
 */
@Controller
public class BackAdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login/signin";
    }

    /**
     * 验证（不好）
     *
     * @param email    邮箱
     * @param password 密码
     * @param request  请求
     * @return 返回JSON数据
     */
    @RequestMapping("/verification")
    @ResponseBody
    public JsonResult verification(@RequestParam("email") @NotNull String email, @RequestParam("password") @NotNull String password, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        Admin admin = adminService.getAdminByEmail(email);
        if (admin == null) {
            return jsonResult.fail("用户不存在");
        }
        if (admin.getAdminStatus() == 0) {
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
     * @param request 请求
     */
    @RequestMapping("/exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "admin/login/signin";
    }

    /**
     * 获取用户权限，生成权限
     *
     * @param modelAndView 返回视图和数据
     * @param request      请求
     * @return 返回视图和数据
     */
    @RequestMapping("/homepage")
    public ModelAndView getHomePage(ModelAndView modelAndView, HttpServletRequest request) {
        //获取登陆管理员
        Object o = request.getSession().getAttribute("admin");
        Map<String, List> map = null;
        if (o instanceof Admin) {
            Admin admin = (Admin) o;
            map = adminService.getAdminAuthority(admin.getAdminId());
        }
        modelAndView.addObject("map", map);
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }
}
