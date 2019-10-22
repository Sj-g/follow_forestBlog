package com.it.enums;

import lombok.Getter;
import lombok.Setter;

public enum ResourceStatus {
    ENABLE(1, "可用"),
    UNABLE(0, "禁用");
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;
    ResourceStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
