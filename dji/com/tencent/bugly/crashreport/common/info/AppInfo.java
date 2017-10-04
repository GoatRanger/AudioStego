package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import com.tencent.android.tpush.common.Constants;
import com.tencent.bugly.proguard.z;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppInfo {
    public static final String[] a = ",msdk,imsdk,tbscore,lejiagu,opengame".split(",");
    public static final String[] b = "0,1,2,3,4,5".split(",");
    private static String c = "BUGLY_DISABLE";
    private static ActivityManager d;

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static synchronized String b(Context context) {
        String str;
        synchronized (AppInfo.class) {
            try {
                str = context.getPackageManager().getPackageInfo(a(context), 0).versionName;
                if (str == null) {
                    throw new Exception("Can not get version name in AndroidManifest.xml.");
                }
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
                str = "fail";
            }
        }
        return str;
    }

    public static boolean a(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (Object equals : strArr) {
                if (str.equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (z.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean c(Context context) {
        boolean a;
        synchronized (AppInfo.class) {
            a = a(context, "android.permission.READ_LOGS");
        }
        return a;
    }

    public static Boolean d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
                return Boolean.valueOf(false);
            }
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return Boolean.valueOf(runningAppProcessInfo.importance == 100);
                }
            }
            return Boolean.valueOf(false);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String a(Context context, int i) {
        FileReader fileReader;
        String substring;
        Throwable th;
        int i2 = 0;
        try {
            fileReader = new FileReader("/proc/" + i + "/cmdline");
            try {
                char[] cArr = new char[512];
                fileReader.read(cArr);
                while (i2 < cArr.length && cArr[i2] != '\u0000') {
                    i2++;
                }
                substring = new String(cArr).substring(0, i2);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    if (!z.a(th)) {
                        th.printStackTrace();
                    }
                    substring = String.valueOf(i);
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable th4) {
                        }
                    }
                    return substring;
                } catch (Throwable th5) {
                    th = th5;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
        return substring;
    }

    public static String e(Context context) {
        try {
            return a(context, Process.myPid());
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static List<String> f(Context context) {
        List<String> list = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                String string = applicationInfo.metaData.getString(c);
                if (!(string == null || string.length() == 0)) {
                    String[] split = string.split(",");
                    for (int i = 0; i < split.length; i++) {
                        split[i] = split[i].trim();
                    }
                    list = Arrays.asList(split);
                }
            }
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
        }
        return list;
    }

    public static boolean g(Context context) {
        if (context == null) {
            return false;
        }
        if (d == null) {
            d = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        }
        try {
            MemoryInfo memoryInfo = new MemoryInfo();
            d.getMemoryInfo(memoryInfo);
            return memoryInfo.lowMemory;
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public static List<String> h(Context context) {
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < b.length - 1; i++) {
            for (int i2 = i + 1; i2 < b.length; i2++) {
                if (Integer.valueOf(b[i]).intValue() > Integer.valueOf(b[i2]).intValue()) {
                    String str = a[i];
                    a[i] = a[i2];
                    a[i2] = str;
                    str = b[i];
                    b[i] = b[i2];
                    b[i2] = str;
                }
            }
        }
        for (String str2 : a) {
            String str3;
            if (str2.equals("")) {
                str3 = "com.tencent.bugly." + ".crashreport.CrashReport".substring(1);
            } else {
                str3 = "com.tencent.bugly." + str2 + ".crashreport.CrashReport";
            }
            try {
                Class.forName(str3);
                if (str2.equals("")) {
                    arrayList.add("bugly");
                } else {
                    z.a("[init] find bugly channel: %s", str2);
                    arrayList.add(str2);
                }
            } catch (Throwable th) {
            }
        }
        return arrayList;
    }
}
