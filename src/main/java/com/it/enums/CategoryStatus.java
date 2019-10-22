package com.it.enums;

import lombok.Getter;
import lombok.Setter;

public enum CategoryStatus {
    Stair(0, "一级分类"),
    Second(1, "二级分类");
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    CategoryStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
