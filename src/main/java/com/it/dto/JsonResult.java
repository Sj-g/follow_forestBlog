package com.it.dto;

import lombok.Data;

@Data
public class JsonResult<T> {
    /**
     * 状态码
     * 200 为成功
     * 100 为失败
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据
     */
    private T date;

    /**
     * 失败构造方法
     *
     * @param code
     * @param message
     */
    private JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult() {
    }

    /**
     * 成功构造方法
     *
     * @param code    状态码
     * @param message 信息
     * @param date    数据
     */
    private JsonResult(int code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public JsonResult fail() {
        return new JsonResult(100, "失败");
    }

    public JsonResult fail(String message) {
        return new JsonResult(100, message);
    }

    public JsonResult success(T date) {
        return new JsonResult(200, "操作成功", date);
    }

    public JsonResult success() {
        return new JsonResult(200, "操作成功");
    }
}
