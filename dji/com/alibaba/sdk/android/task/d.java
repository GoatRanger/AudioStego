package com.alibaba.sdk.android.task;

import com.alibaba.sdk.android.device.DeviceInfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

final class d implements InvocationHandler {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.getName().equals("getDefaultUserTrackerId")) {
            return DeviceInfo.deviceId;
        }
        return null;
    }
}
