/*
 * Copyright (c) 2021. syshlangcom
 * @File: UroraServer.java
 * @Description:
 * @Author: sunys
 * @Date: 2021/11/15 上午10:01
 * @since:
 */

package com.syshlang.urora.core.server;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.syshlang.common.utils.Logger;
import com.syshlang.urora.common.constants.UroraConfig;
import com.syshlang.urora.common.dto.PushMessageDto;
import com.syshlang.urora.common.dto.UroraPushResult;
import com.syshlang.urora.core.factory.PayloadFactory;


/**
 * @description: UroraServer <br>
 * @date: 2021/11/15 10:01 <br>
 * @author: sunys <br>
 * @version: <br>
 */
public class UroraServer {

    private final static ClientConfig clientConfig = ClientConfig.getInstance();
    private static JPushClient jpushClient = null;

    private static JPushClient getJpushClient(){
        if (null == jpushClient) {
            synchronized (JPushClient.class) {
                if (null == jpushClient) {
                    jpushClient = new JPushClient(UroraConfig.MASTER_SECRET, UroraConfig.APP_KEY, null, clientConfig);
                }
            }
        }
        return jpushClient;
    }

    public static UroraPushResult pushMessage(PushMessageDto message){
        Logger.debug("\n向【"+message.getLoginName()+"】推送消息:"+message);
        UroraPushResult uroraPushResult = new UroraPushResult();
        try {
            JPushClient jpushClient = getJpushClient();
            PushPayload payload = PayloadFactory.createMessageForAndroidAndIOS(message);
            PushResult pushResult = jpushClient.sendPush(payload);
            uroraPushResult.setIfSucceed(pushResult.isResultOK());
            uroraPushResult.setMsg_id(pushResult.msg_id);
            uroraPushResult.setSendno(pushResult.sendno);
            uroraPushResult.setStatusCode(pushResult.statusCode);
            PushResult.Error error = pushResult.error;
            if (null != error) {
                uroraPushResult.setCode(error.getCode());
                uroraPushResult.setMessage(error.getMessage());
            }
        } catch (APIConnectionException e) {
            uroraPushResult.setIfSucceed(false);
            uroraPushResult.setMessage("API Connection Exception:" + e.getMessage());
            Logger.error("pushMessage",e);
        } catch (APIRequestException e) {
            uroraPushResult.setIfSucceed(false);
            uroraPushResult.setMessage("API Request Exception:" + e.getMessage());
            Logger.error("pushMessage",e);
        } catch (Exception e) {
            uroraPushResult.setIfSucceed(false);
            uroraPushResult.setMessage("Message push exception:" + e.getMessage());
            Logger.error("pushMessage",e);
        }
        Logger.debug("\n推送结果："+ uroraPushResult);
        return uroraPushResult;
    }
}
