package com.it.controller;

import com.it.entity.Category;
import com.it.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 已不用
 */
@Controller
public class BackCategoryController {
    private final CategoryService categoryService;

    @Autowired
    public BackCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 查询文章列表
     * 一级分类有二级菜单的时候无法删除
     * 当Status为0的时候无删除按钮
     *
     * @param modelAndView 分类列表
     * @return 分类列表
     */
    @RequestMapping("/category")
    public ModelAndView getCategoryMap(ModelAndView modelAndView) {
        //获得分类
        Map<Category, List<Category>> categoryMap = categoryService.MapCategory();
        modelAndView.addObject("categoryMap", categoryMap);
        modelAndView.setViewName("admin/category");
        return modelAndView;
    }

    /**
     * 删除分类
     *
     * @param categoryId 分类ID
     */
    @RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Integer categoryId) {

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
     * @param categoryId 分类Id
     */
    @RequestMapping(value = "/mod/{categoryId}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable Integer categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
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

    /**
     * 这个方法主要是对分类的的处理
     * 根据数据库的结构 通过pid来获得一级分类的二级分类
     * 首先应该先获得一级分类通过pid=0
     * 然后通过一级分类的id来当pid去查询二级分类
     * @param Pid          id
     * @param modelAndView 分类
     * @return 分类
     */
    @RequestMapping("/getCategoryByPid")
    @ResponseBody
    public ModelAndView getCategoryByPid(Integer Pid, ModelAndView modelAndView) {
        List<Category> categoryList = categoryService.getCategoryBypId(Pid);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("");
        return modelAndView;
    }
}
