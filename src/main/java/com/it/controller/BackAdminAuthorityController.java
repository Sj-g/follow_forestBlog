package com.it.controller;

import com.it.dto.JsonResult;
import com.it.dto.Mes;
import com.it.entity.Admin;
import com.it.entity.Resource;
import com.it.service.AdminService;
import com.it.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 权限管理
 * 获得权限
 * 禁止
 * 启用
 */
@Controller
public class BackAdminAuthorityController {
    private final AdminService adminService;
    private final AuthorityService authorityService;

    @Autowired
    public BackAdminAuthorityController(AdminService adminService, AuthorityService authorityService) {
        this.adminService = adminService;
        this.authorityService = authorityService;
    }

    /**
     * 获得低于此管理员的管理员
     *
     * @param modelAndView 管理员
     * @param request      请求
     * @return 管理员
     */
    @RequestMapping("/authority")
    public ModelAndView adminAuthority(ModelAndView modelAndView, HttpServletRequest request) {
        //获取登陆管理员
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //获取比此管理员权限等级低的管理员
        List<Admin> adminList = adminService.getAdminList(admin);
        modelAndView.addObject("adminList", adminList);
        modelAndView.setViewName("admin/authority");
        return modelAndView;
    }

    /**
     * 获得已获的和未获得权限
     *
     * @param adminId      管理员的id
     * @param modelAndView 返回视图和ID
     */
    @RequestMapping("/enAuthority")
    @ResponseBody
    public Mes getEnAuthority(Integer adminId, ModelAndView modelAndView) {
        //获取管理员已有权限和未获得权限
        List<List<Resource>> mapList = authorityService.getEnAuthority(adminId);
        //获得的权限和没有的权限
//        modelAndView.addObject("haveRight", mapList.get(0));
//        modelAndView.addObject("noRight", mapList.get(1));
//        modelAndView.setViewName("");
        return Mes.success().add("haveRight", mapList.get(0)).add("noRight", mapList.get(1));
    }

    /**
     * 禁用权限
     */
    @RequestMapping("/unAbleAuthority/{resourceId}/{adminId}")
    @ResponseBody
    public JsonResult unAbleAuthority(@PathVariable Integer resourceId, @PathVariable Integer adminId) {
        JsonResult jsonResult = new JsonResult();
        if (resourceId == null) {
            return jsonResult.fail("权限无法得到");
        }
        if (adminId == null) {
            return jsonResult.fail("管理员无法得到");
        }
        authorityService.unAble(resourceId, adminId);
        return jsonResult.success();
    }

    /**
     * 启用权限
     */
    @RequestMapping("/enAbleAuthority/{resourceId}/{adminId}")
    @ResponseBody
    public JsonResult enAbleAuthority(@PathVariable Integer resourceId, @PathVariable Integer adminId, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        if (resourceId == null) {
            return jsonResult.fail("权限无法得到");
        }
        if (adminId == null) {
            return jsonResult.fail("管理员无法得到");
        }
        authorityService.enAble(resourceId, adminId, request);
        return jsonResult.success();
    }

}
