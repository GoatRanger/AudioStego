package com.flurry.sdk;

import android.content.Context;
import com.flurry.sdk.jr.a;
import java.lang.Thread.UncaughtExceptionHandler;

public class ke implements iq, a, UncaughtExceptionHandler {
    private static final String a = ke.class.getSimpleName();
    private boolean b;

    public void a(Context context) {
        jr a = jq.a();
        this.b = ((Boolean) a.a("CaptureUncaughtExceptions")).booleanValue();
        a.a("CaptureUncaughtExceptions", (a) this);
        in.a(4, a, "initSettings, CrashReportingEnabled = " + this.b);
        kf.a().a(this);
    }

    public void b() {
        kf.b();
        jq.a().b("CaptureUncaughtExceptions", (a) this);
    }

    public void a(String str, Object obj) {
        if (str.equals("CaptureUncaughtExceptions")) {
            this.b = ((Boolean) obj).booleanValue();
            in.a(4, a, "onSettingUpdate, CrashReportingEnabled = " + this.b);
            return;
        }
        in.a(6, a, "onSettingUpdate internal error!");
    }

    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        if (this.b) {
            String str = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                if (th.getMessage() != null) {
                    stringBuilder.append(" (" + th.getMessage() + ")\n");
                }
                str = stringBuilder.toString();
            } else if (th.getMessage() != null) {
                str = th.getMessage();
            }
            gg.a().a("uncaught", str, th);
        }
        jn.a().g();
        hr.a().d();
    }
}
