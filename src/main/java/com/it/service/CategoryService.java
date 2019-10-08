package com.it.service;

import com.it.entity.Article;
import com.it.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategory();

    /**
     * 删除分类
     *
     * @param id ID
     */

    void deleteCategory(Integer id);

    /**
     * 根据id查询分类信息
     *
     * @param id     ID
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
