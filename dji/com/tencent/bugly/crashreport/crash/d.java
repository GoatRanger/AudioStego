package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.proguard.z;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class d {
    public static String a(Throwable th, int i) {
        if (th.getMessage() == null) {
            return "";
        }
        if (i < 0 || th.getMessage().length() <= i) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, i) + "\n[Message over limit size:" + i + ", has been cutted!]";
    }

    public static String b(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i2 = 0;
                while (i2 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (i <= 0 || stringBuilder.length() < i) {
                        stringBuilder.append(stackTraceElement.toString()).append("\n");
                        i2++;
                    } else {
                        stringBuilder.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return stringBuilder.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            z.e("gen stack error %s", th2.toString());
        }
        return stringBuilder.toString();
    }

    public static Throwable a(Throwable th) {
        if (th == null) {
            return null;
        }
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static String a(Context context, int i) {
        Process exec;
        Throwable th;
        Process process;
        String str = null;
        if (AppInfo.c(context)) {
            String[] strArr = new String[]{"logcat", "-d", "-v", "threadtime"};
            StringBuilder stringBuilder = new StringBuilder();
            try {
                exec = Runtime.getRuntime().exec(strArr);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine).append("\n");
                        if (i > 0 && stringBuilder.length() > i) {
                            stringBuilder.delete(0, stringBuilder.length() - i);
                        }
                    }
                    str = stringBuilder.toString();
                    if (exec != null) {
                        try {
                            exec.getOutputStream().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            exec.getInputStream().close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        try {
                            exec.getErrorStream().close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (exec != null) {
                        exec.getOutputStream().close();
                        exec.getInputStream().close();
                        exec.getErrorStream().close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                exec = null;
                th = th3;
                if (exec != null) {
                    exec.getOutputStream().close();
                    exec.getInputStream().close();
                    exec.getErrorStream().close();
                }
                throw th;
            }
        }
        z.d("no read_log permission!", new Object[0]);
        return str;
    }

    public static Map<String, String> a(int i, boolean z) {
        Map<String, String> hashMap = new HashMap(12);
        Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        long id = Thread.currentThread().getId();
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : allStackTraces.entrySet()) {
            if (!z || id != ((Thread) entry.getKey()).getId()) {
                stringBuilder.setLength(0);
                if (!(entry.getValue() == null || ((StackTraceElement[]) entry.getValue()).length == 0)) {
                    for (StackTraceElement stackTraceElement : (StackTraceElement[]) entry.getValue()) {
                        if (i > 0 && stringBuilder.length() >= i) {
                            stringBuilder.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                            break;
                        }
                        stringBuilder.append(stackTraceElement.toString()).append("\n");
                    }
                    hashMap.put(((Thread) entry.getKey()).getName() + "(" + ((Thread) entry.getKey()).getId() + ")", stringBuilder.toString());
                }
            }
        }
        return hashMap;
    }
}
