package com.alibaba.sdk.android.device;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.ut.UTConstants;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.CommonUtils;
import com.alibaba.sdk.android.util.ReflectionUtils;
import java.util.Collections;
import java.util.Map;

public class DeviceInfo {
    public static String deviceId;

    public static void init(Context context) {
        if (TextUtils.isEmpty(deviceId)) {
            String property = AlibabaSDK.getProperty(SdkConstants.KERNEL_NAME, "utClassName");
            if (property == null) {
                property = "com.ut.device.UTDevice";
            }
            Class loadClassQuietly = ReflectionUtils.loadClassQuietly(property);
            if (loadClassQuietly != null) {
                UserTrackerService userTrackerService;
                try {
                    Object invoke = loadClassQuietly.getMethod("getUtdid", new Class[]{Context.class}).invoke(null, new Object[]{a.a});
                    if (invoke == null) {
                        userTrackerService = (UserTrackerService) a.e.a(UserTrackerService.class, null);
                        if (userTrackerService != null) {
                            userTrackerService.sendCustomHit(UTConstants.E_SDK_GETUTDID, 0, "error", null);
                        }
                    }
                    property = (String) invoke;
                } catch (Throwable e) {
                    Throwable th = e;
                    userTrackerService = (UserTrackerService) a.e.a(UserTrackerService.class, null);
                    if (userTrackerService != null) {
                        Map singletonMap = Collections.singletonMap("msg", CommonUtils.toString(th));
                        userTrackerService.sendCustomHit(UTConstants.E_SDK_GETUTDID, 0, "error", singletonMap);
                    }
                }
                if (property != null) {
                    a.f.postTask(new a());
                }
                deviceId = property;
                a.f.postTask(new b());
                return;
            }
            property = null;
            if (property != null) {
                deviceId = property;
                a.f.postTask(new b());
                return;
            }
            a.f.postTask(new a());
        }
    }
}
