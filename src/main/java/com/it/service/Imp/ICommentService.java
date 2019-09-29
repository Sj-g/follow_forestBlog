package com.it.service.Imp;

import com.it.entity.Article;
import com.it.entity.Comment;
import com.it.enums.ArticleStatus;
import com.it.mapper.ArticleMapper;
import com.it.mapper.CommentMapper;
import com.it.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ICommentService implements CommentService {
    private final CommentMapper commentMapper;
    private final ArticleMapper articleMapper;

    @Autowired
    public ICommentService(ArticleMapper articleMapper, CommentMapper commentMapper) {
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> getCommentList(Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listRecentComment(limit);
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最新评论失败，limit{},case{}", limit, e);
        }
        return commentList;
    }

    @Override
    public List<Comment> getCommentByArticleId(Integer articleId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listCommentByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文章获取失败，case{},articleId{}", e, articleId);
        }
        return commentList;
    }


}
