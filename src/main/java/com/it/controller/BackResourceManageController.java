package com.it.controller;

import com.it.dto.ResponseVo;
import com.it.entity.Resource;
import com.it.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 资源的管理
 * 启用
 * 禁用
 * 获得资源列表
 */
@Controller
public class BackResourceManageController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 禁用资源
     *
     * @param id 资源Id
     * @return 操作结果
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public ResponseVo disable(@PathVariable Integer id, HttpServletRequest request) {
        int judgeSuccess = resourceService.disable(id);
        if (judgeSuccess != 1) {
            return ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 启用资源
     *
     * @param id 资源Id
     * @return 返回结果
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public ResponseVo enable(@PathVariable Integer id, HttpServletRequest request) {
        int judgeSuccess = resourceService.enable(id);
        if (judgeSuccess <= 0) {
            return ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 获得资源列表
     */
    @RequestMapping("/resourceList")
    public ModelAndView resourceList(ModelAndView modelAndView) {
        Map<String, List<Resource>> stringListMap = resourceService.geListResource();
        modelAndView.addObject("stringListMap", stringListMap);
        modelAndView.setViewName("");
        return modelAndView;
    }

}
