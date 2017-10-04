package com.alibaba.sdk.android.plugin;

import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.callback.FailureCallback;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.kernel.message.KernelMessageConstants;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.message.MessageUtils;
import com.alibaba.sdk.android.plugin.config.PluginSystemConfigurations;
import com.alibaba.sdk.android.registry.a;
import com.alibaba.sdk.android.repository.PluginRepository;
import com.alibaba.sdk.android.repository.a.c;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.util.CommonUtils;
import java.util.Collections;
import java.util.Map;

public class b {
    public static final b a = new b();
    private static final String b = b.class.getSimpleName();
    private volatile boolean c = false;
    private final Object d = new Object();

    private b() {
    }

    public final ResultCode a() {
        if (!this.c) {
            synchronized (this.d) {
                if (!this.c) {
                    Map singletonMap = Collections.singletonMap(SdkConstants.SYSTEM_SERVICE_KEY, "true");
                    a aVar = com.alibaba.sdk.android.b.a.e;
                    PluginRepository cVar = new c();
                    aVar.a(new Class[]{PluginRepository.class}, cVar, singletonMap);
                    Class[] clsArr = new Class[]{PluginSystemConfigurations.class};
                    aVar.a(clsArr, new com.alibaba.sdk.android.plugin.config.a.b(com.alibaba.sdk.android.b.a.a, cVar), singletonMap);
                    com.alibaba.sdk.android.plugin.a.b bVar = new com.alibaba.sdk.android.plugin.a.b(cVar);
                    aVar.a(new Class[]{PluginManager.class}, bVar, singletonMap);
                    this.c = true;
                }
            }
        }
        return ResultCode.SUCCESS;
    }

    public static boolean a(InitResultCallback initResultCallback) {
        PluginManager pluginManager = (PluginManager) com.alibaba.sdk.android.b.a.e.a(PluginManager.class, null);
        String[] startedPluginNames = ((PluginSystemConfigurations) com.alibaba.sdk.android.b.a.e.a(PluginSystemConfigurations.class, null)).getStartedPluginNames();
        int length = startedPluginNames.length;
        int i = 0;
        while (i < length) {
            String str = startedPluginNames[i];
            try {
                pluginManager.syncStartPlugin(str);
                i++;
            } catch (Throwable e) {
                AliSDKLogger.e(b, "fail to sync start plugin " + str, e);
                a(initResultCallback, e);
                return false;
            } catch (PluginNotFoundException e2) {
                Message createMessage = MessageUtils.createMessage(13, e2.getPluginName());
                CommonUtils.onFailure(initResultCallback, createMessage.code, createMessage.message);
                AliSDKLogger.log(b, createMessage);
                return false;
            }
        }
        return true;
    }

    public static boolean b(InitResultCallback initResultCallback) {
        PluginManager pluginManager = (PluginManager) com.alibaba.sdk.android.b.a.e.a(PluginManager.class, null);
        String[] startedPluginNames = ((PluginSystemConfigurations) com.alibaba.sdk.android.b.a.e.a(PluginSystemConfigurations.class, null)).getStartedPluginNames();
        int length = startedPluginNames.length;
        int i = 0;
        while (i < length) {
            String str = startedPluginNames[i];
            try {
                pluginManager.startPlugin(str);
                i++;
            } catch (Throwable e) {
                AliSDKLogger.e(b, "fail to async start plugin " + str, e);
                a(initResultCallback, e);
                return false;
            } catch (PluginNotFoundException e2) {
                Message createMessage = MessageUtils.createMessage(13, e2.getPluginName());
                CommonUtils.onFailure(initResultCallback, createMessage.code, createMessage.message);
                AliSDKLogger.log(b, createMessage);
                return false;
            }
        }
        return true;
    }

    private static void a(InitResultCallback initResultCallback, PluginLifecycleException pluginLifecycleException) {
        if (pluginLifecycleException.getResultCode() != null) {
            CommonUtils.onFailure((FailureCallback) initResultCallback, pluginLifecycleException.getResultCode());
            return;
        }
        Message createMessage = MessageUtils.createMessage(KernelMessageConstants.PLUGIN_INIT_FAILED, pluginLifecycleException.getPluginName(), pluginLifecycleException.getMessage());
        AliSDKLogger.log(b, createMessage);
        CommonUtils.onFailure(initResultCallback, createMessage.code, createMessage.message);
    }
}
