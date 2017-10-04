package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.z;
import dji.pilot.usercenter.protocol.d;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;

public class b {
    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static int c() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String a(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (str == null) {
                return "null";
            }
            return str.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            String str2 = str;
            Throwable th3 = th2;
            if (z.a(th3)) {
                return str2;
            }
            th3.printStackTrace();
            return str2;
        }
    }

    public static String b(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (str == null) {
                return "null";
            }
            return str.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            String str2 = str;
            Throwable th3 = th2;
            if (z.a(th3)) {
                return str2;
            }
            th3.printStackTrace();
            return str2;
        }
    }

    public static String c(Context context) {
        Throwable th;
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                return string.toLowerCase();
            }
            try {
                return "null";
            } catch (Throwable th2) {
                Throwable th3 = th2;
                str = string;
                th = th3;
                if (!z.a(th)) {
                    return str;
                }
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            if (!z.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String d(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            str = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (str == null) {
                return "null";
            }
            return str.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            String str2 = str;
            Throwable th3 = th2;
            if (z.a(th3)) {
                return str2;
            }
            th3.printStackTrace();
            return str2;
        }
    }

    public static boolean d() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static String e() {
        try {
            return "" + System.getProperty("os.arch");
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static long f() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (z.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long g() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (z.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long h() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        BufferedReader bufferedReader2 = null;
        FileReader fileReader2;
        try {
            fileReader2 = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader2, 2048);
                try {
                    long parseLong = Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1000;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e) {
                            if (!z.a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (fileReader2 == null) {
                        return parseLong;
                    }
                    try {
                        fileReader2.close();
                        return parseLong;
                    } catch (Throwable e2) {
                        if (z.a(e2)) {
                            return parseLong;
                        }
                        e2.printStackTrace();
                        return parseLong;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader2 = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            throw th;
        }
    }

    public static long i() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader2;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
            try {
                bufferedReader.readLine();
                long parseLong = ((Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1000) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1000)) + (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1000);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e) {
                        if (!z.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return parseLong;
                }
                try {
                    fileReader.close();
                    return parseLong;
                } catch (Throwable e2) {
                    if (z.a(e2)) {
                        return parseLong;
                    }
                    e2.printStackTrace();
                    return parseLong;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static long j() {
        if (!d()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long k() {
        if (!d()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String l() {
        String str = "fail";
        try {
            str = Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String m() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (z.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String e(Context context) {
        String str = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            String str2;
            if (activeNetworkInfo.getType() == 1) {
                str2 = "WIFI";
            } else {
                if (activeNetworkInfo.getType() == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        int networkType = telephonyManager.getNetworkType();
                        switch (networkType) {
                            case 1:
                                str2 = "GPRS";
                                break;
                            case 2:
                                str2 = "EDGE";
                                break;
                            case 3:
                                str2 = "UMTS";
                                break;
                            case 4:
                                str2 = "CDMA";
                                break;
                            case 5:
                                str2 = "EVDO_0";
                                break;
                            case 6:
                                str2 = "EVDO_A";
                                break;
                            case 7:
                                str2 = "1xRTT";
                                break;
                            case 8:
                                str2 = "HSDPA";
                                break;
                            case 9:
                                str2 = "HSUPA";
                                break;
                            case 10:
                                str2 = "HSPA";
                                break;
                            case 11:
                                str2 = "iDen";
                                break;
                            case 12:
                                str2 = "EVDO_B";
                                break;
                            case 13:
                                str2 = "LTE";
                                break;
                            case 14:
                                str2 = "eHRPD";
                                break;
                            case 15:
                                str2 = "HSPA+";
                                break;
                            default:
                                str2 = "MOBILE(" + networkType + ")";
                                break;
                        }
                    }
                }
                str2 = str;
            }
            return str2;
        } catch (Throwable e) {
            if (z.a(e)) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    public static String f(Context context) {
        String a = ag.a(context, "ro.miui.ui.version.name");
        if (!ag.b(a) && !a.equals("fail")) {
            return "XiaoMi/MIUI/" + a;
        }
        a = ag.a(context, "ro.build.version.emui");
        if (!ag.b(a) && !a.equals("fail")) {
            return "HuaWei/EMOTION/" + a;
        }
        a = ag.a(context, "ro.lenovo.series");
        if (ag.b(a) || a.equals("fail")) {
            a = ag.a(context, "ro.build.nubia.rom.name");
            if (!ag.b(a) && !a.equals("fail")) {
                return "Zte/NUBIA/" + a + "_" + ag.a(context, "ro.build.nubia.rom.code");
            }
            a = ag.a(context, "ro.meizu.product.model");
            if (!ag.b(a) && !a.equals("fail")) {
                return "Meizu/FLYME/" + ag.a(context, "ro.build.display.id");
            }
            a = ag.a(context, "ro.build.version.opporom");
            if (!ag.b(a) && !a.equals("fail")) {
                return "Oppo/COLOROS/" + a;
            }
            a = ag.a(context, "ro.vivo.os.build.display.id");
            if (!ag.b(a) && !a.equals("fail")) {
                return "vivo/FUNTOUCH/" + a;
            }
            a = ag.a(context, "ro.aa.romver");
            if (!ag.b(a) && !a.equals("fail")) {
                return "htc/" + a + d.t + ag.a(context, "ro.build.description");
            }
            a = ag.a(context, "ro.lewa.version");
            if (!ag.b(a) && !a.equals("fail")) {
                return "tcl/" + a + d.t + ag.a(context, "ro.build.display.id");
            }
            a = ag.a(context, "ro.gn.gnromvernumber");
            if (!ag.b(a) && !a.equals("fail")) {
                return "amigo/" + a + d.t + ag.a(context, "ro.build.display.id");
            }
            a = ag.a(context, "ro.build.tyd.kbstyle_version");
            if (ag.b(a) || a.equals("fail")) {
                return ag.a(context, "ro.build.fingerprint") + d.t + ag.a(context, "ro.build.rom.id");
            }
            return "dido/" + a;
        }
        return "Lenovo/VIBE/" + ag.a(context, "ro.build.version.incremental");
    }

    public static String g(Context context) {
        return ag.a(context, "ro.board.platform");
    }
}
