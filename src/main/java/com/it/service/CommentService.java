package com.it.service;

import com.it.entity.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 评论列表
     */
    public List<Comment> getCommentList(Integer limit);
}
