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

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IArticleService implements ArticleService {
    private final ArticleMapper articleMapper;
    private final ArticleCategoryRefMapper articleCategoryRefMapper;
    private final ArticleTagRefMapper articleTagRefMapper;
    //每次获取文章的数量
    private final Integer PAGE_NUMBER = 5;
    //初始换文章参数
    private final Integer ARTICLE_PARAMETER = 0;

    @Autowired
    public IArticleService(ArticleMapper articleMapper, ArticleCategoryRefMapper articleCategoryRefMapper, ArticleTagRefMapper articleTagRefMapper) {
        this.articleMapper = articleMapper;
        this.articleCategoryRefMapper = articleCategoryRefMapper;
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
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public int deleteArticle(Integer id) {

        Integer integer = null;
        try {
            integer = articleMapper.deleteArticleById(id);
        } catch (Exception e) {
            log.error("文章删除失败，id{},case{}", id, e);
        }
        return integer;
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), id);
    }

    @Override
    public Integer updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public Integer addArticle(Article article, Admin admin, List<Category> category, List<Tag> tag) {
        //保存文章
        article.setArticleStatus(ArticleStatus.PUBLISHED.getCode());
        article.setArticleUserId(admin.getAdminId());
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
        //保存分类
        ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef();
        articleCategoryRef.setArticleId(article.getArticleId());
        for (Category category1 : category) {
            articleCategoryRef.setCategoryId(category1.getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }
        //保存标签
        ArticleTagRef articleTagRef = new ArticleTagRef();
        articleTagRef.setArticleId(article.getArticleId());
        for (Tag tag1 : tag) {
            articleTagRef.setTagId(tag1.getAgId());
            articleTagRefMapper.insert(articleTagRef);
        }
        return articleMapper.insertArticle(article);
    }

    @Override
    public List<Article> getNewestArticle() {
        return articleMapper.listArticleByLimit(PAGE_NUMBER);
    }

    @Override
    public List<Article> getMostPopularArticle() {
        return articleMapper.listArticleByViewCountAndComment(PAGE_NUMBER);
    }

    @Override
    public Article getArticleByIdAndsUser(Integer articleId) {
        //获得文章
        Article article=articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), articleId);
        article.setArticleViewCount(article.getArticleViewCount()+1);
        //更新文章的观看数
        articleMapper.updateArticle(article);
        return article;
    }

    @Override
    public Integer updateComment(Integer articleId) {
        articleMapper.updateCommentCount(articleId);
        Article article=articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(),articleId);
        return article.getArticleCommentCount();
    }

    @Override
    public Integer updateArticleLikeCount(Integer articleId) {
        Article article=articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(),articleId);
        article.setArticleLikeCount(article.getArticleLikeCount()+1);
        articleMapper.updateArticle(article);
        return article.getArticleLikeCount()+1;
    }

    @Override
    public List<Article> search(Map<String,Object> message) {
        List<Article> articleList=articleMapper.findAll(message);
        return articleList;
    }


}
