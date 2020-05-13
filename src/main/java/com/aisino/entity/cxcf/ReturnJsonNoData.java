package com.aisino.entity.cxcf;

import java.util.List;

/**
 * @program: gyxxdz
 * @description:
 * @author: Bruse Queen
 * @create: 2019-03-01 11:29
 **/
public class ReturnJsonNoData {
    /**
     * 状态码
     */
    private int status;
    /**
     * 信息
     */
    private String msg;

    private String DJHM;

    private Object tSysorderhead;


    private Object data;


    public ReturnJsonNoData() {
    }

    public ReturnJsonNoData(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ReturnJsonNoData(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDJHM() {
        return DJHM;
    }

    public void setDJHM(String DJHM) {
        this.DJHM = DJHM;
    }

    public Object gettSysorderhead() {
        return tSysorderhead;
    }

    public void settSysorderhead(Object tSysorderhead) {
        this.tSysorderhead = tSysorderhead;
    }




    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReturnJsonNoData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
