package com.tencent.bugly.crashreport;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.alipay.sdk.j.i;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.c;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CrashReport {
    private static Context a;
    private static String b = null;
    public static boolean isDebug;

    public static class CrashHandleCallback {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }

    public static class UserStrategy {
        private String a;
        private String b;
        private String c;
        private long d;
        private String e;
        private String f;
        private Map<String, String> g;
        private CrashHandleCallback h;
        private boolean i = true;
        private boolean j = true;
        private boolean k = true;

        public UserStrategy(Context context) {
            a a = a.a(ag.a(context));
            this.a = a.e();
            this.b = a.B();
            this.c = a.f();
            this.e = null;
            this.d = 0;
            this.g = null;
        }

        public synchronized void setBuglyLogUpload(boolean z) {
            this.k = z;
        }

        public synchronized boolean isBuglyLogUpload() {
            return this.k;
        }

        public synchronized String getAppVersion() {
            return this.a;
        }

        public synchronized UserStrategy setAppVersion(String str) {
            this.a = str;
            return this;
        }

        public synchronized String getAppChannel() {
            return this.b;
        }

        public synchronized UserStrategy setAppChannel(String str) {
            this.b = str;
            return this;
        }

        public synchronized String getAppPackageName() {
            return this.c;
        }

        public synchronized UserStrategy setAppPackageName(String str) {
            this.c = str;
            return this;
        }

        public synchronized long getAppReportDelay() {
            return this.d;
        }

        public synchronized UserStrategy setAppReportDelay(long j) {
            this.d = j;
            return this;
        }

        public synchronized String getLibBuglySOFilePath() {
            return this.e;
        }

        public synchronized void setLibBuglySOFilePath(String str) {
            this.e = str;
        }

        public synchronized String getDeviceID() {
            return this.f;
        }

        public synchronized void setDeviceID(String str) {
            this.f = str;
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.h;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.h = crashHandleCallback;
        }

        public synchronized boolean isEnableNativeCrashMonitor() {
            return this.i;
        }

        public synchronized void setEnableNativeCrashMonitor(boolean z) {
            this.i = z;
        }

        public synchronized boolean isEnableANRCrashMonitor() {
            return this.j;
        }

        public synchronized void setEnableANRCrashMonitor(boolean z) {
            this.j = z;
        }

        public synchronized void setOuterSdkVersion(Map<String, String> map) {
            this.g = map;
        }

        public synchronized void putOuterSdkVersion(String str, String str2) {
            if (!(str == null || str2 == null)) {
                if (this.g == null) {
                    this.g = new HashMap();
                }
                this.g.put(str, str2);
            }
        }

        public synchronized Map<String, String> getOuterSdkVersion() {
            return this.g;
        }
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z) {
        synchronized (CrashReport.class) {
            initCrashReport(context, str, z, null);
        }
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        synchronized (CrashReport.class) {
            if (z) {
                isDebug = true;
                z.a(new ac());
                z.d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- 'isDebug' is enabled. Now is running in debug model, please disable it when you release.", new Object[0]);
                z.e("--------------------------------------------------------------------------------------------", new Object[0]);
                z.d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                z.d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                z.d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                z.d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                z.e("--------------------------------------------------------------------------------------------", new Object[0]);
                z.b("[init] bugly in debug mode.", new Object[0]);
            }
            if (userStrategy == null || userStrategy.k) {
                ab.a(context);
                z.a("BuglyLog is inited.", new Object[0]);
            }
            if (a != null) {
                z.d("already inited ! nothing to do !", new Object[0]);
            } else {
                Context a = ag.a(context);
                a = a;
                if (a == null) {
                    throw new ReportInitializedException("init arg 'appContext' should not be null!");
                }
                z.b("your appid is set to: %s", str);
                a a2 = a.a(a);
                a(a, a2);
                List<String> H = a2.H();
                Object i = a2.i().equals("") ? "bugly" : a2.i();
                if (H == null || !H.contains(i)) {
                    String substring;
                    if (H != null && H.size() > 0) {
                        for (String substring2 : H) {
                            z.a("[Configuration] channel disabled: " + substring2, new Object[0]);
                        }
                    }
                    z.a(a2.i() + " crash report start init!", new Object[0]);
                    z.b("[init] bugly start init...", new Object[0]);
                    if (str == null) {
                        throw new ReportInitializedException("init arg 'crashReportAppID' should not be null!");
                    }
                    a2.a(str);
                    z.a("setted APPID:%s", str);
                    if (userStrategy != null) {
                        String b = userStrategy.a;
                        if (!TextUtils.isEmpty(b)) {
                            if (b.length() > 100) {
                                substring2 = b.substring(0, 100);
                                z.d("appVersion %s length is over limit %d substring to %s", b, Integer.valueOf(100), substring2);
                            } else {
                                substring2 = b;
                            }
                            a2.c(substring2);
                            z.a("setted APPVERSION:%s", userStrategy.a);
                        }
                        substring2 = userStrategy.b;
                        if (!TextUtils.isEmpty(substring2)) {
                            if (substring2.length() > 100) {
                                z.d("appChannel %s length is over limit %d substring to %s", substring2, Integer.valueOf(100), substring2.substring(0, 100));
                                substring2 = b;
                            }
                            a2.g(substring2);
                            z.a("setted APPCHANNEL:%s", userStrategy.b);
                        }
                        String str2 = substring2;
                        b = userStrategy.c;
                        if (!TextUtils.isEmpty(b)) {
                            if (b.length() > 100) {
                                substring2 = b.substring(0, 100);
                                z.d("appPackageName %s length is over limit %d substring to %s", b, Integer.valueOf(100), substring2);
                            } else {
                                substring2 = b;
                            }
                            a2.b(substring2);
                            z.a("setted PACKAGENAME:%s", userStrategy.c);
                        }
                        i = userStrategy.e;
                        if (!TextUtils.isEmpty(i)) {
                            a2.h(i);
                            z.a("setted libBugly.so file path :%s", i);
                        }
                        substring2 = userStrategy.f;
                        if (substring2 != null) {
                            if (substring2.length() > 100) {
                                substring2 = substring2.substring(0, 100);
                                z.d("deviceId %s length is over limit %d substring to %s", str2, Integer.valueOf(100), substring2);
                            }
                            a2.e(substring2);
                            z.a("setted deviceId :%s", substring2);
                        }
                        Map g = userStrategy.g;
                        if (g != null && g.size() > 0) {
                            for (Entry entry : g.entrySet()) {
                                a(a, "SDK_" + ((String) entry.getKey()), (String) entry.getValue());
                            }
                        }
                    }
                    p.a().a(a);
                    y a3 = y.a();
                    q a4 = q.a(a);
                    w a5 = w.a(a, a3, a2, a4);
                    c a6 = c.a(a, a2, new StrategyBean(), q.a(a), a5, a3);
                    if (userStrategy == null || userStrategy.d <= 0) {
                        p.a().a(0);
                    } else {
                        p.a().a(userStrategy.d);
                        z.a("setted APP_REPORT_DELAY %d", Long.valueOf(userStrategy.d));
                    }
                    CrashHandleCallback crashHandleCallback = null;
                    if (!(userStrategy == null || userStrategy.getCrashHandleCallback() == null)) {
                        crashHandleCallback = userStrategy.getCrashHandleCallback();
                        z.a("setted CrashHanldeCallback", new Object[0]);
                    }
                    com.tencent.bugly.crashreport.crash.c a7 = com.tencent.bugly.crashreport.crash.c.a(a, a4, a6, a5, a2, a3, z, crashHandleCallback);
                    a7.d();
                    if (userStrategy == null || userStrategy.isEnableNativeCrashMonitor()) {
                        a7.f();
                    } else {
                        z.a("closed native!", new Object[0]);
                        a7.e();
                    }
                    a7.g();
                    BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
                    instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                    instance.regist(a);
                    a(a2);
                    z.a("crash report inited!", new Object[0]);
                    z.b("[init] bugly init finished.", new Object[0]);
                } else {
                    z.a("[init] bugly(%s) is closed.", i);
                }
            }
        }
    }

    private static void a(a aVar) {
        Object obj;
        String i;
        try {
            Class cls = Class.forName("com.tencent.bugly.unity.UnityAgent");
            obj = "com.tencent.bugly";
            i = aVar.i();
            if (!"".equals(i)) {
                obj = obj + "." + i;
            }
            ae.a(cls, "sdkPackageName", obj, null);
        } catch (Throwable th) {
            z.a("no unity agent", new Object[0]);
        }
        try {
            cls = Class.forName("com.tencent.bugly.cococs.Cocos2dxAgent");
            obj = "com.tencent.bugly";
            i = aVar.i();
            if (!"".equals(i)) {
                obj = obj + "." + i;
            }
            ae.a(cls, "sdkPackageName", obj, null);
        } catch (Throwable th2) {
            z.a("no cocos agent", new Object[0]);
        }
    }

    private static void a(Context context, a aVar) {
        List I = aVar.I();
        if (I == null || I.size() == 0) {
            z.a("not have bugly channel version", new Object[0]);
        } else if (I.size() != 1) {
            StringBuilder append = new StringBuilder(16).append((String) I.get(1));
            for (int i = 2; i < I.size(); i++) {
                append.append(",").append((String) I.get(i));
            }
            if (!a(I, aVar.H())) {
                String format = String.format("# BUGLY CRASH REPORTER EXISTS IN MULTIPLE SDK. FOR MORE EFFICIENCY, \n# PLEASE SET <meta-data> IN [AndroidManifest.xml]:\n# <application>\n# ...\n#     <meta-data android:name=\"BUGLY_DISABLE\" android:value=\"%s\"/>\n# ...\n# </application>\n# FOR MORE INFOMATION, VISIT:\n# http://bugly.qq.com/androidsdk", new Object[]{append});
                String format2 = String.format("# 您的App中含有多个异常上报模块，为了节省您的资源，\n# 请在[AndroidManifest.xml]中添加meta-data配置：[AndroidManifest.xml]:\n# <application>\n# ...\n#     <meta-data android:name=\"BUGLY_DISABLE\" android:value=\"%s\"/>\n# ...\n# </application>\n# 更多信息请参见：\n# http://bugly.qq.com/androidsdk", new Object[]{append});
                z.d("--------------------------BUGLY TIPS--------------------------", new Object[0]);
                z.d(format, new Object[0]);
                z.d("--------------------------------------------------------------", new Object[0]);
                z.d(format2, new Object[0]);
                z.d("--------------------------------------------------------------", new Object[0]);
            }
        }
    }

    private static boolean a(List<String> list, List<String> list2) {
        if (list2 == null || list2.size() == 0) {
            return false;
        }
        int i = 0;
        for (String contains : list2) {
            int i2;
            if (list.contains(contains)) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        if (i >= list.size() - 1) {
            return true;
        }
        return false;
    }

    public static synchronized String getAppID() {
        String d;
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            d = a.a(a).d();
        }
        return d;
    }

    public static synchronized void setUserId(String str) {
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            if (str != null) {
                if (str.length() > 100) {
                    z.d("userId %s length is over limit %d substring to %s", str, Integer.valueOf(100), str.substring(0, 100));
                    str = r0;
                }
            }
            a.a(a).d(str);
            z.b("[param] set userId : %s", str);
            c.a().f();
        }
    }

    public static synchronized String getUserId() {
        String m;
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            m = a.a(a).m();
        }
        return m;
    }

    public static synchronized String getAppVer() {
        String e;
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            e = a.a(a).e();
        }
        return e;
    }

    public static synchronized String getAppChannel() {
        String B;
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            B = a.a(a).B();
        }
        return B;
    }

    public static synchronized boolean isLastSessionCrash() {
        boolean b;
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            b = com.tencent.bugly.crashreport.crash.c.a().b();
        }
        return b;
    }

    public static synchronized void testJavaCrash() {
        synchronized (CrashReport.class) {
            z.d("Test java crash...", new Object[0]);
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static synchronized void testNativeCrash() {
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            z.a("start to create a native crash for test!", new Object[0]);
            com.tencent.bugly.crashreport.crash.c.a().j();
        }
    }

    public static synchronized void testANRCrash() {
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            z.a("start to create a anr crash for test!", new Object[0]);
            com.tencent.bugly.crashreport.crash.c.a().k();
        }
    }

    public static synchronized void postCatchedException(Throwable th) {
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            com.tencent.bugly.crashreport.crash.c.a().a(Thread.currentThread(), th, false);
        }
    }

    public static synchronized void closeNativeReport() {
        synchronized (CrashReport.class) {
            if (a == null) {
                throw new ReportInitializedException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
            com.tencent.bugly.crashreport.crash.c.a().e();
        }
    }

    public static synchronized void closeCrashReport() {
        synchronized (CrashReport.class) {
            if (a != null) {
                com.tencent.bugly.crashreport.crash.c.a().i();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (context == null) {
            throw new ReportInitializedException("setTag args context should not be null");
        } else if (i <= 0) {
            throw new ReportInitializedException("setTag args tagId should > 0");
        } else {
            a.a(context).a(i);
            z.b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (context != null) {
            return a.a(context).N();
        }
        throw new ReportInitializedException("getUserSceneTagId args context should not be null");
    }

    public static String getUserData(Context context, String str) {
        if (context == null) {
            throw new ReportInitializedException("getUserDataValue args context should not be null");
        } else if (ag.b(str)) {
            return null;
        } else {
            return a.a(context).k(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (context == null) {
            throw new ReportInitializedException("putUserData args context should not be null");
        } else if (ag.b(str)) {
            throw new ReportInitializedException("putUserData args key should not be null");
        } else if (ag.b(str2)) {
            z.d("putUserData args value should not be null", new Object[0]);
        } else if (str.matches("[a-zA-Z[0-9]]+")) {
            if (str2.length() > 200) {
                z.d("user data value length over limit %d , has been cutted!", Integer.valueOf(200));
                str2 = str2.substring(0, 200);
            }
            a a = a.a(context);
            if (a.L().contains(str)) {
                a.a(context).a(str, str2);
                z.c("replace KV %s %s", str, str2);
            } else if (a.K() >= 10) {
                z.d("user data size is over limit %d , will drop this new key %s", Integer.valueOf(10), str);
            } else if (str.length() > 50) {
                z.d("user data key length over limit %d , will drop this new key %s", Integer.valueOf(50), str);
            } else {
                a.a(context).a(str, str2);
                z.b("[param] set user data: %s - %s", str, str2);
            }
        } else {
            throw new ReportInitializedException("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + i.d);
        }
    }

    private static void a(Context context, String str, String str2) {
        if (context != null && !ag.b(str) && !ag.b(str2)) {
            String replace = str.replace("[a-zA-Z[0-9]]+", "");
            if (replace.length() > 50) {
                z.d("putSdkData key length over limit %d , will drop this new key %s", Integer.valueOf(50), replace);
                return;
            }
            if (str2.length() > 200) {
                z.d("putSdkData value length over limit %d , has been cutted!", Integer.valueOf(200));
                str2 = str2.substring(0, 200);
            }
            a.a(context).b(replace, str2);
            z.b("[param] putSdkData data: %s - %s", replace, str2);
        }
    }

    public static String removeUserData(Context context, String str) {
        if (context == null) {
            throw new ReportInitializedException("removeUserData args context should not be null");
        } else if (ag.b(str)) {
            return null;
        } else {
            z.b("[param] remove user data: %s", str);
            return a.a(context).j(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (context != null) {
            return a.a(context).L();
        }
        throw new ReportInitializedException("getAllUserDataKeys args context should not be null");
    }

    public static int getUserDatasSize(Context context) {
        if (context != null) {
            return a.a(context).K();
        }
        throw new ReportInitializedException("getUserDatasSize args context should not be null");
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z, boolean z2) {
        if (webView.getUrl() == null) {
            return false;
        }
        if (b != null && b.equals(webView.getUrl())) {
            return true;
        }
        b = webView.getUrl();
        if (z2 || VERSION.SDK_INT >= 19) {
            z.a("Set webview monitor.", new Object[0]);
            WebSettings settings = webView.getSettings();
            if (!settings.getJavaScriptEnabled()) {
                z.a("Enable the javascript needed by webview monitor.", new Object[0]);
                settings.setJavaScriptEnabled(true);
            }
            H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webView);
            if (instance != null) {
                z.a("Add a secure javascript interface to the webview.", new Object[0]);
                webView.addJavascriptInterface(instance, "exceptionUploader");
            }
            if (z) {
                if (a == null) {
                    z.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
                    return false;
                }
                z.a("Inject bugly.js(v%s) to the webview.", aa.b());
                webView.loadUrl("javascript:" + aa.a());
            }
            return true;
        }
        z.e("This interface is only available for Android 4.4 or later.", new Object[0]);
        return false;
    }
}
