package com.it.controller;

import com.it.dto.AdminParam;
import com.it.dto.JsonResult;
import com.it.dto.Mes;
import com.it.entity.*;
import com.it.service.*;
import com.it.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 关于用户的登陆和注册
 */
@Controller
public class BackAdminController {
    private final AdminService adminService;
    private final AuthorityService authorityService;

    @Autowired
    public BackAdminController(AuthorityService authorityService, AdminService adminService) {
        this.authorityService = authorityService;
        this.adminService = adminService;
    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }
    /**
     * 注册
     */
    @RequestMapping("/adminRegister")
    public String adminRegister(){
        return "admin/register";
    }

    /**
     * 验证（不好）
     *
     * @param email    邮箱
     * @param password 密码
     * @param request  请求
     * @return 返回JSON数据
     */
    @RequestMapping(value = "/verification",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult verification( String email, String password, HttpServletRequest request) {
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
        admin.setAdminLastLoginIp(MyUtils.getIpAddr(request));
        adminService.update(admin);
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
        return "admin/login";
    }
//
//    /**
//     * 获取用户权限，生成权限
//     *
//     * @param modelAndView 返回视图和数据
//     * @param request      请求
//     * @return 返回视图和数据
//     */
//    @RequestMapping("/homepage")
//    public ModelAndView getHomePage(ModelAndView modelAndView, HttpServletRequest request) {
//        //获取登陆管理员
//        Object o = request.getSession().getAttribute("admin");
//        Map<String, List> map = null;
//        if (o instanceof Admin) {
//            Admin admin = (Admin) o;
//            map = adminService.getAdminAuthority(admin.getAdminId());
//        }
//        modelAndView.addObject("map", map);
//        modelAndView.setViewName("admin/publics/menu");
//        return modelAndView;
//    }
    /**
     * 启用管理员
     */
    @RequestMapping("enAdmin/{adminId}")
    public String enAbleAdmin(@PathVariable Integer adminId){
        if (adminId==null){
            throw new RuntimeException("启用用户，用户无法得到");
        }
        adminService.enAdmin(adminId);
        return "redirect:/authority";
    }
    /**
     * 禁用管理员
     */
    @RequestMapping("unAdmin/{adminId}")
    public String unAbleAdmin(@PathVariable Integer adminId){
        if (adminId==null){
            throw new RuntimeException("启用用户，用户无法得到");
        }
        adminService.unAdmin(adminId);
        return "redirect:/authority";
    }
    /**
     * 管理员的注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public Mes register(@RequestBody AdminParam adminParam, HttpServletRequest request){
        System.out.println("adminParam="+adminParam);
        Admin admin=(Admin) request.getSession().getAttribute("admin");
        if (adminParam==null){
            return Mes.fail("内容为空");
        }
        adminParam.setIp(MyUtils.getIpAddr(request));
        adminParam.setAdminClass(admin.getAdminClass()+1);
        adminService.saveAdmin(adminParam);
        return Mes.success();
    }
    /**
     * 查找邮箱是否重复
     */
    @RequestMapping("/repeat")
    @ResponseBody
    public Mes repeat(String email){
        Admin admin=adminService.getAdminByEmail(email);
        if (admin==null){
            return Mes.success();
        }
        return Mes.fail("用户名重复");
    }
}
