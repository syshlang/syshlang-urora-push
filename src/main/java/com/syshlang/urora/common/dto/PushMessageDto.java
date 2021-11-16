/*
 * Copyright (c) 2021. syshlangcom
 * @File: PushMessageDto.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 上午10:26
 * @since:
 */

package com.syshlang.urora.common.dto;

import java.util.Map;

/**
 * @description: PushMessageDto <br>
 * @date: 2021/11/15 10:26 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class PushMessageDto {

    /**
     * 发送目标别名
     */
    private String loginName;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 附加信息
     */
    private Map<String, String> extras;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }


    @Override
    public String toString() {
        return "PushMessageDto{" +
                "loginName='" + loginName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", extras=" + extras +
                '}';
    }
}
