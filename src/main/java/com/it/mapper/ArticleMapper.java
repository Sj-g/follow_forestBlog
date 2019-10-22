package com.it.mapper;

import com.it.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    public int insertArticle(Article article);

    /**
     * 删除通过Id
     *
     * @param articleId
     * @return
     */
    public int banArticleById(Integer articleId);

    /**
     * 更新文章
     *
     * @param article 文章
     * @return 影响行数
     */
    public int updateArticle(Article article);

    /**
     * 查询所有文章(复杂查询,搜索可用，模糊查询)
     *
     * @return
     */
    public List<Article> findAll(Map<String, Object> criteria);

    /**
     * 按照文章的ID进行排序
     */
    public List<Article> listAllNotWithContent();

    /**
     * 获取文章总数
     *
     * @param status
     * @return
     */
    public Integer countArticle(@Param("status") Integer status);

    /**
     * 获取留言总数
     */
    public Integer countArticleComment();

    /**
     * 浏览总数
     */
    public Integer countArticleView();

    /**
     * 获取所有文章(按一定顺序)
     *
     * @return
     */
    public List<Article> listArticle();

    /**
     * 根据Id查询文章
     * @param status 文章状态
     * @return 符合条件的文章
     */
    public Article getArticleByStatusAndId(@Param("status") Integer status,@Param("id") Integer id);

    public List<Article> pageArticle(@Param("status") Integer status,
                                     @Param("pageIndex") Integer pageIndex);

    /**
     * 最火的文章
     *
     * @param limit
     * @return
     */
    public List<Article> listArticleByViewCountAndComment(@Param("limit") Integer limit);


    /**
     * 获得随机文章
     */
    public List<Article> listRandomArticle(@Param("limit") Integer limit);
    /**
     * 更新文章的评论数
     */
    public void updateCommentCount(@Param("articleId") Integer articleId);

    /**
     * 获得最后更新的记录
     */
    public Article getLastUpdateArticle();

    /**
     * 用户的文章数
     */
    public int countArticleByUser(@Param("id") Integer id);

    /**
     * 根据Category Id分类
     *
     * @param category
     * @param limit
     * @return
     */
    public List<Article> findArticleByCategoryId(@Param("category") Integer category,
                                                 @Param("limit") Integer limit);

    /**
     * 根据多个Id分类
     *
     * @param categoryIds Id集合
     * @param limit       查询数量
     * @return
     */
    public List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds, @Param("limit") Integer limit);

    /**
     * 获得最新的文章
     *
     * @param limit
     * @return
     */
    public List<Article> listArticleByLimit(Integer limit);

    /**
     * 批量删除文章
     *
     * @param ids 文章Id列表
     * @return 影响行数
     */
    Integer deleteBatch(@Param("ids") List<Integer> ids);

    /**
     * 批量删除文章
     *
     * @param  articleId 文章Id
     * @return 影响行数
     */
    Integer deleteArticleById(@Param("articleId") Integer articleId);

    /**
     * 启用文章
     * @param articleId 文章Id
     */
    void starArticleById(Integer articleId);
    /**
     * 获取用户文章列表
     */
    List<Article> userListArticle();
    /**
     * 通过文章Id
     */
    public Article getArticleById(@Param("id") Integer id);
}
