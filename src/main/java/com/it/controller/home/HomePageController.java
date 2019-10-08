package com.it.controller.home;

import com.github.pagehelper.PageInfo;
import com.it.entity.Article;
import com.it.entity.Category;
import com.it.entity.Tag;
import com.it.service.ArticleService;
import com.it.service.CategoryService;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class HomePageController {
    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final TagService tagService;
    @Autowired
    public HomePageController(ArticleService articleService, CategoryService categoryService, TagService tagService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }
    @RequestMapping("/homepage")
    public ModelAndView getHomePage(ModelAndView modelAndView){
        //文章列表
        PageInfo<Article> articleList=articleService.getArticleList(5);
        modelAndView.addObject("articleList",articleList);
        //分类列表
        List<Category> categoryList=categoryService.listCategory();
        modelAndView.addObject("categoryList",categoryList);
        //标签列表
        List<Tag> tagList=tagService.tagList();
        modelAndView.addObject("tagList",tagList);
        //最新文章
        List<Article> articleList2=articleService.getNewestArticle();
        modelAndView.addObject("newestArticle",articleList2);
        //最火文章
        List<Article> articleList1=articleService.getMostPopularArticle();
        modelAndView.addObject("mostPoplarArticle",articleList1);
        return modelAndView;
    }
}
