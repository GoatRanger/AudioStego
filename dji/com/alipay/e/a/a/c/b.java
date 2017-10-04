package com.alipay.e.a.a.c;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class b {
    private static b a = new b();

    private class a implements Comparator<Size> {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public int a(Size size, Size size2) {
            return size.width == size2.width ? 0 : size.width > size2.width ? 1 : -1;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Size) obj, (Size) obj2);
        }
    }

    private b() {
    }

    private String a(Context context, int i) {
        Camera open;
        Throwable th;
        String str = "%2$d*%3$d";
        String str2 = "";
        float t = t(context.getApplicationContext());
        Camera camera = null;
        try {
            open = Camera.open(i);
            try {
                List<Size> supportedPreviewSizes = open.getParameters().getSupportedPreviewSizes();
                Collections.sort(supportedPreviewSizes, new a());
                int i2 = 0;
                for (Size size : supportedPreviewSizes) {
                    if (size.width >= 600) {
                        if ((((double) Math.abs((((float) size.width) / ((float) size.height)) - t)) <= 0.03d ? 1 : null) != null) {
                            break;
                        }
                    }
                    i2++;
                }
                String format = ((Size) supportedPreviewSizes.get(i2 == supportedPreviewSizes.size() ? supportedPreviewSizes.size() - 1 : i2)) != null ? String.format(Locale.ENGLISH, str, new Object[]{Integer.valueOf(i), Integer.valueOf(((Size) supportedPreviewSizes.get(i2 == supportedPreviewSizes.size() ? supportedPreviewSizes.size() - 1 : i2)).width), Integer.valueOf(((Size) supportedPreviewSizes.get(i2 == supportedPreviewSizes.size() ? supportedPreviewSizes.size() - 1 : i2)).height)}) : str2;
                if (open == null) {
                    return format;
                }
                open.release();
                return format;
            } catch (RuntimeException e) {
                camera = open;
                if (camera != null) {
                    camera.release();
                    return str2;
                }
                return str2;
            } catch (Exception e2) {
                if (open != null) {
                    open.release();
                    return str2;
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (open != null) {
                    open.release();
                }
                throw th;
            }
        } catch (RuntimeException e3) {
            if (camera != null) {
                camera.release();
                return str2;
            }
            return str2;
        } catch (Exception e4) {
            open = null;
            if (open != null) {
                open.release();
                return str2;
            }
            return str2;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.release();
            }
            throw th;
        }
    }

    public static b getInstance() {
        return a;
    }

    private static String n() {
        FileReader fileReader;
        Throwable th;
        String str = null;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
                try {
                    String readLine = bufferedReader.readLine();
                    if (com.alipay.e.a.a.b.a.a(readLine)) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e2) {
                        }
                    } else {
                        str = readLine.trim();
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (IOException e5) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e6) {
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e7) {
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        bufferedReader.close();
                    } catch (IOException e8) {
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e9) {
                    }
                    throw th;
                }
            } catch (IOException e10) {
                bufferedReader = null;
                bufferedReader.close();
                fileReader.close();
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = null;
                th = th4;
                bufferedReader.close();
                fileReader.close();
                throw th;
            }
        } catch (IOException e11) {
            bufferedReader = null;
            fileReader = null;
            bufferedReader.close();
            fileReader.close();
            return str;
        } catch (Throwable th32) {
            fileReader = null;
            th = th32;
            bufferedReader = null;
            bufferedReader.close();
            fileReader.close();
            throw th;
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String o() {
        /*
        r6 = 1;
        r0 = 0;
        r1 = "/proc/cpuinfo";
        r2 = new java.io.FileReader;	 Catch:{ IOException -> 0x0040, all -> 0x0050 }
        r2.<init>(r1);	 Catch:{ IOException -> 0x0040, all -> 0x0050 }
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0071, all -> 0x006a }
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r2, r3);	 Catch:{ IOException -> 0x0071, all -> 0x006a }
    L_0x0010:
        r3 = r1.readLine();	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        if (r3 == 0) goto L_0x0039;
    L_0x0016:
        r4 = com.alipay.e.a.a.b.a.a(r3);	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        if (r4 != 0) goto L_0x0010;
    L_0x001c:
        r4 = ":";
        r3 = r3.split(r4);	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        if (r3 == 0) goto L_0x0010;
    L_0x0024:
        r4 = r3.length;	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        if (r4 <= r6) goto L_0x0010;
    L_0x0027:
        r4 = 0;
        r4 = r3[r4];	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        r5 = "BogoMIPS";
        r4 = r4.contains(r5);	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        if (r4 == 0) goto L_0x0010;
    L_0x0032:
        r4 = 1;
        r3 = r3[r4];	 Catch:{ IOException -> 0x0074, all -> 0x006f }
        r0 = r3.trim();	 Catch:{ IOException -> 0x0074, all -> 0x006f }
    L_0x0039:
        r2.close();	 Catch:{ IOException -> 0x0060 }
    L_0x003c:
        r1.close();	 Catch:{ IOException -> 0x0062 }
    L_0x003f:
        return r0;
    L_0x0040:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
    L_0x0043:
        if (r2 == 0) goto L_0x0048;
    L_0x0045:
        r2.close();	 Catch:{ IOException -> 0x0064 }
    L_0x0048:
        if (r1 == 0) goto L_0x003f;
    L_0x004a:
        r1.close();	 Catch:{ IOException -> 0x004e }
        goto L_0x003f;
    L_0x004e:
        r1 = move-exception;
        goto L_0x003f;
    L_0x0050:
        r1 = move-exception;
        r2 = r0;
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x0055:
        if (r2 == 0) goto L_0x005a;
    L_0x0057:
        r2.close();	 Catch:{ IOException -> 0x0066 }
    L_0x005a:
        if (r1 == 0) goto L_0x005f;
    L_0x005c:
        r1.close();	 Catch:{ IOException -> 0x0068 }
    L_0x005f:
        throw r0;
    L_0x0060:
        r2 = move-exception;
        goto L_0x003c;
    L_0x0062:
        r1 = move-exception;
        goto L_0x003f;
    L_0x0064:
        r2 = move-exception;
        goto L_0x0048;
    L_0x0066:
        r2 = move-exception;
        goto L_0x005a;
    L_0x0068:
        r1 = move-exception;
        goto L_0x005f;
    L_0x006a:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0055;
    L_0x006f:
        r0 = move-exception;
        goto L_0x0055;
    L_0x0071:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0043;
    L_0x0074:
        r3 = move-exception;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.e.a.a.c.b.o():java.lang.String");
    }

    private static float t(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Point point = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
        return ((float) point.y) / ((float) point.x);
    }

    public String a() {
        InputStreamReader inputStreamReader;
        Throwable th;
        InputStreamReader inputStreamReader2 = null;
        String str = "0000000000000000";
        LineNumberReader lineNumberReader;
        try {
            inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo | grep Serial").getInputStream());
            try {
                lineNumberReader = new LineNumberReader(inputStreamReader);
                int i = 1;
                while (i < 100) {
                    try {
                        String readLine = lineNumberReader.readLine();
                        if (readLine != null) {
                            if (readLine.indexOf("Serial") >= 0) {
                                str = readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                                break;
                            }
                            i++;
                        }
                    } catch (Exception e) {
                        inputStreamReader2 = inputStreamReader;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                try {
                    lineNumberReader.close();
                } catch (IOException e2) {
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e3) {
                }
            } catch (Exception e4) {
                lineNumberReader = null;
                inputStreamReader2 = inputStreamReader;
                if (lineNumberReader != null) {
                    try {
                        lineNumberReader.close();
                    } catch (IOException e5) {
                    }
                }
                if (inputStreamReader2 != null) {
                    try {
                        inputStreamReader2.close();
                    } catch (IOException e6) {
                    }
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
                lineNumberReader = null;
                if (lineNumberReader != null) {
                    try {
                        lineNumberReader.close();
                    } catch (IOException e7) {
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
        } catch (Exception e9) {
            lineNumberReader = null;
            if (lineNumberReader != null) {
                lineNumberReader.close();
            }
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            return str;
        } catch (Throwable th4) {
            th = th4;
            lineNumberReader = null;
            inputStreamReader = null;
            if (lineNumberReader != null) {
                lineNumberReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        return str;
    }

    public String a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getDeviceId() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String b() {
        InputStreamReader inputStreamReader;
        Throwable th;
        InputStreamReader inputStreamReader2 = null;
        String str = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        LineNumberReader lineNumberReader;
        try {
            inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo | grep Hardware").getInputStream());
            try {
                lineNumberReader = new LineNumberReader(inputStreamReader);
                int i = 1;
                while (i < 100) {
                    try {
                        String readLine = lineNumberReader.readLine();
                        if (readLine != null) {
                            if (readLine.indexOf("Hardware") >= 0) {
                                str = readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                                break;
                            }
                            i++;
                        }
                    } catch (Exception e) {
                        inputStreamReader2 = inputStreamReader;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                try {
                    lineNumberReader.close();
                } catch (IOException e2) {
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e3) {
                }
            } catch (Exception e4) {
                lineNumberReader = null;
                inputStreamReader2 = inputStreamReader;
                if (lineNumberReader != null) {
                    try {
                        lineNumberReader.close();
                    } catch (IOException e5) {
                    }
                }
                if (inputStreamReader2 != null) {
                    try {
                        inputStreamReader2.close();
                    } catch (IOException e6) {
                    }
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
                lineNumberReader = null;
                if (lineNumberReader != null) {
                    try {
                        lineNumberReader.close();
                    } catch (IOException e7) {
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
        } catch (Exception e9) {
            lineNumberReader = null;
            if (lineNumberReader != null) {
                lineNumberReader.close();
            }
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            return str;
        } catch (Throwable th4) {
            th = th4;
            lineNumberReader = null;
            inputStreamReader = null;
            if (lineNumberReader != null) {
                lineNumberReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        return str;
    }

    public String b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getSubscriberId() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String c() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Exception e) {
            return "1";
        }
    }

    public String c(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getSimOperator() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String d() {
        String n = n();
        return !com.alipay.e.a.a.b.a.a(n) ? n : o();
    }

    public String d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getSimOperatorName() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String e() {
        FileReader fileReader;
        Throwable th;
        String str = null;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    String[] split = bufferedReader.readLine().split(":\\s+", 2);
                    if (split == null || split.length <= 1) {
                        try {
                            fileReader.close();
                        } catch (IOException e) {
                        }
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                        }
                        return str;
                    }
                    str = split[1];
                    try {
                        fileReader.close();
                    } catch (IOException e3) {
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                    }
                    return str;
                } catch (IOException e5) {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e6) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e8) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e9) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e10) {
                bufferedReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = null;
                th = th4;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (IOException e11) {
            bufferedReader = null;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str;
        } catch (Throwable th32) {
            fileReader = null;
            th = th32;
            bufferedReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public String e(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getLine1Number() : str;
        } catch (Exception e) {
            return str;
        }
    }

    public String f() {
        Throwable th;
        BufferedReader bufferedReader = null;
        long j = 0;
        FileReader fileReader;
        BufferedReader bufferedReader2;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader, 8192);
            } catch (IOException e) {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e2) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                    }
                }
                return String.valueOf(j);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = null;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e4) {
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    j = (long) Integer.parseInt(readLine.split("\\s+")[1]);
                }
                try {
                    fileReader.close();
                } catch (IOException e6) {
                }
                try {
                    bufferedReader2.close();
                } catch (IOException e7) {
                }
            } catch (IOException e8) {
                bufferedReader = bufferedReader2;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return String.valueOf(j);
            } catch (Throwable th3) {
                th = th3;
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th;
            }
        } catch (IOException e9) {
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return String.valueOf(j);
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
            bufferedReader2 = null;
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
        return String.valueOf(j);
    }

    public String f(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getVoiceMailNumber() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String g() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
        }
        return String.valueOf(j);
    }

    public String g(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getVoiceMailAlphaTag() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String h() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
        } catch (Exception e) {
        }
        return String.valueOf(j);
    }

    public String h(Context context) {
        if (context == null) {
            return null;
        }
        try {
            String e;
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List<Sensor> sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null && sensorList.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        stringBuilder.append(sensor.getName());
                        stringBuilder.append(sensor.getVersion());
                        stringBuilder.append(sensor.getVendor());
                    }
                    e = com.alipay.e.a.a.b.a.e(stringBuilder.toString());
                    return e;
                }
            }
            e = null;
            return e;
        } catch (Exception e2) {
            return null;
        }
    }

    public String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean i() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public String j() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            return (defaultAdapter == null || defaultAdapter.isEnabled()) ? defaultAdapter.getAddress() : "";
        } catch (Exception e) {
            return null;
        }
    }

    public String j(Context context) {
        try {
            return context.getResources().getDisplayMetrics().densityDpi;
        } catch (Exception e) {
            return null;
        }
    }

    public String k() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Object newInstance = cls.newInstance();
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(newInstance, new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception e) {
            return null;
        }
    }

    public String k(Context context) {
        try {
            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e) {
            return null;
        }
    }

    public String l() {
        Throwable th;
        String str = "";
        String str2 = "";
        try {
            InputStream fileInputStream = new FileInputStream("/proc/version");
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream), 8192);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            str2 = str2 + readLine;
                        } else {
                            try {
                                break;
                            } catch (IOException e) {
                            }
                        }
                    } catch (IOException e2) {
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                bufferedReader.close();
                fileInputStream.close();
            } catch (IOException e3) {
                bufferedReader = null;
                try {
                    bufferedReader.close();
                    fileInputStream.close();
                } catch (IOException e4) {
                }
                if (com.alipay.e.a.a.b.a.b(str2)) {
                    str2 = str2.substring(str2.indexOf("version ") + 8);
                    str = str2.substring(0, str2.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR));
                }
                return str;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                try {
                    bufferedReader.close();
                    fileInputStream.close();
                } catch (IOException e5) {
                }
                throw th;
            }
            try {
                if (com.alipay.e.a.a.b.a.b(str2)) {
                    str2 = str2.substring(str2.indexOf("version ") + 8);
                    str = str2.substring(0, str2.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR));
                }
            } catch (Exception e6) {
            }
        } catch (FileNotFoundException e7) {
        }
        return str;
    }

    public String l(Context context) {
        try {
            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            return null;
        }
    }

    public String m() {
        String str = "";
        try {
            return Build.SERIAL;
        } catch (Exception e) {
            return str;
        }
    }

    public String m(Context context) {
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            return null;
        }
    }

    public String n(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    public String o(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            return null;
        }
    }

    public String p(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return String.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String q(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                return wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public Map<String, Integer> r(Context context) {
        Map<String, Integer> hashMap = new HashMap();
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null && installedPackages.size() > 0) {
                for (PackageInfo packageInfo : installedPackages) {
                    hashMap.put(packageInfo.packageName, Integer.valueOf(packageInfo.applicationInfo.uid));
                }
            }
        } catch (Throwable th) {
        }
        return hashMap;
    }

    public String s(Context context) {
        t(context.getApplicationContext());
        int i = -1;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (Integer.parseInt(VERSION.SDK) > 8) {
                i = Camera.getNumberOfCameras();
            }
        } catch (Throwable th) {
        }
        for (int i2 = 0; i2 < i; i2++) {
            String format;
            if (i2 == 0) {
                format = String.format(Locale.ENGLISH, "%1$d:%2$s", new Object[]{Integer.valueOf(i2), a(context, i2)});
            } else {
                format = String.format(Locale.ENGLISH, "#%1$d:%2$s", new Object[]{Integer.valueOf(i2), a(context, i2)});
            }
            stringBuilder.append(format);
        }
        return stringBuilder.toString();
    }
}
