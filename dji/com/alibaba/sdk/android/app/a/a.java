package com.alibaba.sdk.android.app.a;

import android.content.Context;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.Environment;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.app.AppContext;
import com.alibaba.sdk.android.device.DeviceInfo;
import com.alibaba.sdk.android.registry.ServiceRegistration;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.CommonUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class a implements AppContext {
    private String a;
    private com.alibaba.sdk.android.registry.a b = com.alibaba.sdk.android.b.a.e;
    private List<ServiceRegistration> c = Collections.synchronizedList(new ArrayList());

    public a(String str) {
        this.a = str;
    }

    public final String getAppKey() {
        return AlibabaSDK.getGlobalProperty(SdkConstants.APP_KEY);
    }

    public final String getUserTrackerId() {
        if (DeviceInfo.deviceId != null) {
            return DeviceInfo.deviceId;
        }
        UserTrackerService userTrackerService = (UserTrackerService) com.alibaba.sdk.android.b.a.e.a(UserTrackerService.class, null);
        return userTrackerService == null ? null : userTrackerService.getDefaultUserTrackerId();
    }

    public final Context getAndroidContext() {
        return com.alibaba.sdk.android.b.a.a;
    }

    public final ServiceRegistration registerService(Class<?>[] clsArr, Object obj, Map<String, String> map) {
        Map hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put(SdkConstants.PLUGIN_VENDOR_KEY, this.a);
        ServiceRegistration a = this.b.a(clsArr, obj, hashMap);
        this.c.add(a);
        return a;
    }

    public final Object unregisterService(ServiceRegistration serviceRegistration) {
        this.c.remove(serviceRegistration);
        return this.b.a(serviceRegistration);
    }

    public final <T> T getService(Class<T> cls, Map<String, String> map) {
        return this.b.a(cls, map);
    }

    public final Environment getEnvironment() {
        return ConfigManager.getInstance().getEnvironment();
    }

    public final void a() {
        for (ServiceRegistration unregister : this.c) {
            unregister.unregister();
        }
    }

    public final <T> T[] getServices(Class<T> cls, Map<String, String> map) {
        return this.b.b(cls, map);
    }

    public final <T> T getService(Class<T> cls) {
        return this.b.a(cls, null);
    }

    public final <T> T[] getServices(Class<T> cls) {
        return this.b.b(cls, null);
    }

    public final boolean isDebugEnabled() {
        return CommonUtils.isDebuggable();
    }
}
