package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.entity.Article;
import com.it.entity.Comment;
import com.it.service.ArticleService;
import com.it.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BackArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @RequestMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable(value = "id")Integer id, HttpServletRequest request){
        Integer integer=articleService.deleteArticle(id);
        return "redirect:/homepage/1";
    }
    @RequestMapping("/articleList/{page}")
    public ModelAndView getResourceHomePage(ModelAndView modelAndView, @PathVariable(value = "page") int page){
        //获得文章
        PageInfo<Article> pageInfo = articleService.getArticleList(page);
        modelAndView.addObject("pageInfo", pageInfo);

        //获得评论
        List<Comment> commentList = commentService.getCommentList(6);
        modelAndView.addObject("commentList", commentList);

        modelAndView.setViewName("");
        return modelAndView;
    }
}
