package com.it.interceptor;

import com.it.entity.Admin;
import com.it.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class HomeResourceInterceptor implements HandlerInterceptor {
    private final AdminService adminService;

    @Autowired
    public HomeResourceInterceptor(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 验证信息是否登陆
     * 并且把用户的权限信息放入request域中，
     * 因为每次发送方法都会拦截下来，所以可以用request的域来放入数据
     * @param httpServletRequest 请求
     * @param httpServletResponse 响应
     * @param o
     * @return 判断是否通过
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取登陆管理员
        Object adminObject = httpServletRequest.getSession().getAttribute("admin");
        if (adminObject==null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login");
            return false;
        }else{
            Map<String, List> map = null;
            if (adminObject instanceof Admin) {
                Admin admin = (Admin) adminObject;
                map = adminService.getAdminAuthority(admin.getAdminId());
            }
            httpServletRequest.setAttribute("map",map);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
