package com.it.service;

import com.github.pagehelper.PageInfo;
import com.it.entity.Admin;
import com.it.entity.Article;
import com.it.entity.Category;
import com.it.entity.Tag;

import java.util.List;

public interface ArticleService {
    /**
     * 获取文章列表
     */
    public PageInfo<Article> getArticleList(Integer page);
    /**
     * 逻辑删除
     */
    public int deleteArticle(Integer id);

    /**
     * 通过Id获取文章
     * @param id 文章Id
     * @return 文章
     */
    Article getArticleById(Integer id);

    /**
     * 更新文章
     * @param article 文章
     */
    Integer updateArticle(Article article);

    /**
     * 添加文章
     * @param article 文章
     * @param admin 管理员Id
     */
    Integer addArticle(Article article, Admin admin, List<Category> categoryList,List<Tag> tagList);
}
