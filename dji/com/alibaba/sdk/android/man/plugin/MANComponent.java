package com.alibaba.sdk.android.man.plugin;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.app.AppContext;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.man.util.MANLog;
import com.alibaba.sdk.android.plugin.PluginContext;
import com.alibaba.sdk.android.plugin.PluginLifecycleException;
import com.alibaba.sdk.android.plugin.PluginSyncLifecycleAdapter;
import java.util.Collections;

public class MANComponent implements PluginSyncLifecycleAdapter {
    public void syncStart(AppContext appContext, PluginContext pluginContext) throws PluginLifecycleException {
        Class[] clsArr = new Class[]{MANService.class};
        appContext.registerService(clsArr, MANServiceProvider.getService(), Collections.singletonMap(SdkConstants.ISV_SCOPE_FLAG, "true"));
        MANServiceProvider.getService().getMANAnalytics().initMANInternal(appContext.getAndroidContext());
        MANLog.Logi("MANComponent", "[MANComponent] - onesdk init MobileAnalytics success.");
    }
}
