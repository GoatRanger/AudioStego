package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import com.tencent.bugly.crashreport.common.strategy.c;

public class p {
    private static p c = new p();
    public int a;
    public String b = "unknown";
    private boolean d;
    private boolean e;
    private long f;

    @TargetApi(14)
    class a implements ActivityLifecycleCallbacks {
        final /* synthetic */ p a;

        a(p pVar) {
            this.a = pVar;
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityResumed(Activity activity) {
            if (activity != null) {
                this.a.b = activity.getClass().getName();
            } else {
                this.a.b = "unknown";
            }
            if (SystemClock.elapsedRealtime() - this.a.f > 30000) {
                c a = c.a();
                if (a != null) {
                    p pVar = this.a;
                    pVar.a++;
                    z.a("[session] launch app 1 times (app in background over 30 seconds)", new Object[0]);
                    if (this.a.a % 10 == 0) {
                        a.a(true);
                    } else {
                        a.a(false);
                    }
                }
            }
        }

        public void onActivityPaused(Activity activity) {
            this.a.f = SystemClock.elapsedRealtime();
            if (activity != null) {
                this.a.b = "background";
                return;
            }
            this.a.b = "unknown";
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }
    }

    public static p a() {
        return c;
    }

    public void a(Context context) {
        if (!this.d) {
            this.d = true;
            b(context);
        }
    }

    public void a(long j) {
        c.a().a(j);
        z.a("[session] launch app 1 times (app new start)", new Object[0]);
        this.f = SystemClock.elapsedRealtime();
    }

    @TargetApi(14)
    private void b(Context context) {
        Application application = null;
        if (VERSION.SDK_INT < 14) {
            this.e = false;
            return;
        }
        if (context.getApplicationContext() instanceof Application) {
            application = (Application) context.getApplicationContext();
            this.e = true;
        }
        if (application != null) {
            try {
                application.registerActivityLifecycleCallbacks(new a(this));
            } catch (Exception e) {
                this.e = false;
            }
            if (this.e) {
                z.c("[session] registed by api", new Object[0]);
            }
        }
    }
}
