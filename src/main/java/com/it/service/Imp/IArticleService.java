package com.it.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.entity.Article;
import com.it.mapper.ArticleMapper;
import com.it.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IArticleService implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查看最新5篇文章
     * @param page
     * @return
     */
    @Override
    public PageInfo<Article> getArticleList(Integer page) {
        PageHelper.startPage(page,7);
        List<Article> articleList=articleMapper.listArticle();
        PageInfo<Article> pageInfo=new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public int deleteArticle(Integer id) {

        Integer integer= null;
        try {
            integer = articleMapper.deleteArticleById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除失败，id{},case{}",id,e);
        }
        return integer;
    }
}
