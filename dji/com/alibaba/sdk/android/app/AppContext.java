package com.alibaba.sdk.android.app;

import android.content.Context;
import com.alibaba.sdk.android.Environment;
import com.alibaba.sdk.android.registry.ServiceRegistration;
import java.util.Map;

public interface AppContext {
    Context getAndroidContext();

    String getAppKey();

    Environment getEnvironment();

    <T> T getService(Class<T> cls);

    <T> T getService(Class<T> cls, Map<String, String> map);

    <T> T[] getServices(Class<T> cls);

    <T> T[] getServices(Class<T> cls, Map<String, String> map);

    String getUserTrackerId();

    boolean isDebugEnabled();

    ServiceRegistration registerService(Class<?>[] clsArr, Object obj, Map<String, String> map);

    Object unregisterService(ServiceRegistration serviceRegistration);
}
