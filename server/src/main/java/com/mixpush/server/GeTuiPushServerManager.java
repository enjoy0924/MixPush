package com.mixpush.server;

import com.gexin.fastjson.JSON;
import com.gexin.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.TagTarget;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiki on 2017/6/1.
 */

public class GeTuiPushServerManager implements MixPushServerManager {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "51xb25cmJx9I28wet1Rtd5";
    private static String appKey = "Wq0MtiYBdO7YwpTLbR8iI3";
    private static String masterSecret = "W0EHO18Yk77sSLJxCvBlf4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    @Override
    public void sendMessageToAlias(List<String> alias, String messagePayload) throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(messagePayload);
        template.setTransmissionType(2);
        template.setAppId(appId);
        template.setAppkey(appKey);

        SingleMessage message = new SingleMessage();
        message.setData(template);

        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 3600 * 72);

        for (String item : alias) {
            Target target = new Target();
            target.setAppId(appId);
            target.setAlias(item);
            IPushResult ret = push.pushMessageToSingle(message, target);
            System.out.println(item + ":" + ret.getResponse().toString());
        }
//        push.close();
    }

    @Override
    public void sendMessageToTags(List<String> tags, String messagePayload) throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(messagePayload);
        template.setTransmissionType(2);
        template.setAppId(appId);
        template.setAppkey(appKey);

        SingleMessage message = new SingleMessage();
        message.setData(template);

        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 3600 * 72);

        for (String item : tags) {
            TagTarget target = new TagTarget();
            target.setAppId(appId);
            target.setTag(item);
            IPushResult ret = push.pushMessageToSingleByTag(message, target);
            System.out.println(item + ":" + ret.getResponse().toString());
        }
    }

    @Override
    public void sendMessageToAll(String messagePayload) throws IOException, ParseException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(messagePayload);
        template.setTransmissionType(2);
        template.setAppId(appId);
        template.setAppkey(appKey);

        AppMessage message = new AppMessage();
        message.setData(template);
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);

        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 3600 * 72);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println("all:" + ret.getResponse().toString());
    }

    @Override
    public void sendNotifyToAlias(List<String> alias, String title, String description, String messagePayload) throws IOException {
        JSONObject jsonObject = JSON.parseObject(messagePayload);
        jsonObject.put("title",title);
        jsonObject.put("description",description);
        jsonObject.put("notify",1);
        messagePayload = jsonObject.toJSONString();


//        IGtPush push = new IGtPush(url, appKey, masterSecret);
//        push.connect();
//
//
//        NotificationTemplate template = new NotificationTemplate();
//        template.setTransmissionContent(messagePayload);
//        template.setTransmissionType(2);
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//
//        Style0 style = new Style0();
//        // 设置通知栏标题与内容
//        style.setTitle(title);
//        style.setText(description);
//        // 配置通知栏图标
//        style.setLogo("ic_launcher.png");
//        // 配置通知栏网络图标
//        style.setLogoUrl("");
//        // 设置通知是否响铃，震动，或者可清除
//        style.setRing(true);
//        style.setVibrate(true);
//        style.setClearable(true);
//        template.setStyle(style);


        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(messagePayload);
        template.setTransmissionType(2);
        template.setAppId(appId);
        template.setAppkey(appKey);

        SingleMessage message = new SingleMessage();
        message.setData(template);

        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 3600 * 72);

        for (String item : alias) {
            Target target = new Target();
            target.setAppId(appId);
            target.setAlias(item);
            IPushResult ret = push.pushMessageToSingle(message, target);
            System.out.println(item + ":" + ret.getResponse().toString());
        }
    }

    @Override
    public void sendNotifyToTags(List<String> tags, String title, String description, String messagePayload) throws IOException {
        JSONObject jsonObject = JSON.parseObject(messagePayload);
        jsonObject.put("title",title);
        jsonObject.put("description",description);
        jsonObject.put("notify",1);
        messagePayload = jsonObject.toJSONString();

        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(messagePayload);
        template.setTransmissionType(2);
        template.setAppId(appId);
        template.setAppkey(appKey);

        SingleMessage message = new SingleMessage();
        message.setData(template);

        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 3600 * 72);

        for (String item : tags) {
            TagTarget target = new TagTarget();
            target.setAppId(appId);
            target.setTag(item);
            IPushResult ret = push.pushMessageToSingleByTag(message, target);
            System.out.println(item + ":" + ret.getResponse().toString());
        }
    }

    @Override
    public void sendNotifyToAll(String title, String description, String messagePayload) throws IOException, ParseException {
        JSONObject jsonObject = JSON.parseObject(messagePayload);
        jsonObject.put("title",title);
        jsonObject.put("description",description);
        jsonObject.put("notify",1);
        messagePayload = jsonObject.toJSONString();



        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(messagePayload);
        template.setTransmissionType(2);
        template.setAppId(appId);
        template.setAppkey(appKey);

        AppMessage message = new AppMessage();
        message.setData(template);
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);

        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 3600 * 72);

        IPushResult ret = push.pushMessageToApp(message);
        System.out.println("all:" + ret.getResponse().toString());
    }
}