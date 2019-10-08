package com.it.service;

import com.it.entity.Article;
import com.it.entity.Comment;
import com.it.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {
    /**
     * 获得评论和评论的文章
     */
    public List<Comment> getCommentList(Integer limit);

    /**
     * id获得评论
     * @param articleId 文章id
     * @return 评论列表
     */
    List<Comment> getCommentByArticleId(Integer articleId);

    void saveComment(String com, Integer articleId, Integer user, HttpServletRequest request);
}
