package com.it.controller;

import com.it.entity.Category;
import com.it.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BackCategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public ModelAndView getCategoryList(ModelAndView modelAndView) {
        //获得分类
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("");
        return modelAndView;
    }
}
