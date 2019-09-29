package com.it.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private int categoryId;
    private int categoryPid;
    private String categoryName;
    private String categoryDescription;
    private int categoryOrder;
    private String categoryIcon;
    /**
     * 一级分类0
     * 二级分类1
     */
    private Integer stair;

}
