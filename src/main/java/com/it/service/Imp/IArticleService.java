package com.it.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.entity.*;
import com.it.enums.ArticleStatus;
import com.it.mapper.ArticleCategoryRefMapper;
import com.it.mapper.ArticleMapper;
import com.it.mapper.ArticleTagRefMapper;
import com.it.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IArticleService implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;
    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    /**
     * 查看最新5篇文章
     *
     * @param page 页数
     * @return
     */
    @Override
    public PageInfo<Article> getArticleList(Integer page) {
        PageHelper.startPage(page, 7);
        List<Article> articleList = articleMapper.listArticle();
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public int deleteArticle(Integer id) {

        Integer integer = null;
        try {
            integer = articleMapper.deleteArticleById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除失败，id{},case{}", id, e);
        }
        return integer;
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleByStatusAndId(1, id);
    }

    @Override
    public Integer updateArticle(Article article) {

        return articleMapper.updateArticle(article);
    }

    @Override
    public Integer addArticle(Article article, Admin admin,List<Category> category,List<Tag> tag) {
        //保存文章
        article.setArticleStatus(ArticleStatus.PUBLISHED.getCode());
        article.setArticleUserId(admin.getAdminId());
        article.setArticleOrder(1);
        article.setArticleCreateTime(new Date());
        //保存分类
        ArticleCategoryRef articleCategoryRef=new ArticleCategoryRef();
        articleCategoryRef.setArticleId(article.getArticleId());
        for (Category category1:category){
            articleCategoryRef.setCategoryId(category1.getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }
        //保存标签
        ArticleTagRef articleTagRef=new ArticleTagRef();
        articleTagRef.setArticleId(article.getArticleId());
        for(Tag tag1:tag){
            articleTagRef.setTagId(tag1.getAgId());
            articleTagRefMapper.insert(articleTagRef);
        }
        return articleMapper.insertArticle(article);
    }
}
