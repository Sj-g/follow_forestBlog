package com.it.dto;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
public class Mes {
    /**
     * 100失败
     * 200成功
     */
    private Integer code;
    private String message;
    private Map<String,Object> mapper = new HashMap<>();

    public Mes() {
    }

    public Mes add(String key,Object value) {
        this.mapper.put(key,value);
        return this;
    }

    private Mes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 操作成功
     */
    public static Mes success() {
        return new Mes(200, "成功");
    }

    /**
     * 操作失败
     */
    public static Mes fail() {
        return new Mes(100, "失败");
    }
    /**
     * 操作失败
     */
    public static Mes fail(String message) {
        return new Mes(100, message);
    }
}
