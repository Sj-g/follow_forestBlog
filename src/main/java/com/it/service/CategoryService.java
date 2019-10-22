package com.it.service;

import com.it.entity.Article;
import com.it.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    /**
     * 获得分类列表
     * 一级分类有二级菜单的时候无法删除
     * 当Stair为0的时候无删除按钮
     *当一级分类下无二级分类,那么就变成二级菜单stair变为一
     * @return 分类列表
     */
    Map<Category,List<Category>> MapCategory();

    /**
     * 删除分类
     *
     * @param id ID
     */

    void deleteCategory(Integer id);

    /**
     * 根据id查询分类信息
     *
     * @param id ID
     * @return 分类
     */
    Category getCategoryById(Integer id);

    /**
     * 添加分类
     *
     * @param category 分类
     */
    void insertCategory(Category category);

    /**
     * 更新分类
     *
     * @param category 分类
     */
    void updateCategory(Category category);

    /**
     * 根据分类名获取分类
     *
     * @param name 名称
     * @return 分类
     */
    Category getCategoryByName(String name);

    List<Category> getCategoryBypId(Integer pId);

    List<Category> getCategoryByArticleId(Integer article);
}
