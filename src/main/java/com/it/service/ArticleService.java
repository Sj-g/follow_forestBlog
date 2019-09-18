package com.it.service;

import com.github.pagehelper.PageInfo;
import com.it.entity.Article;

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
}
