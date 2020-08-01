package com.example.demo.entity;

public class Json {
    private String code;
    private String msg;
    private Object data;

    public Json() {
    }

    public Json(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Json(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Json(String code) {
        this.code = code;
    }

    public Json(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
