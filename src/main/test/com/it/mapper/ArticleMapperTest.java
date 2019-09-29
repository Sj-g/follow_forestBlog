package com.it.mapper;

import com.it.base.BaseTest;
import com.it.entity.Article;
import com.it.enums.ArticleStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ArticleMapperTest extends BaseTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void insertArticle() {
    }

    @Test
    public void deleteArticleById() {
    }

    @Test
    public void updateArticle() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void listAllNotWithContent() {
        List<Article> articles=articleMapper.listAllNotWithContent();
        for (ArticleStatus a : ArticleStatus.values()){
            System.out.printf("%s \n",a);
        }
//        System.out.println(articles);
    }

    @Test
    public void countArticle() {
    }

    @Test
    public void countArticleComment() {
    }

    @Test
    public void countArticleView() {
        int a=articleMapper.countArticleView();
        System.out.println(a);
    }

    @Test
    public void listArticle() {
    }

    @Test
    public void getArticleByStatusAndId() {
    }

    @Test
    public void pageArticle() {
    }

    @Test
    public void listArticleByViewCount() {
    }

    @Test
    public void getAfterArticle() {
    }

    @Test
    public void getPreArticle() {
    }

    @Test
    public void listRandomArticle() {
    }

    @Test
    public void listArticleByCommentCount() {
    }

    @Test
    public void updateCommentCount() {
    }

    @Test
    public void getLastUpdateArticle() {
    }

    @Test
    public void countArticleByUser() {
    }

    @Test
    public void findArticleByCategoryId() {
    }

    @Test
    public void findArticleByCategoryIds() {
    }

    @Test
    public void listArticleByLimit() {
    }

    @Test
    public void deleteBatch() {
    }

    @Test
    public void listArticleByViewCountAndComment() {
        List<Article> articleList=articleMapper.listArticleByViewCountAndComment(5);
        for (Article article:articleList){
            System.out.println(article);
        }
    }
}