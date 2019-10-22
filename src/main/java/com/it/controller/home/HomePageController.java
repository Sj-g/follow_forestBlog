package com.it.controller.home;

import com.github.pagehelper.PageInfo;
import com.it.dto.SimArticle;
import com.it.entity.Article;
import com.it.entity.Tag;
import com.it.service.ArticleService;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/user")
public class HomePageController {
    private final ArticleService articleService;
    private final TagService tagService;
    @Autowired
    public HomePageController(ArticleService articleService, TagService tagService) {
        this.articleService = articleService;
        this.tagService = tagService;
    }
    @RequestMapping("/homepage/{page}")
    public ModelAndView getHomePage(ModelAndView modelAndView, @PathVariable Integer page){
        //文章列表
        PageInfo<Article> pageInfo=articleService.getArticleList(page);
        modelAndView.addObject("pageInfo",pageInfo);
        //分类列表
//        Map<Category,List<Category>> categoryList=categoryService.MapCategory();
//        modelAndView.addObject("categoryList",categoryList);
        //标签列表
        List<Tag> tagList=tagService.tagList();
        modelAndView.addObject("tagList",tagList);
        //最新文章 只需要像前台传递id 和 name就行
        List<SimArticle> newestArticle=articleService.getNewestArticle();
        modelAndView.addObject("newestArticle",newestArticle);
        //最火文章 只需要像前台传递id和name就行
        List<SimArticle> mostPoplarArticle=articleService.getMostPopularArticle();
        modelAndView.addObject("mostPoplarArticle",mostPoplarArticle);
        modelAndView.setViewName("user/index");
        return modelAndView;
    }
}
