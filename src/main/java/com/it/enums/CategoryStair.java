package com.it.enums;

import lombok.Getter;
import lombok.Setter;

public enum CategoryStair {
    Stair(0, "一级菜单"),
    Second(1, "二级菜单");
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    CategoryStair(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
