package com.lucky.NewSignInWeb.bean;


import com.lucky.NewSignInWeb.enums.Code;

/**
 * 数据库操作返回结果的封装.
 * @param <T>
 */
public class Result<T> {

    /**
     * 状态码.
     */
    private Code code;

    /**
     * 返回信息.
     */
    private T obj;

    public Result(){}

    public Result(Code code, T obj) {
        this.code = code;
        this.obj = obj;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + obj.toString() + '\'' +
                '}';
    }
}
