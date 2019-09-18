package com.it.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int articleId;
    private int articleUserId;
    private String articleTitle;
    private String articleContent;
    private int articleViewCount;
    private int articleCommentCount;
    private int articleLikeCount;
    private int articleIsComment;
    private int articleStatus;
    private int articleOrder;
    private Date articleUpdateTime;
    private Date articleCreateTime;
    private String articleSummary;
}
