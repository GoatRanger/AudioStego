package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;

public class ShareSDK {
    public static final String SDK_TAG = "SHARESDK";
    public static final int SDK_VERSION_INT = 67;
    private static f a;
    private static boolean b = true;

    public static void initSDK(Context context) {
        initSDK(context, null, true);
    }

    public static void initSDK(Context context, boolean z) {
        initSDK(context, null, z);
    }

    public static void initSDK(Context context, String str) {
        initSDK(context, str, true);
    }

    public static void initSDK(Context context, String str, boolean z) {
        if (DeviceHelper.getInstance(context) == null) {
            throw new RuntimeException("The param of context is null which in ShareSDK.initSDK(context)!");
        } else if (a == null) {
            f fVar = new f(context, str);
            fVar.a(z);
            fVar.startThread();
            a = fVar;
        }
    }

    public static void stopSDK(Context context) {
        stopSDK();
    }

    public static void stopSDK() {
    }

    private static void b() {
        if (a == null) {
            throw new RuntimeException("Please call ShareSDK.initSDK(Context) in the main process before any action.");
        }
    }

    public static void registerService(Class<? extends Service> cls) {
        b();
        a.a((Class) cls);
    }

    public static void unregisterService(Class<? extends Service> cls) {
        b();
        a.b((Class) cls);
    }

    public static <T extends Service> T getService(Class<T> cls) {
        b();
        return a.c((Class) cls);
    }

    public static void registerPlatform(Class<? extends CustomPlatform> cls) {
        b();
        a.d(cls);
    }

    public static void unregisterPlatform(Class<? extends CustomPlatform> cls) {
        b();
        a.e(cls);
    }

    public static String getSDKVersionName() {
        b();
        return a.b();
    }

    public static int getSDKVersionCode() {
        return 67;
    }

    public static void setConnTimeout(int i) {
        b();
        a.a(i);
    }

    public static void setReadTimeout(int i) {
        b();
        a.b(i);
    }

    public static void removeCookieOnAuthorize(boolean z) {
        b();
        a.b(z);
    }

    public static void deleteCache() {
        b();
        a.e();
    }

    @Deprecated
    public static synchronized Platform[] getPlatformList(Context context) {
        Platform[] platformList;
        synchronized (ShareSDK.class) {
            platformList = getPlatformList();
        }
        return platformList;
    }

    public static synchronized Platform[] getPlatformList() {
        Platform[] a;
        synchronized (ShareSDK.class) {
            b();
            a = a.a();
        }
        return a;
    }

    @Deprecated
    public static Platform getPlatform(Context context, String str) {
        b();
        return a.a(str);
    }

    public static Platform getPlatform(String str) {
        b();
        return a.a(str);
    }

    public static void logDemoEvent(int i, Platform platform) {
        b();
        a.a(i, platform);
    }

    public static void logApiEvent(String str, int i) {
        b();
        a.a(str, i);
    }

    public static void setPlatformDevInfo(String str, HashMap<String, Object> hashMap) {
        b();
        a.a(str, (HashMap) hashMap);
    }

    public static String platformIdToName(int i) {
        b();
        return a.c(i);
    }

    public static int platformNameToId(String str) {
        b();
        return a.b(str);
    }

    public static boolean isRemoveCookieOnAuthorize() {
        b();
        return a.c();
    }

    public static void closeDebug() {
        b = false;
    }

    public static boolean isDebug() {
        return b;
    }

    static void a(String str, String str2) {
        b();
        a.a(str, str2);
    }

    static void a(int i, int i2) {
        b();
        a.a(i, i2);
    }

    static String b(String str, String str2) {
        b();
        return a.b(str, str2);
    }

    static String a(int i, String str) {
        b();
        return a.a(i, str);
    }

    static boolean a() {
        b();
        return a.d();
    }

    static boolean a(HashMap<String, Object> hashMap) {
        b();
        return a.a((HashMap) hashMap);
    }

    static boolean b(HashMap<String, Object> hashMap) {
        b();
        return a.b((HashMap) hashMap);
    }

    static String a(String str, boolean z, int i, String str2) {
        b();
        return a.a(str, z, i, str2);
    }

    static String a(String str) {
        b();
        return a.c(str);
    }

    static String a(Bitmap bitmap) {
        b();
        return a.a(bitmap);
    }
}
