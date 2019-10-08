package com.it.service.Imp;

import com.it.entity.Article;
import com.it.entity.Comment;
import com.it.entity.User;
import com.it.enums.ArticleStatus;
import com.it.enums.AuthorityStatus;
import com.it.mapper.ArticleMapper;
import com.it.mapper.CommentMapper;
import com.it.mapper.UserMapper;
import com.it.service.CommentService;
import com.it.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ICommentService implements CommentService {
    private final CommentMapper commentMapper;
    private final ArticleMapper articleMapper;
    private final UserMapper userMapper;

    @Autowired
    public ICommentService(ArticleMapper articleMapper, CommentMapper commentMapper, UserMapper userMapper) {
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
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
        return commentMapper.listCommentByArticleId(articleId);
    }

    @Override
    public void saveComment(String com, Integer articleId, Integer userId, HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setCommentContent(com);
        comment.setCommentArticleId(articleId);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        comment.setCommentCreateTime(timestamp);

        Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISHED.getCode(), articleId);
        comment.setCommentPid(article.getArticleUserId());
        //PName代表文章题目
        comment.setCommentPName(article.getArticleTitle());

        User user = userMapper.getUserById(userId);
        comment.setCommentAuthorName(user.getUserNickName());
        comment.setCommentAutoEmail(user.getUserEmail());

        comment.setCommentRole(AuthorityStatus.USER.getCode());
        comment.setCommentIp(MyUtils.getIpAddr(request));

        commentMapper.insert(comment);
    }


}
