package com.it.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public enum ArticleStatus {
    PUBLISHED(1, "已发布"),
    UNPUBLISHED(2, "草稿");
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    ArticleStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
