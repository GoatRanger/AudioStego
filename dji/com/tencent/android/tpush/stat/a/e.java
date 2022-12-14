package com.tencent.android.tpush.stat.a;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.data.a;
import com.tencent.android.tpush.service.a.b;
import com.tencent.android.tpush.service.a.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.d.d;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;

public class e {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static Random d = null;
    private static Map e = new HashMap(10);
    private static DisplayMetrics f = null;
    private static String g = null;
    private static String h = "";
    private static f i = null;
    private static String j = null;
    private static String k = null;
    private static String l = null;
    private static long m = -1;
    private static long n = -1;
    private static int o = 0;

    private static synchronized Random f() {
        Random random;
        synchronized (e.class) {
            if (d == null) {
                d = new Random();
            }
            random = d;
        }
        return random;
    }

    public static String a(Context context, long j) {
        try {
            if (e.containsKey(Long.valueOf(j))) {
                return (String) e.get(Long.valueOf(j));
            }
            if (context != null) {
                List<ResolveInfo> a = com.tencent.android.tpush.service.d.e.a(context);
                if (a != null) {
                    for (ResolveInfo resolveInfo : a) {
                        String str = resolveInfo.activityInfo.packageName;
                        if (str != null) {
                            a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                            if (registerInfoByPkgName != null) {
                                c a2 = b.a(context, str);
                                if (registerInfoByPkgName.a == j) {
                                    str = a2.a + "";
                                    e.put(Long.valueOf(registerInfoByPkgName.a), str);
                                    return str;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
            return "0";
        } catch (Throwable th) {
        }
    }

    public static int a() {
        return f().nextInt(Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
    }

    public static HttpHost a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            String defaultHost = Proxy.getDefaultHost();
            if (defaultHost != null && defaultHost.trim().length() > 0) {
                return new HttpHost(defaultHost, Proxy.getDefaultPort());
            }
            return null;
        } catch (Throwable th) {
            i.b(th);
        }
    }

    public static DisplayMetrics b(Context context) {
        if (f == null) {
            f = new DisplayMetrics();
            ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(f);
        }
        return f;
    }

    public static String c(Context context) {
        if (g != null) {
            return g;
        }
        try {
            if (d(context)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    g = telephonyManager.getSimOperator();
                }
            }
        } catch (Throwable th) {
            i.b(th);
        }
        return g;
    }

    public static String b(Context context, long j) {
        return d.a(context).a(j);
    }

    public static boolean d(Context context) {
        if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public static String e(Context context) {
        String str = "";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                String typeName = activeNetworkInfo.getTypeName();
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (typeName != null) {
                    if (typeName.equalsIgnoreCase("WIFI")) {
                        return "WIFI";
                    }
                    if (typeName.equalsIgnoreCase("MOBILE")) {
                        if (extraInfo == null || extraInfo.trim().length() <= 0) {
                            return "MOBILE";
                        }
                        return extraInfo;
                    } else if (extraInfo == null || extraInfo.trim().length() <= 0) {
                        return typeName;
                    } else {
                        return extraInfo;
                    }
                }
            }
        } catch (Throwable th) {
            i.b(th);
        }
        return str;
    }

    public static Integer f(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static synchronized f b() {
        f fVar;
        synchronized (e.class) {
            if (i == null) {
                i = new f("XgStat");
                i.a(false);
            }
            fVar = i;
        }
        return fVar;
    }

    public static long c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + 86400000;
        } catch (Throwable th) {
            i.b(th);
            return System.currentTimeMillis() + 86400000;
        }
    }

    public static Long a(String str, String str2, int i, int i2, Long l) {
        if (str == null || str2 == null) {
            return l;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = "\\" + str2;
        }
        String[] split = str.split(str2);
        if (split.length != i2) {
            return l;
        }
        try {
            Long valueOf = Long.valueOf(0);
            int i3 = 0;
            while (i3 < split.length) {
                Long valueOf2 = Long.valueOf(((long) i) * (valueOf.longValue() + Long.valueOf(split[i3]).longValue()));
                i3++;
                valueOf = valueOf2;
            }
            return valueOf;
        } catch (NumberFormatException e) {
            return l;
        }
    }

    public static long a(String str) {
        return a(str, ".", 100, 3, Long.valueOf(0)).longValue();
    }

    public static boolean b(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static String g(Context context) {
        if (b(j)) {
            return j;
        }
        try {
            String externalStorageState = Environment.getExternalStorageState();
            if (externalStorageState != null && externalStorageState.equals("mounted")) {
                externalStorageState = Environment.getExternalStorageDirectory().getPath();
                if (externalStorageState != null) {
                    StatFs statFs = new StatFs(externalStorageState);
                    long blockSize = (((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000;
                    j = String.valueOf(blockSize) + dji.pilot.usercenter.protocol.d.t + String.valueOf((((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000);
                    return j;
                }
            }
        } catch (Throwable th) {
            i.b(th);
        }
        return null;
    }

    public static String h(Context context) {
        try {
            if (k != null) {
                return k;
            }
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    k = runningAppProcessInfo.processName;
                    break;
                }
            }
            return k;
        } catch (Throwable th) {
        }
    }

    public static String a(Context context, String str) {
        if (!com.tencent.android.tpush.stat.c.e()) {
            return str;
        }
        if (k == null) {
            k = h(context);
        }
        if (k != null) {
            return str + "_" + k;
        }
        return str;
    }

    public static String d() {
        if (b(l)) {
            return l;
        }
        long e = e() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        l = String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + dji.pilot.usercenter.protocol.d.t + String.valueOf(e);
        return l;
    }

    public static long e() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static String i(Context context) {
        try {
            return String.valueOf(m(context) / 1000000) + dji.pilot.usercenter.protocol.d.t + String.valueOf(g() / 1000000);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static long m(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    private static long g() {
        if (m > 0) {
            return m;
        }
        long j = 1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                j = (long) (Integer.valueOf(readLine.split("\\s+")[1]).intValue() * 1024);
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
        m = j;
        return m;
    }

    public static boolean j(Context context) {
        if (n < 0) {
            n = g.a(context, "mta.qq.com.checktime", 0);
        }
        return Math.abs(System.currentTimeMillis() - n) > 86400000;
    }

    public static void k(Context context) {
        n = System.currentTimeMillis();
        g.b(context, "mta.qq.com.checktime", n);
    }

    public static int a(Context context, boolean z) {
        if (z) {
            o = l(context);
        }
        return o;
    }

    public static int l(Context context) {
        return g.a(context, "mta.qq.com.difftime", 0);
    }

    public static void a(Context context, int i) {
        o = i;
        g.b(context, "mta.qq.com.difftime", i);
    }
}
