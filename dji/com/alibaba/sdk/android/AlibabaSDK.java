package com.alibaba.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.callback.ResultCallback;
import com.alibaba.sdk.android.plugin.PluginContext;
import com.alibaba.sdk.android.plugin.config.PluginSystemConfigurations;
import com.alibaba.sdk.android.ut.UTConstants;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.CommonUtils;
import java.util.Collections;
import java.util.Map;

public class AlibabaSDK {
    private static final Map<String, String> a = Collections.singletonMap(SdkConstants.ISV_SCOPE_FLAG, "true");
    private static Environment b;

    public static Version getVersion() {
        return ConfigManager.TAE_SDK_VERSION;
    }

    public static void setSecGuardImagePostfix(String str) {
        ConfigManager.POSTFIX_OF_SECURITY_JPG_USER_SET = str;
    }

    public static void setGlobalProperty(String str, String str2) {
        PluginSystemConfigurations pluginSystemConfigurations = (PluginSystemConfigurations) a.e.a(PluginSystemConfigurations.class, null);
        if (pluginSystemConfigurations != null) {
            pluginSystemConfigurations.setGlobalProperty(str, str2);
        } else {
            ConfigManager.userProperties.put("$global$." + str, str2);
        }
    }

    public static String getGlobalProperty(String str) {
        PluginSystemConfigurations pluginSystemConfigurations = (PluginSystemConfigurations) a.e.a(PluginSystemConfigurations.class, null);
        if (pluginSystemConfigurations != null) {
            return pluginSystemConfigurations.getGlobalProperty(str);
        }
        return (String) ConfigManager.userProperties.get("$global$." + str);
    }

    public static void setProperty(String str, String str2, String str3) {
        PluginSystemConfigurations pluginSystemConfigurations = (PluginSystemConfigurations) a.e.a(PluginSystemConfigurations.class, null);
        if (pluginSystemConfigurations != null) {
            pluginSystemConfigurations.setPluginProperty(str, str2, str3);
        } else {
            ConfigManager.userProperties.put(str + "." + str2, str3);
        }
    }

    public static String getProperty(String str, String str2) {
        PluginContext b = com.alibaba.sdk.android.plugin.a.a.b(str);
        return b == null ? (String) ConfigManager.userProperties.get(str + "." + str2) : b.getPluginConfigurations().getStringValue(str2);
    }

    public static <T> T getService(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        if (cls.getName().startsWith("com.taobao.tae.sdk.api.")) {
            return a.e.a(cls, null);
        }
        return a.e.a(cls, a);
    }

    public static <T> void getService(Activity activity, Class<T> cls, ResultCallback<T> resultCallback) {
        CommonUtils.startInitWaitTask(activity, resultCallback, new a(cls, resultCallback), UTConstants.E_GETSERVICE);
    }

    public static void setEnvironment(Environment environment) {
        b = environment;
    }

    public static void asyncInit(Context context) {
        asyncInit(context, null);
    }

    public static void asyncInit(Context context, InitResultCallback initResultCallback) {
        a(context, initResultCallback);
    }

    public static void asyncInitWithFinish(Context context, InitResultCallback initResultCallback) {
        a(context, initResultCallback).b();
    }

    public static void setAppVersion(String str) {
        if (str != null) {
            UserTrackerService userTrackerService = (UserTrackerService) a.e.a(UserTrackerService.class, null);
            if (userTrackerService != null) {
                userTrackerService.updateUserTrackerProperties(Collections.singletonMap(UTConstants.APP_VERSION, str));
            }
            setGlobalProperty("appVersion", str);
        }
    }

    public static boolean isInitSucceed() {
        return a.b == null ? false : a.b.booleanValue();
    }

    public static void turnOnDebug() {
        Log.w("AlibabaSDK", "************************************\nDebug is enabled, make sure to turn it off in the production environment\n************************************");
        ConfigManager.DEBUG = true;
    }

    private static com.alibaba.sdk.android.task.a a(Context context, InitResultCallback initResultCallback) {
        a.a = context.getApplicationContext();
        if (b == null) {
            b = Environment.ONLINE;
        }
        Object aVar = new com.alibaba.sdk.android.task.a(initResultCallback, Integer.valueOf(b.ordinal()));
        if (aVar.a()) {
            a.f.postHandlerTask(aVar);
        }
        return aVar;
    }
}
