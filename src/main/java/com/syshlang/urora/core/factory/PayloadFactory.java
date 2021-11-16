/*
 * Copyright (c) 2021. syshlangcom
 * @File: PayloadFactory.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 上午9:00
 * @since:
 */

package com.syshlang.urora.core.factory;

import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.syshlang.urora.common.constants.PushConfig;
import com.syshlang.urora.common.dto.PushMessageDto;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @description: PayloadFactory <br>
 * @date: 2021/11/15 9:00 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class PayloadFactory {

    private final static  String ALIAS_SPACER = "_";

    public static PushPayload createMessageForAndroidAndIOS(PushMessageDto message) {
        String alias = getAlias(message.getLoginName());
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(getNotificationForAndroidAndIOS(message))
                .setOptions(Options.newBuilder().setApnsProduction(PushConfig.APNS_PRODUCTION).build())
                .build();
    }

    private static String getAlias(String loginName) {
        if (StringUtils.isBlank(loginName)) {
            throw new NullPointerException("Illegal parameter exception [loginName]");
        }
        return PushConfig.Company.SLJD.name() + ALIAS_SPACER + getEnvPrefix() + ALIAS_SPACER + loginName;
    }


    private static String getEnvPrefix(){
        if (PushConfig.APNS_PRODUCTION) {
            return PushConfig.PushEnv.PROD.name().toLowerCase();
        }
        return PushConfig.PushEnv.TEST.name().toLowerCase();
    }

    private static Notification getNotificationForAndroidAndIOS(PushMessageDto message) {
        String content = message.getContent();
        if (StringUtils.isBlank(content)) {
            throw new IllegalArgumentException("The message content cannot be empty");
        }
        Map<String, String> extras = message.getExtras();
        if (null == extras) {
            extras = new HashedMap();
        }
        IosNotification.Builder iosNotice = IosNotification.newBuilder().setAlert(content).addExtras(extras);
        AndroidNotification.Builder androidNotice = AndroidNotification.newBuilder().setAlert(content).addExtras(extras);
        if (StringUtils.isNotBlank(message.getTitle())) {
            androidNotice.setTitle(message.getTitle());
        }
        return Notification.newBuilder()
                .addPlatformNotification(iosNotice.build())
                .addPlatformNotification(androidNotice.build())
                .build();
    }

}
