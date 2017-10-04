package dji.pilot.phonecamera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.SurfaceTexture;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.OrientationEventListener;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.pilot.phonecamera.e.h;
import dji.pilot.phonecamera.e.i;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public abstract class g implements c {
    private static final String H = "DJILPCameraModule";
    public static final String a = "OSMO_PHONE_TAKE_PHOTO";
    protected List<String> A;
    protected int B;
    protected int C;
    protected float D;
    protected e E;
    protected dji.pilot.g.a F;
    protected boolean G = false;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    protected int k = -1;
    protected int l = 0;
    protected int m = -1;
    protected final Handler n = new d(this);
    protected int o;
    protected int p;
    protected dji.pilot.phonecamera.e.g q;
    protected Parameters r;
    protected Parameters s;
    protected SurfaceTexture t;
    protected Context u;
    protected boolean v = false;
    protected int w = -1;
    protected List<String> x;
    protected List<String> y;
    protected List<String> z;

    protected class a extends AsyncTask<Void, Void, String> {
        final /* synthetic */ g a;
        private byte[] b;
        private String c;
        private final String d = Environment.getExternalStoragePublicDirectory(DJIUsbAccessoryReceiver.c).toString();
        private final String e = (this.d + "/Camera");

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        public a(g gVar, byte[] bArr, String str) {
            this.a = gVar;
            this.b = bArr;
            this.c = str;
        }

        protected void onPreExecute() {
            Log.d(g.H, "onPreExecute: ");
        }

        protected String a(Void... voidArr) {
            Log.d(g.H, "doInBackground: ");
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(this.b, 0, this.b.length, options);
            b(this.c, this.b);
            return b(this.c);
        }

        protected void a(String str) {
            this.a.k();
            this.a.d(str);
            if (this.a.k != 1) {
                dji.thirdparty.a.c.a().e(dji.pilot.phonecamera.a.a.TAKEPICTUREFINISHED);
            }
        }

        private void b(String str, byte[] bArr) {
            String b = b(str);
            Log.d(g.H, "addImage: path = " + b + " title = " + str);
            a(b, bArr);
            File file = new File(b);
            if (file.exists()) {
                HashMap hashMap = new HashMap();
                if (h.c.equals(this.a.r.getSceneMode())) {
                    Log.d(g.H, "addImage: add DJI-HDR flag");
                    hashMap.put(dji.thirdparty.g.b.b.a.b.aL, "DJI-HDR");
                    dji.b.a.a.a.a(file, hashMap);
                }
            }
        }

        private String b(String str) {
            File file = new File(this.e);
            if (!file.exists()) {
                file.mkdir();
            }
            return this.e + '/' + str + dji.pilot2.main.a.a.n;
        }

        public void a(String str, byte[] bArr) {
            Throwable e;
            FileOutputStream fileOutputStream = null;
            FileOutputStream fileOutputStream2;
            try {
                Log.d(g.H, "writeFile");
                fileOutputStream2 = new FileOutputStream(str);
                try {
                    fileOutputStream2.write(bArr);
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable e2) {
                        Log.e(g.H, "Failed to close file after write", e2);
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        Log.e(g.H, "Failed to write data", e2);
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable e22) {
                            Log.e(g.H, "Failed to close file after write", e22);
                        }
                    } catch (Throwable th) {
                        e22 = th;
                        fileOutputStream = fileOutputStream2;
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e4) {
                            Log.e(g.H, "Failed to close file after write", e4);
                        }
                        throw e22;
                    }
                }
            } catch (Exception e5) {
                e22 = e5;
                fileOutputStream2 = null;
                Log.e(g.H, "Failed to write data", e22);
                fileOutputStream2.close();
            } catch (Throwable th2) {
                e22 = th2;
                fileOutputStream.close();
                throw e22;
            }
        }
    }

    protected class b extends AsyncTask<Void, Void, String> {
        final /* synthetic */ g a;
        private byte[] b;
        private File c = null;
        private Uri d = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        public b(g gVar, byte[] bArr) {
            this.a = gVar;
            this.b = bArr;
        }

        protected String a(Void... voidArr) {
            try {
                this.d = dji.pilot.storage.a.a(this.a.u, 1, "jpg", dji.pilot.storage.a.d);
                Log.d(g.H, "saveUri: " + this.d);
                this.c = File.createTempFile("picFile", "jpg", this.a.u.getCacheDir());
                Log.d(g.H, "temp picFile: " + this.c.getAbsolutePath());
                HashMap hashMap = new HashMap();
                if (h.c.equals(this.a.r.getSceneMode())) {
                    Log.d(g.H, "addImage: add DJI-HDR flag");
                    hashMap.put(dji.thirdparty.g.b.b.a.b.aL, "DJI-HDR");
                    dji.b.a.a.a.a(this.c, hashMap);
                }
                new FileOutputStream(this.c).write(this.b);
                dji.pilot.storage.a.a(this.a.u, this.d, this.c);
            } catch (FileNotFoundException e) {
                Log.e(g.H, "File not found: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e2) {
                Log.e(g.H, "I/O error writing file: " + e2.getMessage());
                e2.printStackTrace();
            }
            return dji.pilot.storage.a.a(this.d).getAbsolutePath();
        }

        protected void a(String str) {
            this.a.k();
            this.a.d(str);
            if (this.a.k != 1) {
                dji.thirdparty.a.c.a().e(dji.pilot.phonecamera.a.a.TAKEPICTUREFINISHED);
            }
        }
    }

    protected final class c implements dji.pilot.phonecamera.e.e {
        final /* synthetic */ g a;

        protected c(g gVar) {
            this.a = gVar;
        }

        public void a(byte[] bArr, dji.pilot.phonecamera.e.g gVar) {
            Log.d(g.H, "onPictureTaken");
            if (dji.pilot.storage.a.c(this.a.u)) {
                new b(this.a, bArr).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return;
            }
            new a(this.a, bArr, h.a(System.currentTimeMillis())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private class d extends Handler {
        final /* synthetic */ g a;

        public d(g gVar) {
            this.a = gVar;
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    protected class e extends OrientationEventListener {
        final /* synthetic */ g a;

        public e(g gVar, Context context) {
            this.a = gVar;
            super(context);
        }

        public void onOrientationChanged(int i) {
            if (i != -1) {
                this.a.w = h.b(i, this.a.w);
            }
        }
    }

    protected final class f implements h {
        final /* synthetic */ g a;

        protected f(g gVar) {
            this.a = gVar;
        }

        public void a(Parameters parameters) {
            Log.d(g.H, "onParametersSetFailure: ");
            this.a.c(parameters);
            if (this.a.q != null) {
                this.a.q.k();
            }
        }

        public void b(Parameters parameters) {
            Log.d(g.H, "onParametersSetSuccess: ");
            this.a.c(parameters);
            if (this.a.q != null) {
                this.a.q.k();
            }
        }
    }

    protected final class g implements i {
        final /* synthetic */ g a;

        public g(g gVar) {
            this.a = gVar;
        }

        public void a(dji.pilot.phonecamera.e.g gVar) {
            Log.v(g.H, "mShutter");
        }
    }

    public dji.pilot.phonecamera.e.g b() {
        if (this.q == null) {
            return null;
        }
        return this.q;
    }

    public dji.pilot.phonecamera.e.g a(int i, dji.pilot.phonecamera.e.d dVar) {
        return a(this.n, i, dVar);
    }

    public dji.pilot.phonecamera.e.g a(dji.pilot.phonecamera.e.d dVar) {
        return d.a().a(this.n, dVar);
    }

    public dji.pilot.phonecamera.e.g a(int i) {
        return d.a().a(i);
    }

    public dji.pilot.phonecamera.e.g a(Handler handler, int i, dji.pilot.phonecamera.e.d dVar) {
        return d.a().a(handler, i, dVar);
    }

    public void j() {
        Log.d(H, "Close camera device.");
        if (this.q != null) {
            this.q.a(null);
            this.q.a(null, null);
            this.q.a(null);
            d.a().b(500);
            d.a().f();
            this.q = null;
        }
    }

    public void b(int i) {
        Log.d(H, "switchCamera: Start to switch camera. id =" + i);
        if (i >= 0) {
            this.m = i;
        } else if (d.a().i() == this.m) {
            this.m = d.a().j();
        } else if (d.a().j() == this.m) {
            this.m = d.a().i();
        }
        j();
        this.l = 0;
        this.q = a(this.m);
        if (this.q == null) {
            Log.d(H, "switchCamera: Failed to open camera:" + this.m + ", aborting.");
            return;
        }
        this.r = this.q.j();
        dji.pilot.phonecamera.a.c.a().a(this.r, this.m);
        o();
        k();
        this.n.postDelayed(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                dji.thirdparty.a.c.a().e(dji.pilot.phonecamera.a.a.CAMERA_ID_CHANGED);
            }
        }, 600);
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.t = surfaceTexture;
    }

    public void a(Handler handler, dji.pilot.phonecamera.e.f fVar) {
        this.q.a(handler, fVar);
    }

    public int a() {
        return this.k;
    }

    public void o() {
        this.s = this.q.j();
        this.x = this.s.getSupportedFlashModes();
        this.y = this.s.getSupportedWhiteBalance();
        this.z = this.s.getSupportedSceneModes();
        this.B = this.s.getMinExposureCompensation();
        this.C = this.s.getMaxExposureCompensation();
        this.D = this.s.getExposureCompensationStep();
        this.A = this.s.getSupportedFocusModes();
        this.I = h.f(this.s);
        this.J = h.e(this.s);
        Log.d(H, "initializeCapabilities: mMeteringAreaSupported = " + this.J);
        this.K = h.a(this.s);
        this.L = h.b(this.s);
        this.M = this.s.getSupportedFocusModes().contains(h.a);
    }

    public List<String> q() {
        if (this.y == null || this.y.size() == 0) {
            return null;
        }
        return this.y;
    }

    public List<String> r() {
        if (this.z == null || this.z.size() == 0) {
            return null;
        }
        return this.z;
    }

    public List<String> s() {
        if (this.z == null || this.z.size() == 0) {
            return null;
        }
        return this.z;
    }

    public int t() {
        return this.B;
    }

    public int u() {
        return this.C;
    }

    public float v() {
        return this.D;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(dji.pilot.phonecamera.c.a r5) {
        /*
        r4 = this;
        r2 = 0;
        r1 = 1;
        r0 = r4.s;
        if (r0 != 0) goto L_0x000e;
    L_0x0006:
        r0 = "DJILPCameraModule";
        r1 = "isSupported: mInitialParam is null";
        android.util.Log.d(r0, r1);
    L_0x000d:
        return r2;
    L_0x000e:
        r0 = dji.pilot.phonecamera.g.AnonymousClass2.a;
        r3 = r5.ordinal();
        r0 = r0[r3];
        switch(r0) {
            case 1: goto L_0x0056;
            case 2: goto L_0x0074;
            case 3: goto L_0x0082;
            case 4: goto L_0x008e;
            case 5: goto L_0x009c;
            case 6: goto L_0x00a6;
            default: goto L_0x0019;
        };
    L_0x0019:
        r0 = "DJILPCameraModule";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "isSupported: ";
        r1 = r1.append(r3);
        r3 = r5.toString();
        r1 = r1.append(r3);
        r3 = " don't support";
        r1 = r1.append(r3);
        r1 = r1.toString();
        android.util.Log.d(r0, r1);
    L_0x003b:
        r0 = r2;
    L_0x003c:
        r1 = "DJILPCameraModule";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "isSupported: mIsSupported = ";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        android.util.Log.d(r1, r2);
        r2 = r0;
        goto L_0x000d;
    L_0x0056:
        r0 = r4.A;
        if (r0 == 0) goto L_0x003b;
    L_0x005a:
        r0 = r4.A;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x003b;
    L_0x0062:
        r0 = r4.A;
        r0 = r0.get(r2);
        r0 = (java.lang.String) r0;
        r3 = "fixed";
        r0 = r0.equals(r3);
        if (r0 != 0) goto L_0x003b;
    L_0x0072:
        r0 = r1;
        goto L_0x003c;
    L_0x0074:
        r0 = r4.x;
        if (r0 == 0) goto L_0x003b;
    L_0x0078:
        r0 = r4.x;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x003b;
    L_0x0080:
        r0 = r1;
        goto L_0x003c;
    L_0x0082:
        r0 = "hdr";
        r3 = r4.z;
        r0 = dji.pilot.phonecamera.h.a(r0, r3);
        if (r0 == 0) goto L_0x003b;
    L_0x008c:
        r0 = r1;
        goto L_0x003c;
    L_0x008e:
        r0 = r4.z;
        if (r0 == 0) goto L_0x003b;
    L_0x0092:
        r0 = r4.z;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x003b;
    L_0x009a:
        r0 = r1;
        goto L_0x003c;
    L_0x009c:
        r0 = r4.C;
        if (r0 > 0) goto L_0x00a4;
    L_0x00a0:
        r0 = r4.B;
        if (r0 >= 0) goto L_0x003b;
    L_0x00a4:
        r0 = r1;
        goto L_0x003c;
    L_0x00a6:
        r0 = r4.y;
        if (r0 == 0) goto L_0x003b;
    L_0x00aa:
        r0 = r4.y;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x003b;
    L_0x00b2:
        r0 = r1;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.phonecamera.g.a(dji.pilot.phonecamera.c$a):boolean");
    }

    @TargetApi(16)
    public void a(boolean z) {
        a(z, true);
    }

    public void a(boolean z, boolean z2) {
        if (this.K) {
            this.G = z;
            Log.d(H, "setAutoExposureLockIfSupported: lock = " + z);
            if (!Build.MODEL.equals("LG-H868")) {
                this.r.setAutoExposureLock(z);
            }
            this.r.setFocusMode("auto");
            b(this.r);
            if (!z2) {
                return;
            }
            if (z) {
                dji.thirdparty.a.c.a().e(dji.pilot.phonecamera.a.a.AEAF_LOCKED);
            } else {
                dji.thirdparty.a.c.a().e(dji.pilot.phonecamera.a.a.AEAF_UNLOCKED);
            }
        }
    }

    @TargetApi(16)
    public void b(boolean z) {
        if (this.L) {
            this.r.setAutoWhiteBalanceLock(z);
            a(this.r);
        }
    }

    public void a(List<Area> list) {
        if (this.I) {
            Log.d(H, "setFocusAreasIfSupported:");
            this.r.setFocusAreas(list);
            a(this.r);
        }
    }

    public void b(List<Area> list) {
        if (this.J) {
            Log.d(H, "setMeteringAreasIfSupported: " + list.toString());
            this.r.setMeteringAreas(list);
            a(this.r);
        }
    }

    protected void g() {
        this.o = h.a((Activity) this.u);
        this.p = h.c(this.o, this.m);
        if (this.q != null) {
            this.q.a(this.p);
        }
    }

    public void d(String str) {
        Log.d(H, "notifyNewMedia:" + str);
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        this.u.sendBroadcast(intent);
        intent = new Intent(a);
        intent.putExtra("path", str);
        this.u.sendBroadcast(intent);
    }

    public void b(Parameters parameters) {
        Log.d(H, "setSyncParameters: ");
        this.q.a(parameters);
    }

    protected synchronized void c(Parameters parameters) {
        this.r = parameters;
    }

    public void a(Handler handler, dji.pilot.phonecamera.e.a aVar) {
        Log.d(H, "autoFocus:");
        this.r.setFocusMode("auto");
        a(this.r);
        this.q.a(handler, aVar);
    }

    public void n() {
        this.q.g();
    }

    public synchronized void a(Parameters parameters) {
        if (this.q != null) {
            Log.d(H, "setParameters: " + parameters);
            this.q.a(parameters, new f(this));
        }
    }

    public synchronized void a(Parameters parameters, h hVar) {
        if (this.q != null) {
            Log.d(H, "setParameters: " + parameters);
            this.q.a(parameters, hVar);
        }
    }

    public int x() {
        return this.r.getExposureCompensation();
    }

    public Size w() {
        if (this.q == null) {
            return null;
        }
        return this.r.getPreviewSize();
    }

    public int e() {
        return 1;
    }

    public boolean f() {
        return false;
    }

    public Location y() {
        return this.F == null ? null : this.F.a();
    }

    protected void c(boolean z) {
        if (z) {
            Log.e(H, "Parameters don't open!!");
            throw new AssertionError();
        }
    }
}
