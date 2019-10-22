package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.dto.ArticleParam;
import com.it.dto.JsonResult;
import com.it.dto.Mes;
import com.it.entity.*;
import com.it.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 关于文章的增加，删除，修改，查询
 * 增加这里是逻辑删除
 * 需要在每篇文章下增加评论（关于评论的删除）
 */
@Controller
public class BackArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final TagService tagService;
//    private final CategoryService categoryService;

    @Autowired
    public BackArticleController(ArticleService articleService, CommentService commentService, TagService tagService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.tagService = tagService;
//        this.categoryService = categoryService;
    }

    /**
     * 禁用文章
     *
     * @param id 文章Id
     */
    @RequestMapping(value = "/banArticleById/{id}")
    @ResponseBody
    public Mes banArticleById(@PathVariable(value = "id") Integer id) {
        articleService.banArticleById(id);
        return Mes.success();
    }
    /**
     * 启用文章
     *
     * @param id 文章Id
     */
    @RequestMapping(value = "/starArticleById/{id}")
    @ResponseBody
    public Mes starArticleById(@PathVariable(value = "id") Integer id) {
        articleService.starArticleById(id);
        return Mes.success();
    }

    /**
     * 禁用文章
     *
     * @param id 文章Id
     */
    @RequestMapping(value = "/deleteArticleById/{id}")
    @ResponseBody
    public Mes deleteArticleById(@PathVariable(value = "id") Integer id) {
        articleService.deleteArticleById(id);
        return Mes.success();
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
        modelAndView.setViewName("admin/article");
        return modelAndView;
    }

    /**
     * 通过ID查询文章
     *
     * @param articleId    文章Id
     * @param modelAndView 文章和评论
     * @return 文章和评论
     */
    @RequestMapping(value = "/getArticleById/{articleId}", method = RequestMethod.GET)
    public ModelAndView getArticleById(@PathVariable Integer articleId, ModelAndView modelAndView) {
        Article article = articleService.getArticleById(articleId);
        modelAndView.addObject("article", article);
        //标签列表
        List<Tag> tagList=tagService.tagList();
        modelAndView.addObject("tagList",tagList);
        //获得文章的评论
//        List<Comment> commentList = commentService.getCommentByArticleId(articleId);
//        modelAndView.addObject("commentList", commentList);
        modelAndView.setViewName("admin/modArticle");
        return modelAndView;
    }

    /**
     * 修改文章
     *
     * @param article 文章
     * @return 修改结果
     */
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    @ResponseBody
    public Mes updateArticle(ArticleParam article) {
        articleService.updateArticle(article);
        return Mes.success();
    }

    /**
     * 添加文章
     * 传入参数 文章题目 文章简洁 文章内容
     * 文章分类和标签
     *
     * @param articleParam 文章参数
     * @return 全部文章页面
     */
    @RequestMapping("/addArticle")
    @ResponseBody
    public JsonResult addArticle(ArticleParam articleParam, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        System.out.println("getArticleTagIds=" + articleParam.getArticleTagIds());
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            //这段代码是以后无用的，但是现在由于没有拦截器，所以要配上
            return jsonResult.fail("管理员未登陆");
        }
        if (articleParam.getArticleContent() == null) {
            return jsonResult.fail("文章为空");
        }
        if (articleParam.getArticleStatus() == null) {
            //当未设置状态的时候，默认为草稿
            articleParam.setArticleStatus(0);
        }
        articleService.addArticle(articleParam, admin);

        return jsonResult.success();
    }

    @RequestMapping("/editorArticle")
    public ModelAndView editorArticle(ModelAndView modelAndView) {
        List<Tag> tagList = tagService.tagList();
        modelAndView.addObject(tagList);
//        Map<Category, List<Category>> mapCategory = categoryService.MapCategory();
//        modelAndView.addObject("mapCategory", mapCategory);
        modelAndView.setViewName("admin/editorarticle");
        return modelAndView;
    }
}
