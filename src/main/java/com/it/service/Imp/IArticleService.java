package com.it.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.dto.ArticleParam;
import com.it.dto.SimArticle;
import com.it.entity.*;
import com.it.enums.ArticleStatus;
import com.it.mapper.ArticleCategoryRefMapper;
import com.it.mapper.ArticleMapper;
import com.it.mapper.ArticleTagRefMapper;
import com.it.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IArticleService implements ArticleService {
    private final ArticleMapper articleMapper;
    //    private final ArticleCategoryRefMapper articleCategoryRefMapper;
    private final ArticleTagRefMapper articleTagRefMapper;
    //每次获取文章的数量
    private final Integer PAGE_NUMBER = 5;
    //初始换文章参数
    private final Integer ARTICLE_PARAMETER = 0;

    @Autowired
    public IArticleService(ArticleMapper articleMapper, ArticleCategoryRefMapper articleCategoryRefMapper, ArticleTagRefMapper articleTagRefMapper) {
        this.articleMapper = articleMapper;
//        this.articleCategoryRefMapper = articleCategoryRefMapper;
        this.articleTagRefMapper = articleTagRefMapper;
    }

    /**
     * 查看最新5篇文章
     *
     * @param page 页数
     * @return
     */
    @Override
    public PageInfo<Article> getArticleList(Integer page) {
        PageHelper.startPage(page, PAGE_NUMBER);
        List<Article> articleList = articleMapper.listArticle();
        return new PageInfo<>(articleList);
    }

    @Override
    public void banArticleById(Integer id) {
        try {
            articleMapper.banArticleById(id);
        } catch (Exception e) {
            log.error("文章删除失败，id{},case{}", id, e);
        }
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public void updateArticle(ArticleParam articleParam) {
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleSummary(articleParam.getArticleSummary());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //这里是更新标签
        articleTagRefMapper.deleteByArticleId(articleParam.getArticleId());
        ArticleTagRef articleTagRef = new ArticleTagRef();
        articleTagRef.setArticleId(articleParam.getArticleId());
        //如果为空进不了循环
        for (Integer tagId : articleParam.getArticleTagIds()) {
            articleTagRef.setTagId(tagId);
            articleTagRefMapper.insert(articleTagRef);
        }
        articleMapper.updateArticle(article);
    }

    @Override
    public void addArticle(ArticleParam articleParam, Admin admin) {
        Article article = new Article();
        //保存文章

        article.setArticleTitle(articleParam.getArticleTitle()); //文章题目

        article.setArticleContent(articleParam.getArticleContent()); //文章内容

        article.setArticleStatus(articleParam.getArticleStatus());  //文章状态

        article.setArticleUserId(admin.getAdminId());//文章的作者

        article.setArticleSummary(articleParam.getArticleSummary());//文章简介
        //初始化参数
        article.setArticleViewCount(ARTICLE_PARAMETER);
        article.setArticleLikeCount(ARTICLE_PARAMETER);
        article.setArticleIsComment(ARTICLE_PARAMETER);
        //
        article.setArticleOrder(admin.getAdminClass());
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//        String date=simpleDateFormat.format(new Date());
        //java有关于时间的java.util.Date java.sql.Date，所以向数据库存入时间的时候，转换为java.sql。Date类型
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        article.setArticleCreateTime(timestamp);
        article.setArticleUpdateTime(timestamp);
        articleMapper.insertArticle(article);
        //保存分类
//        ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef();
//        articleCategoryRef.setArticleId(articleId);
//        //设置第一分类
//        articleCategoryRef.setCategoryId(articleParam.getArticleParentCategoryId());
//        articleCategoryRefMapper.insert(articleCategoryRef);
//        //设置第二分类
//        articleCategoryRef.setCategoryId(articleParam.getArticleChildCategoryId());
//        articleCategoryRefMapper.insert(articleCategoryRef);
        //保存标签
        if (articleParam.getArticleTagIds() != null) {
            ArticleTagRef articleTagRef = new ArticleTagRef();
            articleTagRef.setArticleId(article.getArticleId());
            for (Integer tagId : articleParam.getArticleTagIds()) {
                articleTagRef.setTagId(tagId);
                articleTagRefMapper.insert(articleTagRef);
            }
        }
    }

    @Override
    public List<SimArticle> getNewestArticle() {
        List<SimArticle> simArticles = new ArrayList<>();
        List<Article> articleList = articleMapper.listArticleByLimit(PAGE_NUMBER);
        for (Article article : articleList) {
            SimArticle simArticle = new SimArticle();
            simArticle.setArticleId(article.getArticleId());
            simArticle.setArticleTitle(article.getArticleTitle());
            simArticles.add(simArticle);
        }
        return simArticles;
    }

    @Override
    public List<SimArticle> getMostPopularArticle() {
        List<SimArticle> simArticles = new ArrayList<>();
        List<Article> articleList = articleMapper.listArticleByViewCountAndComment(PAGE_NUMBER);
        for (Article article : articleList) {
            SimArticle simArticle = new SimArticle();
            simArticle.setArticleId(article.getArticleId());
            simArticle.setArticleTitle(article.getArticleTitle());
            simArticles.add(simArticle);
        }
        return simArticles;
    }

    @Override
    public Article getArticleByIdAndsUser(Integer articleId) {
        //获得文章
        Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), articleId);
        article.setArticleViewCount(article.getArticleViewCount() + 1);
        //更新文章的观看数
        articleMapper.updateArticle(article);
        return article;
    }

    @Override
    public Integer updateComment(Integer articleId) {
        articleMapper.updateCommentCount(articleId);
        Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), articleId);
        return article.getArticleCommentCount();
    }

    @Override
    public Integer updateArticleLikeCount(Integer articleId) {
        Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), articleId);
        article.setArticleLikeCount(article.getArticleLikeCount() + 1);
        articleMapper.updateArticle(article);
        return article.getArticleLikeCount() + 1;
    }

    @Override
    public PageInfo search(Map<String, Object> message, Integer page) {
//        System.out.println(message.get("keywords"));
//        System.out.println(page);
//        System.out.println(message.get("tagId"));
        PageHelper.startPage(page, PAGE_NUMBER);
        List<Article> articleList = articleMapper.findAll(message);
        return new PageInfo<>(articleList);
    }

    @Override
    public void deleteArticleById(Integer articleId) {
        articleMapper.deleteArticleById(articleId);
    }

    @Override
    public void starArticleById(Integer articleId) {
        articleMapper.starArticleById(articleId);
    }

}
