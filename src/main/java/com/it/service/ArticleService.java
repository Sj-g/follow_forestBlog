package com.it.service;

import com.github.pagehelper.PageInfo;
import com.it.entity.Admin;
import com.it.entity.Article;
import com.it.entity.Category;
import com.it.entity.Tag;

import java.util.List;
import java.util.Map;

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

    /**
     * 获得最新的5篇文章
     * @return 文章
     */
    List<Article> getNewestArticle();

    /**
     * 最受欢迎的文章
     * 根据评论人数和观看人数和喜欢人数确定
     * @return 文章
     */
    List<Article> getMostPopularArticle();

    /**
     * 获得文章 评论 分类 标签
     * @param articleId 文章ID
     * @return 文章 评论 分类 标签
     */
    Article getArticleByIdAndsUser(Integer articleId);

    /**
     * 更新评论的数量 设置更新的是否
     * @param articleId 文章的ID
     */
    Integer updateComment(Integer articleId);

    /**
     * 更新文章喜欢的人数
     * @param articleId 文章Id
     */
    Integer updateArticleLikeCount(Integer articleId);

    /**
     * 模糊查询
     * @param message 信息
     * @return 文章
     */
    List<Article> search(Map<String,Object> message);
}
