package com.it.entity;


import lombok.Data;

import java.io.Serializable;
@Data
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int articleId;
    private int tagId;


}
