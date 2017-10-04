package com.flurry.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.flurry.sdk.ic.a;

public class id {
    private static id a;
    private static final String b = id.class.getSimpleName();
    private Object c;

    public static synchronized id a() {
        id idVar;
        synchronized (id.class) {
            if (a == null) {
                a = new id();
            }
            idVar = a;
        }
        return idVar;
    }

    public static synchronized void b() {
        synchronized (id.class) {
            if (a != null) {
                a.f();
            }
            a = null;
        }
    }

    private id() {
        e();
    }

    public boolean c() {
        return this.c != null;
    }

    @TargetApi(14)
    private void e() {
        if (VERSION.SDK_INT >= 14 && this.c == null) {
            Context c = hz.a().c();
            if (c instanceof Application) {
                this.c = new ActivityLifecycleCallbacks(this) {
                    final /* synthetic */ id a;

                    {
                        this.a = r1;
                    }

                    public void onActivityCreated(Activity activity, Bundle bundle) {
                        in.a(3, id.b, "onActivityCreated for activity:" + activity);
                        a(activity, a.kCreated);
                    }

                    public void onActivityStarted(Activity activity) {
                        in.a(3, id.b, "onActivityStarted for activity:" + activity);
                        a(activity, a.kStarted);
                    }

                    public void onActivityResumed(Activity activity) {
                        in.a(3, id.b, "onActivityResumed for activity:" + activity);
                        a(activity, a.kResumed);
                    }

                    public void onActivityPaused(Activity activity) {
                        in.a(3, id.b, "onActivityPaused for activity:" + activity);
                        a(activity, a.kPaused);
                    }

                    public void onActivityStopped(Activity activity) {
                        in.a(3, id.b, "onActivityStopped for activity:" + activity);
                        a(activity, a.kStopped);
                    }

                    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                        in.a(3, id.b, "onActivitySaveInstanceState for activity:" + activity);
                        a(activity, a.kSaveState);
                    }

                    public void onActivityDestroyed(Activity activity) {
                        in.a(3, id.b, "onActivityDestroyed for activity:" + activity);
                        a(activity, a.kDestroyed);
                    }

                    protected void a(Activity activity, a aVar) {
                        ic icVar = new ic();
                        icVar.a = activity;
                        icVar.b = aVar;
                        icVar.b();
                    }
                };
                ((Application) c).registerActivityLifecycleCallbacks((ActivityLifecycleCallbacks) this.c);
            }
        }
    }

    @TargetApi(14)
    private void f() {
        if (VERSION.SDK_INT >= 14 && this.c != null) {
            Context c = hz.a().c();
            if (c instanceof Application) {
                ((Application) c).unregisterActivityLifecycleCallbacks((ActivityLifecycleCallbacks) this.c);
                this.c = null;
            }
        }
    }
}
