/*
 * Copyright (c) 2021. syshlangcom
 * @File: UroraConfig.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 上午9:19
 * @since:
 */

package com.syshlang.urora.common.constants;


import com.syshlang.common.utils.PropertiesUtil;

/**
 * @description: UroraConfig <br>
 * @date: 2021/11/15 9:19 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class UroraConfig {

    public static final String APP_KEY = PropertiesUtil.getAplicationProperties("urora.push.appkey");
    public static final String MASTER_SECRET = PropertiesUtil.getAplicationProperties("urora.push.mastersecret");


}
