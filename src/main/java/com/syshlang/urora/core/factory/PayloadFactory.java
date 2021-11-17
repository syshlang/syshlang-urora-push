/*
 * Copyright (c) 2021. syshlangcom
 * @File: PayloadFactory.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 上午9:00
 * @since:
 */

package com.syshlang.urora.core.factory;

import cn.hutool.core.lang.Assert;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.syshlang.urora.common.constants.UroraConfig;
import com.syshlang.urora.common.dto.PushMessageDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @description: PayloadFactory <br>
 * @date: 2021/11/15 9:00 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class PayloadFactory {

    private final static  String ALIAS_SPACER = "_";

    public static PushPayload createMessageForAndroidAndIOS(PushMessageDto message) {
        Assert.notNull(message.getLoginName(),"Illegal parameter exception :${}","loginName");
        Assert.notNull(message.getContent(),"The message content cannot be empty");
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(message.getLoginName()))
                .setNotification(getNotificationForAndroidAndIOS(message))
                .setOptions(Options.newBuilder().setApnsProduction(UroraConfig.APNS_PRODUCTION).build())
                .build();
    }

    private static Notification getNotificationForAndroidAndIOS(PushMessageDto message) {
        Map<String, String> extras = Optional.ofNullable(message.getExtras()).orElseGet(() -> new HashMap<>());
        IosNotification.Builder iosNotice = IosNotification.newBuilder().setAlert(message.getContent()).addExtras(extras);
        AndroidNotification.Builder androidNotice = AndroidNotification.newBuilder().setAlert(message.getContent()).addExtras(extras);
        Optional.ofNullable(message.getTitle()).ifPresent(title -> {
            androidNotice.setTitle(title);
        });

        return Notification.newBuilder()
                .addPlatformNotification(iosNotice.build())
                .addPlatformNotification(androidNotice.build())
                .build();
    }

}
