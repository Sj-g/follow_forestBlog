package com.it.dto;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class ResponseVo {
    /**
     * 100失败
     * 200成功
     */
    private Integer code;
    private String message;
    private Map mapper = new HashMap<>();

    public ResponseVo() {
    }

    public ResponseVo add(Map map) {
        this.mapper = map;
        return this;
    }

    private ResponseVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 操作成功
     *
     * @return
     */
    public static ResponseVo success() {
        return new ResponseVo(200, "成功");
    }

    /**
     * 操作失败
     *
     * @return this
     */
    public static ResponseVo fail() {
        return new ResponseVo(100, "失败");
    }
}
