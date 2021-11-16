/*
 * Copyright (c) 2021. syshlangcom
 * @File: UroraPushResult.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 下午4:16
 * @since:
 */

package com.syshlang.urora.common.dto;

/**
 * @description: UroraPushResult <br>
 * @date: 2021/11/15 16:16 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class UroraPushResult {

    /**
     * 是否成功
     */
    private boolean ifSucceed;
    /**
     * 消息ID
     */
    private long msg_id;
    /**
     * 发送编号
     */
    private int sendno;
    /**
     * 状态编码
     */
    private int statusCode;
    /**
     * 错误编码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    public boolean getIfSucceed() {
        return ifSucceed;
    }

    public void setIfSucceed(boolean ifSucceed) {
        this.ifSucceed = ifSucceed;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

    public int getSendno() {
        return sendno;
    }

    public void setSendno(int sendno) {
        this.sendno = sendno;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UroraPushResult{" +
                "ifSucceed='" + ifSucceed + '\'' +
                ", msg_id=" + msg_id +
                ", sendno=" + sendno +
                ", statusCode=" + statusCode +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
