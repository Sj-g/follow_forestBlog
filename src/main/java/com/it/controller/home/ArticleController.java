package com.it.controller.home;

import com.alibaba.fastjson.JSON;
import com.it.entity.*;
import com.it.enums.ArticleStatus;
import com.it.service.ArticleService;
import com.it.service.CategoryService;
import com.it.service.CommentService;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章详细页
 * 要加上观看次数 评论数 文章是否被评论 喜欢的数量
 */
@Controller
@RequestMapping("/user")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService, TagService tagService, CategoryService categoryService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    /**
     * 获得文章及文章所属标签和分类
     * 文章的评价
     * 更新文章的观看数
     * @param articleId 文章id
     * @return 文章 标签 分类 评价
     */
    @RequestMapping("/getDetailArticle")
    public ModelAndView getDetailArticle(Integer articleId, ModelAndView modelAndView) {
        if (articleId == null) {
            //返回相应的界面
            throw new RuntimeException("文章获取失败，无法获得文章的位置");
        }
        //获得文章并更新文章观看数
        Article article = articleService.getArticleByIdAndsUser(articleId);
        modelAndView.addObject("article", article);
        //文章的评价
        List<Comment> commentList = commentService.getCommentByArticleId(articleId);
        modelAndView.addObject("commentList", commentList);
        //标签
        List<Tag> tagList = tagService.getTagByArticleId(articleId);
        modelAndView.addObject("tagList", tagList);
        //分类
        List<Category> categoryList = categoryService.getCategoryByArticleId(articleId);
        modelAndView.addObject("categoryList", categoryList);
        return modelAndView;
    }

    /**
     * 保存文章的评价
     * 更新文章的评论数
     * 是否被评论
     * @param articleId 文章ID
     * @param comment 前端需要传来关于评论者的id 评论内容
     * @return 评论结果
     */
    @RequestMapping("/saveArticleComment")
    @ResponseBody
    public String  saveArticleComment(Integer articleId, String comment, Integer user, HttpServletRequest  request) {
        //保存评论
        commentService.saveComment(comment,articleId,user,request);
        //更新文章数据
        Integer integer=articleService.updateComment(articleId);
        return JSON.toJSONString(integer);
    }
    /**
     * 更新文章的喜欢人数
     */
    @RequestMapping("/updateLikeCount")
    @ResponseBody
    public String updateLikeCount(Integer articleId){
        Integer integer=articleService.updateArticleLikeCount(articleId);
        return JSON.toJSONString(integer);
    }
    /**
     * 关于查询
     */
    @RequestMapping("/search")
    public ModelAndView search(String message,ModelAndView modelAndView){
        Map<String,Object> map=new HashMap<>();
        map.put("status", ArticleStatus.PUBLISHED);
        map.put("keyword",message);
        List<Article> articleList=articleService.search(map);
        modelAndView.addObject("articleList",articleList);
        return modelAndView;
    }
}
