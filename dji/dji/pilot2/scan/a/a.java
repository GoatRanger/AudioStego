package dji.pilot2.scan.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.preference.PreferenceManager;
import android.util.Log;
import dji.pilot2.scan.android.PreferencesActivity;
import java.util.ArrayList;
import java.util.Collection;

final class a implements AutoFocusCallback {
    private static final String a = a.class.getSimpleName();
    private static final long b = 2000;
    private static final Collection<String> c = new ArrayList(2);
    private boolean d;
    private boolean e;
    private final boolean f;
    private final Camera g;
    private AsyncTask<?, ?, ?> h;

    private final class a extends AsyncTask<Object, Object, Object> {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            this.a.a();
            return null;
        }
    }

    static {
        c.add("auto");
        c.add("macro");
    }

    a(Context context, Camera camera) {
        boolean z = true;
        this.g = camera;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera.getParameters().getFocusMode();
        if (!(defaultSharedPreferences.getBoolean(PreferencesActivity.o, true) && c.contains(focusMode))) {
            z = false;
        }
        this.f = z;
        Log.i(a, "Current focus mode '" + focusMode + "'; use auto focus? " + this.f);
        a();
    }

    public synchronized void onAutoFocus(boolean z, Camera camera) {
        this.e = false;
        c();
    }

    @SuppressLint({"NewApi"})
    private synchronized void c() {
        if (!this.d && this.h == null) {
            AsyncTask aVar = new a();
            try {
                aVar.execute(new Object[0]);
                this.h = aVar;
            } catch (Throwable e) {
                Log.w(a, "Could not request auto focus", e);
            }
        }
    }

    synchronized void a() {
        if (this.f) {
            this.h = null;
            if (!(this.d || this.e)) {
                try {
                    this.g.autoFocus(this);
                    this.e = true;
                } catch (Throwable e) {
                    Log.w(a, "Unexpected exception while focusing", e);
                    c();
                }
            }
        }
    }

    private synchronized void d() {
        if (this.h != null) {
            if (this.h.getStatus() != Status.FINISHED) {
                this.h.cancel(true);
            }
            this.h = null;
        }
    }

    synchronized void b() {
        this.d = true;
        if (this.f) {
            d();
            try {
                this.g.cancelAutoFocus();
            } catch (Throwable e) {
                Log.w(a, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
