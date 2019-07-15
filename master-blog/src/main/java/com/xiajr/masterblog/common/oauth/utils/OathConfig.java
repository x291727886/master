package com.xiajr.masterblog.common.oauth.utils;


import com.xiajr.masterblog.common.oauth.APIConfig;

public class OathConfig {
    public static String getValue(String key) {
        return APIConfig.getInstance().getValue(key);
    }
}
