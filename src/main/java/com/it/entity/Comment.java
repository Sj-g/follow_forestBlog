package com.it.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int commentId;
    private int commentPid;
    private String commentPName;
    private int commentArticleId;
    private String commentAuthorName;
    private String commentAutoEmail;
    private String commentContent;
    private String CommentIp;
    private Date commentCreateTime;
    /**
     * 角色(管理员1，访客0)
     */
    private Integer commentRole;

    /**
     * 非数据库字段
     */
    private Article article;

}
