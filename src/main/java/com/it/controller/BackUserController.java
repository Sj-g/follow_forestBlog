package com.it.controller;

import com.it.dto.ResponseVo;
import com.it.entity.User;
import com.it.service.UserService;
import com.it.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BackUserController {
    @Autowired
    private UserService userService;

    /**
     * 获得管理员列表
     *
     * @param modelAndView 用户列表
     * @return 用户列表
     */
    @RequestMapping("/userList")
    public ModelAndView getUserList(ModelAndView modelAndView) {
        List<User> userList = userService.findAll();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 禁用用户
     *
     * @param userId 用户ID
     * @return 操作结果
     */
    @RequestMapping("/enUser")
    public ResponseVo enUser(@NotNull Integer userId) {
        Integer integer = userService.enUser(userId);
        if (integer == null||integer<=0) {
            ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 启用用户
     *
     * @param userId 用户ID
     * @return 操作结果
     */
    @RequestMapping("/unUser")
    public ResponseVo unUser(@NotNull Integer userId) {
        Integer integer = userService.unUser(userId);
        if (integer == null||integer<=0) {
            ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 操作结果
     */
    @RequestMapping("/modUser")
    public ResponseVo modUser(@RequestBody @Valid User user) {
        Integer integer = userService.updateUser(user);
        if (integer == null||integer<=0) {
            ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 添加用户
     *
     * @param user          用户
     * @param bindingResult 错误
     * @param request       请求
     * @return 操作结果
     */
    @RequestMapping("/addUser")
    public ResponseVo addUser(@RequestBody @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println("错误字段:" + fieldError.getField());
                System.out.println("错误信息:" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseVo.fail().add(map);
        }
        user.setUserLastLoginIp(MyUtils.getIpAddr(request));
        Integer integer = userService.addUser(user);
        if (integer == null||integer<=0) {
            ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 得到用户
     *
     * @param userId       用户Id
     * @param modelAndView 用户
     * @return 用户数据
     */
    @RequestMapping("/getUser")
    public ModelAndView getUser(@NotNull Integer userId, ModelAndView modelAndView) {
        User user = userService.findById(userId);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("");
        return modelAndView;
    }
}
