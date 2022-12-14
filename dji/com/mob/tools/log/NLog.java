package com.mob.tools.log;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;

public abstract class NLog {
    private static HashMap<String, NLog> loggers = new HashMap();
    private static LogPrinter printer = new LogPrinter();

    protected abstract String getSDKTag();

    static {
        MobUncaughtExceptionHandler.register();
    }

    public NLog() {
        loggers.put(getSDKTag(), this);
        if (loggers.size() == 1) {
            loggers.put("__FIRST__", this);
        }
    }

    public static void setContext(Context context) {
        if (context != null) {
            printer.setContext(context);
            NativeErrorHandler.prepare(context);
        }
    }

    public static void setCollector(String str, LogCollector logCollector) {
        printer.setCollector(str, logCollector);
    }

    protected static final NLog getInstanceForSDK(final String str, boolean z) {
        NLog nLog = (NLog) loggers.get(str);
        if (nLog == null) {
            nLog = (NLog) loggers.get("__FIRST__");
        }
        if (nLog == null && z) {
            return new NLog() {
                protected String getSDKTag() {
                    return str;
                }
            };
        }
        return nLog;
    }

    public final int v(Throwable th) {
        return printer.println(getSDKTag(), 2, 0, Log.getStackTraceString(th));
    }

    public final int v(Object obj, Object... objArr) {
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 2, 0, obj2);
    }

    public final int v(Throwable th, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 2, 0, stringBuilder.append(obj2).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public final int d(Throwable th) {
        return printer.println(getSDKTag(), 3, 0, Log.getStackTraceString(th));
    }

    public final int d(Object obj, Object... objArr) {
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 3, 0, obj2);
    }

    public final int d(Throwable th, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 3, 0, stringBuilder.append(obj2).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public final int i(Throwable th) {
        return printer.println(getSDKTag(), 4, 0, Log.getStackTraceString(th));
    }

    public final int i(Object obj, Object... objArr) {
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 4, 0, obj2);
    }

    public final int i(Throwable th, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 4, 0, stringBuilder.append(obj2).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public final int w(Throwable th) {
        return printer.println(getSDKTag(), 5, 0, Log.getStackTraceString(th));
    }

    public final int w(Object obj, Object... objArr) {
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 5, 0, obj2);
    }

    public final int w(Throwable th, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 5, 0, stringBuilder.append(obj2).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public final int e(Throwable th) {
        return printer.println(getSDKTag(), 6, 0, Log.getStackTraceString(th));
    }

    public final int e(Object obj, Object... objArr) {
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 6, 0, obj2);
    }

    public final int e(Throwable th, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return printer.println(getSDKTag(), 6, 0, stringBuilder.append(obj2).append('\n').append(Log.getStackTraceString(th)).toString());
    }

    public final int crash(Throwable th) {
        return printer.println(getSDKTag(), 6, 1, Log.getStackTraceString(th));
    }

    public final void nativeCrashLog(String str) {
        printer.nativeCrashLog(getSDKTag(), str);
    }
}
