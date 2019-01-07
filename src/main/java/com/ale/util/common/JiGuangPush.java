package com.ale.util.common;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

/**
 * java后台极光推送,使用Java SDK
 * 推送的主要有两个核心。
 * 一是创建一个链接对象，即 PushClient; 二是创建推送对象，即 PushPayload（关键）
 *
 * @author alewu
 * @date 2017/11/14 11:49
 * @description JiGuangPush
 */

@SuppressWarnings({"deprecation", "restriction"})
public class JiGuangPush {
    private static final Logger LOGGER = LoggerFactory.getLogger(JiGuangPush.class);
    private static final boolean apns_production = true;
    private static final int time_to_live = 86400;
    private static String masterSecret = ConfigUtil.getParameter("masterSecret");
    private static String appkey = ConfigUtil.getParameter("appkey");

    public static int getTime_to_live() {
        return time_to_live;
    }

    public static String getMasterSecret() {
        return masterSecret;
    }

    public static void setMasterSecret(String masterSecret) {
        JiGuangPush.masterSecret = masterSecret;
    }

    public static String getAppkey() {
        return appkey;
    }

    public static void setAppkey(String appkey) {
        JiGuangPush.appkey = appkey;
    }

    public static void main(String[] args) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appkey, null, clientConfig);
        Gson gson = new Gson();
        PushMsg pushMsg = new PushMsg();
        pushMsg.setTitle("系统消息");
        pushMsg.setMsg("都会受到很多黑人的人人");
        Map<String, String> pushMap = new HashMap<String, String>();
        pushMap.put("news", "幅度很大");
        pushMsg.setMap(pushMap);
        PushPayload payload = buildPushObject_all_all_alert(pushMsg.getTitle());
        try {
            jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            LOGGER.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOGGER.error(
                    "Error response from JPush server. Should review and fix it. ",
                    e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
            LOGGER.info("Msg ID: " + e.getMsgId());
        }
    }


    /**
     * 根据别名推送
     *
     * @param alias 别名
     * @return PushResult
     */
    public static PushResult pushAlias(String alias, String contentType, Map<String, String> map) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appkey, null, clientConfig);
        // 推送对象可以设置的属性比较多，可以决定推送的信息内容，平台，平台下的设备等等
        // PushPayload payload = buildPushObject_all_alias_alert(alias, pushMsg);
        PushPayload pushPayload = buildPushObject_android_alias_silenceAlert(alias, contentType, map);
        try {
            return jpushClient.sendPush(pushPayload);
        } catch (APIConnectionException e) {
            LOGGER.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOGGER.error("Error response from JPush server. Should review and fix it. ", e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
            LOGGER.info("Msg ID: " + e.getMsgId());
            LOGGER.error("Sendno: " + pushPayload.getSendno());
        }
        return null;
    }

    /**
     * 推送所有的平台
     *
     * @return PushResult
     */
    public static PushResult pushAll(String msg) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appkey, null, clientConfig);
        PushPayload payload = buildPushObject_all_all_alert(msg);
        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            LOGGER.error("Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            LOGGER.error(
                    "Error response from JPush server. Should review and fix it. ",
                    e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
            LOGGER.info("Msg ID: " + e.getMsgId());
            return null;
        }
    }

    /**
     * 静默推送
     *
     * @param deviceAlias 设备别名
     * @param map         业务的key/value
     * @return 推送载体
     */
    public static PushPayload buildPushObject_android_alias_silenceAlert(String deviceAlias, String contentType, Map<String, String> map) {
        // 推送平台android
        Platform platform = Platform.android();
        // 推送设备对象，表示一条推送可以被推送到哪些设备列表。确认推送设备对象，JPush 提供了多种方式，比如：别名、标签、注册ID、分群、广播等。
        Audience alias = Audience.alias(deviceAlias);
        // 可选 消息内容体。是被推送到客户端的内容。应用内消息。与 notification 一起二者必须有其一，可以二者并存
        Message message = Message.newBuilder().setMsgContent("").setContentType(contentType).addExtras(map).build();
        // 可选	推送参数
        Options options = Options.newBuilder().setApnsProduction(true).build();
        return PushPayload.newBuilder().setPlatform(platform).setAudience(alias)
                .setMessage(message)
                .setOptions(options)
                .build();
    }

    /**
     * 根据别名推送通知 :所有平台
     *
     * @param alias   别名
     * @param content 通知内容
     * @return 推送载体
     */
    public static PushPayload buildPushObject_all_alias_alert(String alias, String content) {
        // 所有平台
        Platform all = Platform.all();
        // 设置推送目标
        Audience target = Audience.alias(alias);
        // 设置通知 安卓、iOS、winPhone
        AndroidNotification androidNotification = AndroidNotification.newBuilder().setAlert(content).build();
        IosNotification iosNotification = IosNotification.newBuilder().setAlert(content).build();
        WinphoneNotification winphoneNotification = WinphoneNotification.newBuilder().setAlert(content).build();
        // 可选	推送参数
        Options options = Options.newBuilder()
                .setApnsProduction(false)//true-推送生产环境 false-推送开发环境（测试使用参数）
                .setTimeToLive(90)//消息在JPush服务器的失效时间（测试使用参数）
                .build();
        return PushPayload.newBuilder().setPlatform(all).setAudience(target)
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(androidNotification)
                        .addPlatformNotification(iosNotification)
                        .build())
                .setOptions(options)
                .build();
    }

    /**
     * 所有平台
     */
    public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }

    public static void pushJson(String pushUrl, String alias, String alert,
                                String appKey, String masterSecret, PushMsg pushMsg) {
        try {
            String result = push(pushUrl, alias, alert, appKey, masterSecret,
                    apns_production, time_to_live, pushMsg);
            JSONObject resData = JSONObject.parseObject(result);
            if (!resData.containsKey("error")) {
                LOGGER.info("针对别名为" + alias + "的信息推送成功！");
            } else {
                LOGGER.error("针对别名为" + alias + "的信息推送失败！");
            }
        } catch (Exception e) {
            LOGGER.error("针对别名为" + alias + "的信息推送失败！", e);
        }
    }

    /**
     * 推送方法-调用极光API
     *
     * @param reqUrl
     * @param alias
     * @param alert
     * @return result
     */
    public static String push(String reqUrl, String alias, String alert, String appKey, String masterSecret,
                              boolean apns_production, int time_to_live, PushMsg pushMsg) {
        String base64_auth_string = encryptBASE64(appKey + ":" + masterSecret);
        String authorization = "Basic " + base64_auth_string;
        return sendPostRequest(reqUrl, generateJson(alias, alert, apns_production, time_to_live, pushMsg).toString(),
                "UTF-8", authorization);
    }

    /**
     * 发送Post请求（json格式）
     *
     * @param reqURL
     * @param data
     * @param encodeCharset
     * @param authorization
     * @return result
     */
    @SuppressWarnings("resource")
    public static String sendPostRequest(String reqURL, String data, String encodeCharset, String authorization) {
        HttpPost httpPost = new HttpPost(reqURL);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        String result = "";
        try {
            StringEntity entity = new StringEntity(data, encodeCharset);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization", authorization.trim());
            response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), encodeCharset);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下", e);
        } finally {
            client.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * 组装极光推送专用json串
     *
     * @param alias
     * @param alert
     * @return json
     */
    public static JSONObject generateJson(String alias, String alert, boolean apns_production,
                                          int time_to_live, PushMsg pushMsg) {
        JSONObject json = new JSONObject();
        JSONArray platform = new JSONArray();// 平台
        platform.add("android");
        platform.add("ios");

        JSONObject audience = new JSONObject();// 推送目标
        JSONArray alias1 = new JSONArray();
        alias1.add(alias);
        audience.put("alias", alias1);

        JSONObject notification = new JSONObject();// 通知内容
        JSONObject android = new JSONObject();// android通知内容
        android.put("alert", alert);
        android.put("builder_id", 1);
        android.put("extras", pushMsg);

        JSONObject ios = new JSONObject();// ios通知内容
        ios.put("alert", alert);
        ios.put("sound", "default");
        ios.put("badge", "+1");
        ios.put("extras", pushMsg);
        notification.put("android", android);
        notification.put("ios", ios);

        JSONObject options = new JSONObject();// 设置参数
        options.put("time_to_live", Integer.valueOf(time_to_live));
        options.put("apns_production", apns_production);

        json.put("platform", platform);
        json.put("audience", audience);
        json.put("notification", notification);
        json.put("options", options);
        return json;

    }

    /**
     * BASE64加密工具
     */
    public static String encryptBASE64(String str) {
        byte[] key = str.getBytes();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String strs = base64Encoder.encodeBuffer(key);
        return strs;
    }


}