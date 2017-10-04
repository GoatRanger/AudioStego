package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.ReportInitializedException;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.z;
import java.util.Map;

public class InnerAPI {
    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            z.e("post u3d fail args null", new Object[0]);
        }
        c a = c.a();
        if (a == null) {
            throw new ReportInitializedException("post u3d fail,Report has not been initialed! pls to call method 'initCrashReport' first!");
        }
        z.a("post u3d crash %s %s", str, str2);
        a.a(Thread.currentThread(), str, str2, str3);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            z.e("post cocos2d-x fail args null", new Object[0]);
        } else if (i == 5 || i == 6) {
            c a = c.a();
            if (a == null) {
                throw new ReportInitializedException("post cocos2d-x fail,Report has not been initialed! pleas call method 'initCrashReport' first!");
            }
            z.a("post cocos2d-x crash %s %s", str, str2);
            a.a(Thread.currentThread(), i, str, str2, str3);
        } else {
            z.e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            z.e("post h5 fail args null", new Object[0]);
            return;
        }
        c a = c.a();
        if (a == null) {
            throw new ReportInitializedException("Failed to post H5 crash, CrashReport has not been initialed! Pleas call method 'initCrashReport' first!");
        }
        z.a("post h5 crash %s %s", str, str2);
        a.a(thread, str, str2, str3, (Map) map);
    }
}
