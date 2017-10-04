package com.alipay.sdk.j;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.alipay.sdk.app.a.c;
import com.alipay.sdk.app.h;
import com.alipay.sdk.app.i;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.android.tpush.common.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public final class l {
    static final String a = "com.alipay.android.app";
    public static final String b = "com.eg.android.AlipayGphone";
    private static final String c = "7.0.0";

    public static class a {
        public byte[] a;
        public int b;
    }

    public static Map<String, String> a(String str) {
        Map<String, String> hashMap = new HashMap();
        for (String str2 : str.split(com.alipay.sdk.h.a.b)) {
            int indexOf = str2.indexOf("=", 1);
            hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
        }
        return hashMap;
    }

    public static String a(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            if (length <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, length);
            }
            if (i <= 0) {
                return str3.substring(length);
            }
            return str3.substring(length, i);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String a(byte[] bArr) {
        try {
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (obj.indexOf("modulus") != -1) {
                return obj.substring(obj.indexOf("modulus") + 8, obj.lastIndexOf(",")).trim();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static a a(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
            if (packageInfo.packageName.equals(str)) {
                a aVar = new a();
                aVar.a = packageInfo.signatures[0].toByteArray();
                aVar.b = packageInfo.versionCode;
                return aVar;
            }
        }
        return null;
    }

    public static boolean a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(a, 128) == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b, 128);
            if (packageInfo == null) {
                return false;
            }
            int parseInt;
            String str = packageInfo.versionName;
            String str2 = c;
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            arrayList.addAll(Arrays.asList(str.split("\\.")));
            arrayList2.addAll(Arrays.asList(str2.split("\\.")));
            int max = Math.max(arrayList.size(), arrayList2.size());
            while (arrayList.size() < max) {
                arrayList.add("0");
            }
            while (arrayList2.size() < max) {
                arrayList2.add("0");
            }
            for (int i = 0; i < max; i++) {
                if (Integer.parseInt((String) arrayList.get(i)) != Integer.parseInt((String) arrayList2.get(i))) {
                    parseInt = Integer.parseInt((String) arrayList.get(i)) - Integer.parseInt((String) arrayList2.get(i));
                    break;
                }
            }
            parseInt = 0;
            if (parseInt < 0) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String c(Context context) {
        String a = a();
        String b = b();
        String d = d(context);
        return " (" + a + i.b + b + i.b + d + ";;" + e(context) + ")(sdk android)";
    }

    public static String a() {
        return "Android " + VERSION.RELEASE;
    }

    public static WebView a(Activity activity, String str, String str2) {
        Method method;
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(activity.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
        View linearLayout = new LinearLayout(activity);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        activity.setContentView(linearLayout, layoutParams);
        View webView = new WebView(activity);
        layoutParams.weight = 1.0f;
        webView.setVisibility(0);
        linearLayout.addView(webView, layoutParams);
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + c(activity));
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setDownloadListener(new m(activity));
        if (VERSION.SDK_INT >= 7) {
            try {
                method = webView.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                if (method != null) {
                    method.invoke(webView.getSettings(), new Object[]{Boolean.valueOf(true)});
                }
            } catch (Exception e) {
            }
        }
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
        }
        if (VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(1);
        }
        webView.loadUrl(str);
        return webView;
    }

    public static String b() {
        String d = d();
        int indexOf = d.indexOf("-");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        indexOf = d.indexOf("\n");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        return "Linux " + d;
    }

    private static String d() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            CharSequence readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (!matcher.matches()) {
                return "Unavailable";
            }
            if (matcher.groupCount() < 4) {
                return "Unavailable";
            }
            return new StringBuilder(matcher.group(1)).append("\n").append(matcher.group(2)).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(matcher.group(3)).append("\n").append(matcher.group(4)).toString();
        } catch (IOException e) {
            return "Unavailable";
        } catch (Throwable th) {
            bufferedReader.close();
        }
    }

    public static String d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.widthPixels);
        stringBuilder.append("*");
        stringBuilder.append(displayMetrics.heightPixels);
        return stringBuilder.toString();
    }

    private static DisplayMetrics g(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static String h(Context context) {
        String a = k.a(context);
        return a.substring(0, a.indexOf("://"));
    }

    private static String e() {
        return "-1;-1";
    }

    public static String c() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case 1:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case 2:
                    stringBuilder.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private static int a(String str, String str2) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList.addAll(Arrays.asList(str.split("\\.")));
        arrayList2.addAll(Arrays.asList(str2.split("\\.")));
        int max = Math.max(arrayList.size(), arrayList2.size());
        while (arrayList.size() < max) {
            arrayList.add("0");
        }
        while (arrayList2.size() < max) {
            arrayList2.add("0");
        }
        for (int i = 0; i < max; i++) {
            if (Integer.parseInt((String) arrayList.get(i)) != Integer.parseInt((String) arrayList2.get(i))) {
                return Integer.parseInt((String) arrayList.get(i)) - Integer.parseInt((String) arrayList2.get(i));
            }
        }
        return 0;
    }

    public static boolean b(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static String f(Context context) {
        String str = "";
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(b)) {
                    str = str + "#M";
                } else {
                    String str2;
                    if (runningAppProcessInfo.processName.startsWith("com.eg.android.AlipayGphone:")) {
                        str2 = str + "#" + runningAppProcessInfo.processName.replace("com.eg.android.AlipayGphone:", "");
                    } else {
                        str2 = str;
                    }
                    str = str2;
                }
            }
        } catch (Throwable th) {
            str = "";
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            return "N";
        }
        return str;
    }

    private static String i(Context context) {
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
            int i2 = packageInfo.applicationInfo.flags;
            if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (i2 != 0) {
                if (packageInfo.packageName.equals(b)) {
                    stringBuilder.append(packageInfo.packageName).append(packageInfo.versionCode).append("-");
                } else if (!(packageInfo.packageName.contains("theme") || packageInfo.packageName.startsWith("com.google.") || packageInfo.packageName.startsWith("com.android."))) {
                    stringBuilder.append(packageInfo.packageName).append("-");
                }
            }
        }
        return stringBuilder.toString();
    }

    @SuppressLint({"InlinedApi"})
    private static boolean a(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return (i & 1) == 0 && (i & 128) == 0;
    }

    public static boolean a(WebView webView, String str, Activity activity) {
        if (!TextUtils.isEmpty(str)) {
            String a;
            if (str.toLowerCase().startsWith(com.alipay.sdk.b.a.h.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.b.a.i.toLowerCase())) {
                try {
                    a a2 = a((Context) activity, b);
                    if (a2 != null) {
                        a = a(a2.a);
                        if (a == null || TextUtils.equals(a, com.alipay.sdk.b.a.g)) {
                            if (str.startsWith("intent://platformapi/startapp")) {
                                str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.b.a.h);
                            }
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        } else {
                            com.alipay.sdk.app.a.a.a(c.b, c.i, a);
                        }
                    }
                } catch (Throwable th) {
                }
            } else if (TextUtils.equals(str, com.alipay.sdk.b.a.k) || TextUtils.equals(str, com.alipay.sdk.b.a.l)) {
                h.a = h.a();
                activity.finish();
            } else if (str.startsWith(com.alipay.sdk.b.a.j)) {
                i a3;
                try {
                    a = str.substring(str.indexOf(com.alipay.sdk.b.a.j) + 24);
                    int parseInt = Integer.parseInt(a.substring(a.lastIndexOf(com.alipay.sdk.b.a.m) + 10));
                    if (parseInt == i.SUCCEEDED.h || parseInt == i.PAY_WAITTING.h) {
                        StringBuilder stringBuilder = new StringBuilder();
                        a = URLDecoder.decode(str);
                        a = a.substring(a.indexOf(com.alipay.sdk.b.a.j) + 24, a.lastIndexOf(com.alipay.sdk.b.a.m));
                        if (a.contains("&return_url=")) {
                            int indexOf = a.indexOf("&return_url=") + 12;
                            stringBuilder.append(a.split("&return_url=")[0]).append("&return_url=").append(a.substring(indexOf, a.indexOf(com.alipay.sdk.h.a.a, indexOf))).append(a.substring(a.indexOf(com.alipay.sdk.h.a.a, indexOf)));
                            a = stringBuilder.toString();
                        }
                        i a4 = i.a(parseInt);
                        h.a = h.a(a4.h, a4.i, a);
                        activity.runOnUiThread(new n(activity));
                    } else {
                        a3 = i.a(i.FAILED.h);
                        h.a = h.a(a3.h, a3.i, "");
                        activity.runOnUiThread(new n(activity));
                    }
                } catch (Exception e) {
                    a3 = i.a(i.PARAMS_ERROR.h);
                    h.a = h.a(a3.h, a3.i, "");
                }
            } else {
                webView.loadUrl(str);
            }
        }
        return true;
    }
}
