/*
 * Copyright (c) 2021. syshlangcom
 * @File: PushConfig.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 上午10:19
 * @since:
 */

package com.syshlang.urora.common.constants;

import com.syshlang.common.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: PushConfig <br>
 * @date: 2021/11/15 10:19 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class PushConfig {

    private static final String systemEnv = PropertiesUtil.getAplicationProperties("system.env");

    public static final boolean APNS_PRODUCTION = StringUtils.equals("pro",systemEnv) ? true : false;


    public enum Company{
        SLJD;
    }

    public enum PushEnv {
        PROD,TEST;
    }
}
