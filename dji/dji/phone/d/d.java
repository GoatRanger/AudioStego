package dji.phone.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.phonecamera.a.c;
import dji.pilot.phonecamera.a.c.b;

public class d implements OnSharedPreferenceChangeListener, b, b {
    private static final Object l = new Object();
    private static d m = null;
    private static final int n = 300;
    private final String g = d.class.getSimpleName();
    private Context h = null;
    private c i;
    private HandlerThread j;
    private boolean k = false;
    private a o;

    private class a extends Handler {
        final /* synthetic */ d a;

        a(d dVar, Looper looper) {
            this.a = dVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    String str = (String) message.obj;
                    if (str.equals(c.u)) {
                        if (c.x.equals(c.a().t())) {
                            this.a.i.a.a(a.b.SINGLE, false);
                        } else if (c.y.equals(c.a().t())) {
                            this.a.i.a.a(a.b.LONGEXPOSURE, false);
                        }
                    }
                    if (str.equals(c.n)) {
                    }
                    if (str.equals(c.w)) {
                    }
                    if (str.equals(c.d)) {
                        Log.d(this.a.g, "handleMessage: KEY_VIDEO_QUALITY_ID");
                        this.a.i.a.d(c.a().l(), false);
                    }
                    if (!str.equals(c.s)) {
                        return;
                    }
                    return;
                case 101:
                    Log.d(this.a.g, "handleMessage: MSG_ON_SHARED_PREFERENCE_CLEAR");
                    this.a.i.a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, false);
                    this.a.i.a(c.b.CAMERA_BACK, false);
                    this.a.a(a.b.SINGLE, false);
                    this.a.a(a.c.NOT_VEDIO, false);
                    this.a.a(dji.phone.d.a.a.SINGLE_0s, false);
                    this.a.d(c.a().l(), false);
                    this.a.b(c.a().g(), false);
                    this.a.a(c.a().f(), false);
                    this.a.n();
                    return;
                case 300:
                    this.a.m();
                    return;
                default:
                    return;
            }
        }
    }

    public static d a(Context context) {
        d dVar;
        synchronized (l) {
            if (m != null) {
                m.b(context);
                dVar = m;
            } else {
                m = new d();
                m.b(context);
                dVar = m;
            }
        }
        return dVar;
    }

    public static d getInstance() {
        d dVar;
        synchronized (l) {
            if (m == null) {
                throw new RuntimeException("DJILPCameraStatusInfoController.getInstance can't be called before make()");
            }
            dVar = m;
        }
        return dVar;
    }

    private d() {
    }

    private void b(Context context) {
        this.j = new HandlerThread("camera status info updater");
        this.j.start();
        this.o = new a(this, this.j.getLooper());
        Log.d(this.g, "DJILPCameraStatusInfoController: Constructor invoked");
        this.h = context;
        this.o.sendMessage(this.o.obtainMessage(300));
    }

    public void a() {
        c.a().b(this);
        c.a().b(this);
        if (this.j != null) {
            this.j.quit();
        }
        this.j = null;
        this.h = null;
        this.k = false;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.o.sendMessage(this.o.obtainMessage(100, str));
    }

    private void m() {
        Log.d(this.g, "initCameraStatus: ");
        this.i = new c();
        this.i.a = new a();
        if (c.a().s() == dji.pilot.phonecamera.d.a().i()) {
            a(c.b.CAMERA_BACK, false);
            this.i.a.b();
        } else if (c.a().s() == dji.pilot.phonecamera.d.a().j()) {
            a(c.b.CAMERA_FRONT, false);
            this.i.a.a();
        }
        if (c.a().o() == 1) {
            a(dji.phone.d.c.a.CAMERASTATE_RECORD_PREVIEW, false);
        } else if (c.a().o() == 0) {
            a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, false);
        }
        b(c.a().g(), false);
        a(c.a().f(), false);
        d(c.a().l(), false);
        c.a().a(this);
        c.a().a(this);
        this.k = true;
    }

    public c.b b() {
        return this.i.a();
    }

    public dji.phone.d.c.a c() {
        return this.i.b();
    }

    public void a(c.b bVar, boolean z) {
        this.i.a(bVar, z);
    }

    public void a(dji.phone.d.c.a aVar, boolean z) {
        this.i.a(aVar, z);
    }

    public void a(int i, boolean z) {
        this.i.a.a(i, z);
    }

    public int d() {
        return this.i.a.c();
    }

    public void b(int i, boolean z) {
        this.i.a.b(i, z);
    }

    public int e() {
        return this.i.a.e();
    }

    public synchronized void c(int i, boolean z) {
        if (!(c() == dji.phone.d.c.a.CAMERASTATE_RECORDING && (i() == a.c.TIMELAPSE_STATIONARY || i() == a.c.TIMELAPSE_MOTION))) {
            if (!((c() == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE && (h() == a.b.LONGEXPOSURE || h() == a.b.PANO)) || DJILPUISwitcher.a == dji.phone.g.b.TRACKING)) {
                Log.d(this.g, "setZoom: " + c() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + h());
                dji.phone.c.a.c().c(i);
                this.i.a.c(i, z);
            }
        }
    }

    public int f() {
        return this.i.a.d();
    }

    public void d(int i, boolean z) {
        this.i.a.d(i, z);
    }

    public int g() {
        return this.i.a.f();
    }

    public void a(a.b bVar, boolean z) {
        this.i.a.a(bVar, z);
    }

    public a.b h() {
        return this.i.a.g();
    }

    public void a(a.c cVar, boolean z) {
        this.i.a.a(cVar, z);
    }

    public a.c i() {
        return this.i.a.h();
    }

    public void a(dji.phone.d.a.a aVar, boolean z) {
        this.i.a.a(aVar, z);
    }

    public dji.phone.d.a.a j() {
        return this.i.a.i();
    }

    private void n() {
        Log.d(this.g, "notifyCameraStatusInfoChanged: ");
        dji.thirdparty.a.c.a().e(this.i);
    }

    public c k() {
        return this.i;
    }

    public void l() {
        this.o.sendMessage(this.o.obtainMessage(101));
    }
}
