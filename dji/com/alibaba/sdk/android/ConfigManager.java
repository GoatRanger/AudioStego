package com.alibaba.sdk.android;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    public static String API_VERSION;
    public static int APP_KEY_INDEX = 0;
    public static boolean DEBUG = false;
    public static String INIT_SERVER_HOST = "sdkinit.taobao.com";
    public static final int ONLINE_INDEX = Environment.ONLINE.ordinal();
    public static String POSTFIX_OF_SECURITY_JPG = "";
    public static String POSTFIX_OF_SECURITY_JPG_USER_SET;
    public static final int PRE_INDEX = Environment.PRE.ordinal();
    public static final int SANDBOX_INDEX = Environment.SANDBOX.ordinal();
    public static final String SDK_INTERNAL_VERSION = ("a_" + TAE_SDK_VERSION.toString());
    public static final Version TAE_SDK_VERSION = new Version(1, 8, 0);
    public static final int TEST_INDEX = Environment.TEST.ordinal();
    private static final ConfigManager a = new ConfigManager();
    public static final Map<String, String> userProperties = new HashMap();
    private Environment b;

    private ConfigManager() {
    }

    public Environment getEnvironment() {
        return this.b;
    }

    public static ConfigManager getInstance() {
        return a;
    }

    public void init(int i) {
        this.b = Environment.values()[i];
        INIT_SERVER_HOST = new String[]{"10.189.224.121", "sdkinit.taobao.com", "sdkinit.taobao.com", "sdkinit.tbsandbox.com"}[i];
        API_VERSION = new String[]{"1.0.0.daily", "1.0.0", "1.0.0", "1.0.0.daily"}[i];
        if (POSTFIX_OF_SECURITY_JPG_USER_SET == null) {
            POSTFIX_OF_SECURITY_JPG = new String[]{"test", "", "", "test"}[i];
            return;
        }
        POSTFIX_OF_SECURITY_JPG = POSTFIX_OF_SECURITY_JPG_USER_SET;
    }

    public static int getAppKeyIndex() {
        return APP_KEY_INDEX;
    }
}
