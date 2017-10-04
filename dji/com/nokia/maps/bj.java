package com.nokia.maps;

import android.os.Process;
import android.util.Log;
import dji.thirdparty.g.b.b.a.c;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;

public class bj {
    private static bk a = bk.a;
    private static OutputStreamWriter b = null;
    private static String c = null;

    private static void a(int i, String str, String str2, Object... objArr) {
        if (a != bk.a) {
            if (a != bk.b || MapsEngine.H() || ((i != 3 && i != 2) || c != null)) {
                if (c == null || c.compareTo(str) == 0) {
                    Object obj = objArr.length == 0 ? 1 : null;
                    StringBuilder stringBuilder = new StringBuilder();
                    StackTraceElement[] stackTrace = new Exception().getStackTrace();
                    if (stackTrace.length >= 2) {
                        stringBuilder.append(stackTrace[2].getMethodName());
                        stringBuilder.append(": ");
                    }
                    if (obj == null) {
                        str2 = String.format(str2, objArr);
                    }
                    stringBuilder.append(str2);
                    String replaceAll = stringBuilder.toString().replaceAll("(\\r|\\n)", "");
                    switch (i) {
                        case 2:
                            if (a == bk.c) {
                                a(c.il_, str, replaceAll);
                                return;
                            } else {
                                Log.v(str, replaceAll);
                                return;
                            }
                        case 4:
                            if (a == bk.c) {
                                a("I", str, replaceAll);
                                return;
                            } else {
                                Log.i(str, replaceAll);
                                return;
                            }
                        case 5:
                            if (a == bk.c) {
                                a("W", str, replaceAll);
                                return;
                            } else {
                                Log.w(str, replaceAll);
                                return;
                            }
                        case 6:
                            if (a == bk.c) {
                                a("E", str, replaceAll);
                                return;
                            } else {
                                Log.e(str, replaceAll);
                                return;
                            }
                        case 7:
                            if (a == bk.c) {
                                a("WTF", str, replaceAll);
                                return;
                            } else {
                                Log.wtf(str, replaceAll);
                                return;
                            }
                        default:
                            if (a == bk.c) {
                                a("D", str, replaceAll);
                                return;
                            } else {
                                Log.d(str, replaceAll);
                                return;
                            }
                    }
                }
            }
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        a(2, str, str2, objArr);
    }

    public static void b(String str, String str2, Object... objArr) {
        a(5, str, str2, objArr);
    }

    public static void c(String str, String str2, Object... objArr) {
        a(6, str, str2, objArr);
    }

    public static void d(String str, String str2, Object... objArr) {
        a(4, str, str2, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        a(3, str, str2, objArr);
    }

    public static void f(String str, String str2, Object... objArr) {
        a(7, str, str2, objArr);
    }

    public static String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static bk a() {
        return a;
    }

    private static void a(String str, String str2, String str3) {
        if (b != null && a == bk.c) {
            try {
                b.write(String.format("[%s] %s/%s (%d):%s%n", new Object[]{DateFormat.getDateTimeInstance(3, 3).format(new Date()), str.toUpperCase(), str2, Integer.valueOf(Process.myPid()), str3}));
                b.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
