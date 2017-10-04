package dji.pilot.phonecamera;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import dji.pilot.phonecamera.e.g;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class d {
    private static final String a = "DJILPCameraHolder";
    private static final int b = 3000;
    private static g[] l = null;
    private static CameraInfo[] m = null;
    private static final boolean n = false;
    private static ArrayList<b> o = new ArrayList();
    private static SimpleDateFormat p = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static d r = null;
    private static final int s = 1;
    private g c;
    private long d;
    private final Handler e;
    private boolean f;
    private final int g;
    private int h = -1;
    private int i = -1;
    private int j = -1;
    private final CameraInfo[] k;
    private Parameters q;

    private class a extends Handler {
        final /* synthetic */ d a;

        a(d dVar, Looper looper) {
            this.a = dVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    synchronized (this.a) {
                        if (!this.a.f) {
                            this.a.f();
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private static class b {
        long a;
        int b;
        String c;
        String[] d;

        private b() {
        }
    }

    private static synchronized void a(int i, g gVar) {
        synchronized (d.class) {
            b bVar = new b();
            bVar.a = System.currentTimeMillis();
            bVar.b = i;
            if (gVar == null) {
                bVar.c = "(null)";
            } else {
                bVar.c = gVar.toString();
            }
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String[] strArr = new String[stackTrace.length];
            for (int i2 = 0; i2 < stackTrace.length; i2++) {
                strArr[i2] = stackTrace[i2].toString();
            }
            bVar.d = strArr;
            if (o.size() > 10) {
                o.remove(0);
            }
            o.add(bVar);
        }
    }

    private static synchronized void l() {
        synchronized (d.class) {
            for (int size = o.size() - 1; size >= 0; size--) {
                b bVar = (b) o.get(size);
                Log.d(a, "State " + size + " at " + p.format(new Date(bVar.a)));
                Log.d(a, "mCameraId = " + bVar.b + ", mCameraDevice = " + bVar.c);
                Log.d(a, "Stack:");
                for (String str : bVar.d) {
                    Log.d(a, "  " + str);
                }
            }
        }
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (r == null) {
                r = new d();
            }
            dVar = r;
        }
        return dVar;
    }

    public static void a(CameraInfo[] cameraInfoArr, g[] gVarArr) {
        m = cameraInfoArr;
        l = gVarArr;
        r = new d();
    }

    private d() {
        int i = 0;
        HandlerThread handlerThread = new HandlerThread(a);
        handlerThread.start();
        this.e = new a(this, handlerThread.getLooper());
        if (m != null) {
            this.g = m.length;
            this.k = m;
        } else {
            this.g = Camera.getNumberOfCameras();
            this.k = new CameraInfo[this.g];
            for (int i2 = 0; i2 < this.g; i2++) {
                this.k[i2] = new CameraInfo();
                Camera.getCameraInfo(i2, this.k[i2]);
            }
        }
        while (i < this.g) {
            if (this.i == -1 && this.k[i].facing == 0) {
                this.i = i;
            } else if (this.j == -1 && this.k[i].facing == 1) {
                this.j = i;
            }
            i++;
        }
    }

    public CameraInfo b() {
        CameraInfo cameraInfo = null;
        for (int i = 0; i < this.g; i++) {
            if (this.k[i].facing == 1) {
                cameraInfo = this.k[i];
            }
        }
        return cameraInfo;
    }

    public CameraInfo c() {
        CameraInfo cameraInfo = null;
        for (int i = 0; i < this.g; i++) {
            if (this.k[i].facing == 0) {
                cameraInfo = this.k[i];
            }
        }
        return cameraInfo;
    }

    public int d() {
        return this.g;
    }

    public CameraInfo[] e() {
        return this.k;
    }

    public synchronized g a(int i) {
        return a(this.e, i, new dji.pilot.phonecamera.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
            }

            public void b(int i) {
            }

            public void a(e eVar) {
            }
        });
    }

    public synchronized g a(dji.pilot.phonecamera.e.d dVar) {
        return a(this.e, i(), dVar);
    }

    public synchronized g a(Handler handler, dji.pilot.phonecamera.e.d dVar) {
        return a(handler, i(), dVar);
    }

    public synchronized g a(Handler handler, int i, dji.pilot.phonecamera.e.d dVar) {
        g gVar = null;
        synchronized (this) {
            if (!(this.c == null || this.h == i)) {
                this.c.b();
                this.c = null;
                this.h = -1;
            }
            if (this.c == null) {
                if (m == null) {
                    this.c = f.a().a(handler, i, dVar);
                } else if (l != null) {
                    this.c = l[i];
                } else {
                    Log.e(a, "MockCameraInfo found, but no MockCamera provided.");
                    this.c = null;
                }
                if (this.c == null) {
                    Log.e(a, "fail to connect Camera:" + this.h + ", aborting.");
                } else {
                    this.h = i;
                    this.q = this.c.j();
                    this.f = true;
                    if (this.e != null) {
                        this.e.removeMessages(1);
                    }
                    this.d = 0;
                    gVar = this.c;
                }
            } else if (this.c.a(handler, dVar)) {
                this.c.a(this.q, null);
                this.f = true;
                if (this.e != null) {
                    this.e.removeMessages(1);
                }
                this.d = 0;
                gVar = this.c;
            } else {
                Log.e(a, "fail to reconnect Camera:" + this.h + ", aborting.");
            }
        }
        return gVar;
    }

    public void a(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    public synchronized g b(Handler handler, int i, dji.pilot.phonecamera.e.d dVar) {
        return !this.f ? a(handler, i, dVar) : null;
    }

    public synchronized void f() {
        if (this.c != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.d) {
                if (this.f) {
                    this.f = false;
                    this.c.f();
                }
                this.e.sendEmptyMessageDelayed(1, this.d - currentTimeMillis);
            } else {
                g();
            }
        }
    }

    public synchronized void g() {
        if (this.c != null) {
            this.f = false;
            this.c.b();
            this.c = null;
            this.q = null;
            this.h = -1;
        }
    }

    public void h() {
        b(3000);
    }

    public synchronized void b(int i) {
        this.d = System.currentTimeMillis() + ((long) i);
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public int k() {
        return this.h;
    }
}
