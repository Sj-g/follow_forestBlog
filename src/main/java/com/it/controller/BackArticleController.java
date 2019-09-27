package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.dto.ResponseVo;
import com.it.entity.*;
import com.it.service.ArticleService;
import com.it.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 关于文章的增加，删除，修改，查询
 * 增加这里是逻辑删除
 * 需要在每篇文章下增加评论（关于评论的删除）
 */
@Controller
public class BackArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    /**
     * 删除文章
     *
     * @param id      文章Id
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "/deleteArticle/{id}", method = RequestMethod.DELETE)
    public String deleteArticle(@PathVariable(value = "id") @NotNull Integer id, HttpServletRequest request) {
        Integer integer = articleService.deleteArticle(id);
        return "redirect:/homepage/1";
    }

    /**
     * 查询文章列表
     *
     * @param modelAndView 文章
     * @param page         页数
     * @return 文章列表
     */
    @RequestMapping(value = "/articleList/{page}", method = RequestMethod.GET)
    public ModelAndView getResourceHomePage(ModelAndView modelAndView, @PathVariable(value = "page") int page) {
        //获得文章
        PageInfo<Article> pageInfo = articleService.getArticleList(page);
        modelAndView.addObject("pageInfo", pageInfo);

        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 通过ID查询文章
     *
     * @param articleId    文章Id
     * @param modelAndView 文章和评论
     * @return 文章和评论
     */
    @RequestMapping(value = "/getArticleById", method = RequestMethod.GET)
    public ModelAndView getArticleById(Integer articleId, ModelAndView modelAndView) {
        Article article = articleService.getArticleById(articleId);
        modelAndView.addObject("article", article);
        //获得文章的评论
        List<Comment> commentList = commentService.getCommentByArticleId(articleId);
        modelAndView.addObject("commentList", commentList);
        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 修改文章
     *
     * @param article 文章
     * @return 修改结果
     */
    @RequestMapping(value = "/updateArticle", method = RequestMethod.PUT)
    public ResponseVo updateArticle(Article article) {
        Integer integer = articleService.updateArticle(article);
        if (integer == null) {
            return ResponseVo.fail();
        }
        return ResponseVo.success();
    }

    /**
     * 添加文章
     * 传入参数 文章题目 文章简洁 文章内容
     * 文章分类和标签
     *
     * @param article 文章
     * @return 操作结果
     */
    @RequestMapping("/addArticle")
    public ResponseVo addArticle(Article article, HttpServletRequest request, List<Category> categoryList, List<Tag> tagList) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Integer integer = articleService.addArticle(article, admin,categoryList,tagList);
        if (integer == null||integer<=0) {
            return ResponseVo.fail();
        }
        return ResponseVo.success();
    }
}
