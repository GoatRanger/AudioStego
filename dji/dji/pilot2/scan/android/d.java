package dji.pilot2.scan.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

public final class d {
    private static final String a = d.class.getSimpleName();
    private static final long b = 300000;
    private final Activity c;
    private final BroadcastReceiver d = new b();
    private boolean e = false;
    private AsyncTask<Object, Object, Object> f;

    private final class a extends AsyncTask<Object, Object, Object> {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(300000);
                Log.i(d.a, "Finishing activity due to inactivity");
                this.a.c.finish();
            } catch (InterruptedException e) {
            }
            return null;
        }
    }

    private final class b extends BroadcastReceiver {
        final /* synthetic */ d a;

        private b(d dVar) {
            this.a = dVar;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if ((intent.getIntExtra("plugged", -1) <= 0 ? 1 : null) != null) {
                    this.a.a();
                } else {
                    this.a.f();
                }
            }
        }
    }

    public d(Activity activity) {
        this.c = activity;
        a();
    }

    @SuppressLint({"NewApi"})
    public synchronized void a() {
        f();
        this.f = new a();
        this.f.execute(new Object[0]);
    }

    public synchronized void b() {
        f();
        if (this.e) {
            this.c.unregisterReceiver(this.d);
            this.e = false;
        } else {
            Log.w(a, "PowerStatusReceiver was never registered?");
        }
    }

    public synchronized void c() {
        if (this.e) {
            Log.w(a, "PowerStatusReceiver was already registered?");
        } else {
            this.c.registerReceiver(this.d, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.e = true;
        }
        a();
    }

    private synchronized void f() {
        AsyncTask asyncTask = this.f;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f = null;
        }
    }

    public void d() {
        f();
    }
}
