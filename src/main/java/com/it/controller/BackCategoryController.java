package com.it.controller;

import com.it.entity.Category;
import com.it.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BackCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询文章列表
     *
     * @param modelAndView 分类列表
     * @return 分类列表
     */
    @RequestMapping("/category")
    public ModelAndView getCategoryList(ModelAndView modelAndView) {
        //获得分类
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteCategory(Integer categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    /**
     * 添加文章
     *
     * @param category 分类
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addCategory(Category category) {
        categoryService.insertCategory(category);
    }

    /**
     * 修改分类
     *
     * @param category 分类
     */
    @RequestMapping(value = "/mod", method = RequestMethod.PUT)
    public void updateCategory(Category category) {
        categoryService.updateCategory(category);
    }

    /**
     * 通过id查询
     *
     * @param categoryId 分类标签Id
     * @return 分类
     */
    @RequestMapping("/getCategoryById")
    public ModelAndView getCategoryById(Integer categoryId, ModelAndView modelAndView) {
        Category category = categoryService.getCategoryById(categoryId);
        modelAndView.addObject("category", category);
        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 得到分类通过名字
     *
     * @param categoryName 分类名称
     * @param modelAndView 分类
     * @return 分类
     */
    @RequestMapping("/getCategoryByName")
    public ModelAndView getCategoryByName(String categoryName, ModelAndView modelAndView) {
        Category category = categoryService.getCategoryByName(categoryName);
        modelAndView.addObject("category", category);
        modelAndView.setViewName("");
        return modelAndView;
    }

}
