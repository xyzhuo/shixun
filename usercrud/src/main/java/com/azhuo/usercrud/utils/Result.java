package com.azhuo.usercrud.utils;

/**
 * 返回结果集
 */

public class Result {

    private String message; // 消息
    private int statuCode; // 状态吗
    private Object data; // 放回数据

    public Result() {
    }

    /**
     * 构造 没有返回数据 的结果集
     * @param message
     * @param statuCode
     */
    public Result(String message, int statuCode) {
        this.message = message;
        this.statuCode = statuCode;
    }

    /**
     * 构造 有返回数据 的结果集
     * @param message
     * @param statuCode
     * @param data
     */
    public Result(String message, int statuCode, Object data) {
        this.message = message;
        this.statuCode = statuCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(int statuCode) {
        this.statuCode = statuCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", statuCode=" + statuCode +
                ", data=" + data +
                '}';
    }
}
