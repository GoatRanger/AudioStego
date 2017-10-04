package com.nokia.maps;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.PointF;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.media.TransportMediator;
import android.support.v4.widget.AutoScrollHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import com.here.android.mpa.ar.ARController.SensorType;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.LocationDataSource;
import com.here.android.mpa.common.MatchedGeoPosition;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.common.Size;
import com.nokia.maps.annotation.HybridPlus;
import java.util.concurrent.atomic.AtomicBoolean;

@HybridPlus
public class ARSensors extends BaseNativeObject {
    private static volatile GeoCoordinateImpl G = null;
    private static final String j = ARSensors.class.getSimpleName();
    private final int A = 1000000;
    private fd B = null;
    private Size C = null;
    private float D;
    private float E;
    private GeoCoordinateImpl F = null;
    private GeoCoordinateImpl J = null;
    private boolean K = false;
    private volatile boolean L = false;
    private volatile boolean M = false;
    private boolean N = false;
    private boolean O = false;
    private Object P = new Object();
    private volatile int Q = -1;
    private volatile int R = -1;
    private int S = -1;
    private int T = -1;
    private a U;
    private long V;
    private float[] W = new float[9];
    private float[] X = new float[9];
    private float[] Y = new float[3];
    private float[] Z = new float[3];
    public final ar a = new ar();
    private float[] aa = new float[3];
    private double ab = Double.MAX_VALUE;
    private long ac = 30000;
    private long ad;
    private float ae = 0.0f;
    private float af = 3.0E-6f;
    private OrientationEventListener ag;
    private final boolean ah = true;
    private boolean ai = false;
    private float[] aj = new float[4];
    private boolean ak = false;
    private boolean al = false;
    private b am = b.UNKNOWN;
    private final boolean[] an = new boolean[SensorType.values().length];
    private ContentObserver ao = new ContentObserver(this, new Handler()) {
        final /* synthetic */ ARSensors a;

        public void onChange(boolean z) {
            if (!this.a.ag.canDetectOrientation()) {
                this.a.ag.disable();
                this.a.ai = false;
            } else if (this.a.v()) {
                this.a.ag.enable();
                this.a.ai = true;
            }
        }
    };
    private final Runnable ap = new Runnable(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.w();
            ez.a(this, 500);
        }
    };
    private SensorEventListener aq = new SensorEventListener(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onAccuracyChanged(android.hardware.Sensor r4, int r5) {
            /*
            r3 = this;
            r1 = r3.a;
            monitor-enter(r1);
            r0 = r3.a;	 Catch:{ all -> 0x0040 }
            r0 = r0.L;	 Catch:{ all -> 0x0040 }
            if (r0 != 0) goto L_0x000d;
        L_0x000b:
            monitor-exit(r1);	 Catch:{ all -> 0x0040 }
        L_0x000c:
            return;
        L_0x000d:
            monitor-exit(r1);	 Catch:{ all -> 0x0040 }
            r0 = r4.getType();
            switch(r0) {
                case 1: goto L_0x0016;
                case 2: goto L_0x0073;
                case 3: goto L_0x0015;
                case 4: goto L_0x0043;
                case 5: goto L_0x0015;
                case 6: goto L_0x0015;
                case 7: goto L_0x0015;
                case 8: goto L_0x0015;
                case 9: goto L_0x0016;
                case 10: goto L_0x0015;
                case 11: goto L_0x0089;
                default: goto L_0x0015;
            };
        L_0x0015:
            goto L_0x000c;
        L_0x0016:
            r0 = r3.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.ACCELEROMETER;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x000c;
        L_0x0026:
            r1 = r3.a;
            monitor-enter(r1);
            r0 = r3.a;	 Catch:{ all -> 0x0070 }
            r0.Q = r5;	 Catch:{ all -> 0x0070 }
            monitor-exit(r1);	 Catch:{ all -> 0x0070 }
            r0 = r3.a;
            r0 = r0.a;
            r1 = 1;
            r1 = java.lang.Integer.valueOf(r1);
            r2 = java.lang.Integer.valueOf(r5);
            r0.a(r3, r1, r2);
            goto L_0x000c;
        L_0x0040:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0040 }
            throw r0;
        L_0x0043:
            r0 = r3.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.GYROSCOPE;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x000c;
        L_0x0053:
            r1 = r3.a;
            monitor-enter(r1);
            r0 = r3.a;	 Catch:{ all -> 0x006d }
            r0.S = r5;	 Catch:{ all -> 0x006d }
            monitor-exit(r1);	 Catch:{ all -> 0x006d }
            r0 = r3.a;
            r0 = r0.a;
            r1 = 4;
            r1 = java.lang.Integer.valueOf(r1);
            r2 = java.lang.Integer.valueOf(r5);
            r0.a(r3, r1, r2);
            goto L_0x000c;
        L_0x006d:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x006d }
            throw r0;
        L_0x0070:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0070 }
            throw r0;
        L_0x0073:
            r0 = r3.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.COMPASS;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x000c;
        L_0x0083:
            r0 = r3.a;
            r0.c(r5);
            goto L_0x000c;
        L_0x0089:
            r0 = r3.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.COMPASS;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x000c;
        L_0x0099:
            r1 = r3.a;
            monitor-enter(r1);
            r0 = r3.a;	 Catch:{ all -> 0x00b5 }
            r0.T = r5;	 Catch:{ all -> 0x00b5 }
            monitor-exit(r1);	 Catch:{ all -> 0x00b5 }
            r0 = r3.a;
            r0 = r0.a;
            r1 = 11;
            r1 = java.lang.Integer.valueOf(r1);
            r2 = java.lang.Integer.valueOf(r5);
            r0.a(r3, r1, r2);
            goto L_0x000c;
        L_0x00b5:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x00b5 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.ARSensors.13.onAccuracyChanged(android.hardware.Sensor, int):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onSensorChanged(android.hardware.SensorEvent r3) {
            /*
            r2 = this;
            r1 = r2.a;
            monitor-enter(r1);
            r0 = r2.a;	 Catch:{ all -> 0x0041 }
            r0 = r0.M;	 Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0013;
        L_0x000b:
            r0 = r2.a;	 Catch:{ all -> 0x0041 }
            r0 = r0.L;	 Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0015;
        L_0x0013:
            monitor-exit(r1);	 Catch:{ all -> 0x0041 }
        L_0x0014:
            return;
        L_0x0015:
            monitor-exit(r1);	 Catch:{ all -> 0x0041 }
            r0 = r3.sensor;
            r0 = r0.getType();
            switch(r0) {
                case 1: goto L_0x0020;
                case 2: goto L_0x0065;
                case 3: goto L_0x001f;
                case 4: goto L_0x0044;
                case 5: goto L_0x001f;
                case 6: goto L_0x001f;
                case 7: goto L_0x001f;
                case 8: goto L_0x001f;
                case 9: goto L_0x0020;
                case 10: goto L_0x001f;
                case 11: goto L_0x008f;
                default: goto L_0x001f;
            };
        L_0x001f:
            goto L_0x0014;
        L_0x0020:
            r0 = com.nokia.maps.h.g;
            if (r0 == 0) goto L_0x0014;
        L_0x0024:
            r0 = r2.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.ACCELEROMETER;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x0014;
        L_0x0034:
            r0 = r2.a;
            r0.b(r3);
            r0 = r2.a;
            r0 = r0.d;
            r0.a(r2, r3);
            goto L_0x0014;
        L_0x0041:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0041 }
            throw r0;
        L_0x0044:
            r0 = com.nokia.maps.h.i;
            if (r0 == 0) goto L_0x0014;
        L_0x0048:
            r0 = r2.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.GYROSCOPE;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x0014;
        L_0x0058:
            r0 = r2.a;
            r0.a(r3);
            r0 = r2.a;
            r0 = r0.c;
            r0.a(r2, r3);
            goto L_0x0014;
        L_0x0065:
            r0 = com.nokia.maps.h.h;
            if (r0 == 0) goto L_0x0014;
        L_0x0069:
            r0 = r2.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.COMPASS;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x0014;
        L_0x0079:
            r0 = r2.a;
            r0 = r0.R;
            r1 = -1;
            if (r0 != r1) goto L_0x0089;
        L_0x0082:
            r0 = r2.a;
            r1 = r3.accuracy;
            r0.c(r1);
        L_0x0089:
            r0 = r2.a;
            r0.c(r3);
            goto L_0x0014;
        L_0x008f:
            r0 = com.nokia.maps.h.j;
            if (r0 == 0) goto L_0x0014;
        L_0x0093:
            r0 = r2.a;
            r0 = r0.an;
            r1 = com.here.android.mpa.ar.ARController.SensorType.COMPASS;
            r1 = r1.ordinal();
            r0 = r0[r1];
            if (r0 != 0) goto L_0x0014;
        L_0x00a3:
            r0 = r2.a;
            r0.d(r3);
            r0 = r2.a;
            r0 = r0.e;
            r0.a(r2, r3);
            goto L_0x0014;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.ARSensors.13.onSensorChanged(android.hardware.SensorEvent):void");
        }
    };
    private bh ar = new bh(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        public void a(LocationMethod locationMethod, Location location) {
        }

        public void a(LocationMethod locationMethod, int i) {
        }
    };
    private final com.nokia.maps.ar.a as = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (obj == null || obj2 == null) {
                return false;
            }
            Size size = (Size) obj2;
            return this.a.b((byte[]) obj, size.width, size.height);
        }
    };
    private com.nokia.maps.ar.a at = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a) {
                this.a.D();
            }
            this.a.h();
            return false;
        }
    };
    public final ar b = new ar();
    public final ar c = new ar();
    public final ar d = new ar();
    public final ar e = new ar();
    public final ar f = new ar();
    public final ar g = new ar();
    final Runnable h = new Runnable(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.C();
        }
    };
    final Runnable i = new Runnable(this) {
        final /* synthetic */ ARSensors a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.D();
        }
    };
    private SensorManager k = null;
    private int l = 1;
    private int m = 1;
    private int n = 1;
    private int o = 1;
    private final double p = Double.MAX_VALUE;
    private Sensor q = null;
    private Sensor r = null;
    private Sensor s = null;
    private Sensor t = null;
    private PositioningManager u = null;
    private LocationDataSource v = null;
    private Display w = null;
    private volatile float x = 0.0f;
    private float y = 0.0f;
    private Context z;

    enum a {
        UNKNOWN,
        RGB,
        RGBA,
        GRAY,
        YUV420PLANAR
    }

    private enum b {
        UNKNOWN,
        ORIENTATION_0,
        ORIENTATION_90,
        ORIENTATION_180,
        ORIENTATION_270;

        static b a(int i) {
            switch (i) {
                case 0:
                    return ORIENTATION_0;
                case 1:
                    return ORIENTATION_270;
                case 2:
                    return ORIENTATION_180;
                case 3:
                    return ORIENTATION_90;
                default:
                    return UNKNOWN;
            }
        }
    }

    private native void createNative(int i, int i2, float f, float f2, int i3, int i4, boolean z);

    private native void destroyNative();

    private native void onAccelerometerReading(float f, float f2, float f3, long j);

    private native boolean onCameraFrameNative(byte[] bArr, int i, int i2, long j);

    private native void onCompassReading(float f, float f2, float f3, float f4, float f5, float f6, long j);

    private native void onGyroscopeReading(float f, float f2, float f3, long j);

    private native void onLocationReading(double d, float f, double d2, float f2, float f3, float f4, float f5, float f6, float f7, int i, long j);

    private native void onOrientationReading(float f, float f2);

    private native void setCameraOrientationOffset(int i);

    private native void setMagneticDeclination(float f);

    private native void updateOrientation(int i);

    public native float Heading();

    public native float Pitch();

    public native float Roll();

    native synchronized void restartPoseEngine(int i);

    native void setCameraParameters(int i, int i2, float f, float f2);

    ARSensors(Context context, boolean z) {
        int ordinal;
        this.z = context.getApplicationContext();
        this.u = PositioningManager.getInstance();
        this.w = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.w.getMetrics(displayMetrics);
        this.C = new Size(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.D = displayMetrics.xdpi;
        this.E = displayMetrics.ydpi;
        this.al = r();
        this.am = b.a(this.w.getRotation());
        if (z) {
            ordinal = a.YUV420PLANAR.ordinal();
        } else {
            ordinal = a.UNKNOWN.ordinal();
        }
        createNative(h.b.width, h.b.height, 1.0f, 1.0f, ordinal, this.am.ordinal(), this.al);
        this.V = SystemClock.uptimeMillis();
        this.O = z();
        this.ag = new OrientationEventListener(this, context, 3) {
            final /* synthetic */ ARSensors a;

            public void onOrientationChanged(int i) {
                if (i != -1) {
                    this.a.f.a(null, Integer.valueOf(i));
                }
            }
        };
        if (G == null) {
            this.F = new GeoCoordinateImpl(52.519004d, 13.400234d, 2.0d);
            G = this.F;
        }
    }

    private static int a(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        switch (cameraInfo.orientation) {
            case 0:
                return 3;
            case 180:
                return 1;
            case 270:
                return 2;
            default:
                return 0;
        }
    }

    public boolean a() {
        return this.N;
    }

    private boolean v() {
        try {
            return System.getInt(this.z.getContentResolver(), "accelerometer_rotation") == 1;
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean b() {
        if (F().getDefaultSensor(1) == null && !this.an[SensorType.ACCELEROMETER.ordinal()]) {
            Log.e("livesight", "Livesight cannot work without accelerometer sensor");
            return false;
        } else if (F().getDefaultSensor(2) != null || this.an[SensorType.COMPASS.ordinal()]) {
            return true;
        } else {
            Log.e("livesight", "Livesight cannot work without compass sensor");
            return false;
        }
    }

    static void a(float f) {
        h.f = f;
    }

    static float c() {
        return h.f;
    }

    protected void finalize() {
        destroyNative();
    }

    public synchronized void d() {
        if (!this.L) {
            g();
            this.L = true;
        }
    }

    public synchronized void e() {
        if (this.L) {
            this.L = false;
            f();
        }
    }

    synchronized void f() {
        this.M = true;
        a(false);
        b(false);
        c(false);
        d(false);
        F().unregisterListener(this.aq);
        ez.b(this.ap);
        ez.a(this.i);
        if (this.ag.canDetectOrientation()) {
            this.ag.disable();
        }
        fd fdVar = this.B;
        this.B = null;
        if (fdVar != null) {
            fdVar.a();
        }
        this.k = null;
    }

    synchronized void g() {
        this.M = false;
        if (this.B == null) {
            this.B = new fd();
        }
        b a = b.a(this.w.getRotation());
        if (this.am != a) {
            updateOrientation(a.ordinal());
            this.am = a;
            this.g.a(null, Integer.valueOf(a.ordinal() - 1));
        }
        if (this.ag.canDetectOrientation()) {
            this.ag.enable();
        }
        c(true);
        d(true);
        a(true);
        b(true);
        ez.a(this.ap);
        ez.a(this.h);
    }

    private void w() {
        synchronized (this.P) {
            GeoCoordinateImpl E = E();
            if (E == null) {
                return;
            }
            a(E.a(), E.b(), E.c());
        }
    }

    synchronized void h() {
    }

    public int i() {
        return b.a(this.w.getRotation()).ordinal() - 1;
    }

    public int b(float f) {
        return (int) (this.D * f);
    }

    public int c(float f) {
        return (int) (this.E * f);
    }

    private void a(boolean z) {
        if (!h.g) {
            return;
        }
        if (!z) {
            this.Q = -1;
            b(1);
            this.q = null;
        } else if (this.q == null) {
            this.q = a(9, this.l);
            if (this.q == null) {
                Log.i("livesight", "TYPE_GRAVITY - FAILED");
                this.q = a(1, this.l);
            }
            if (this.q != null) {
                this.Q = -1;
            } else {
                h.g = false;
            }
        }
    }

    private void b(boolean z) {
        if (!h.h) {
            return;
        }
        if (!z) {
            this.R = -1;
            this.ab = Double.MAX_VALUE;
            b(2);
            this.r = null;
        } else if (this.r == null) {
            this.r = a(2, this.o);
            if (this.r != null) {
                this.R = -1;
            } else {
                h.h = false;
            }
        }
    }

    private void c(boolean z) {
        if (!h.i) {
            return;
        }
        if (!z) {
            this.S = -1;
            b(4);
            this.s = null;
        } else if (this.s == null) {
            this.s = a(4, this.m);
            if (this.s != null) {
                this.S = -1;
            } else {
                h.i = false;
            }
        }
    }

    private void d(boolean z) {
        if (!h.j) {
            return;
        }
        if (!z) {
            this.ak = false;
            this.T = -1;
            b(11);
            this.t = null;
        } else if (this.t == null && !this.an[SensorType.COMPASS.ordinal()]) {
            this.t = a(11, this.n);
            if (this.t != null) {
                this.T = -1;
            } else {
                h.j = false;
            }
        }
    }

    private synchronized Sensor a(int i, int i2) {
        Sensor sensor;
        if (F().getSensorList(i).isEmpty()) {
            sensor = null;
        } else {
            sensor = F().getDefaultSensor(i);
            F().registerListener(this.aq, sensor, i2);
        }
        return sensor;
    }

    private synchronized void b(int i) {
        switch (i) {
            case 1:
                if (this.q != null) {
                    F().unregisterListener(this.aq, this.q);
                    this.q = null;
                    break;
                }
                break;
            case 2:
                if (this.r != null) {
                    F().unregisterListener(this.aq, this.r);
                    this.r = null;
                    break;
                }
                break;
            case 4:
                if (this.s != null) {
                    F().unregisterListener(this.aq, this.s);
                    this.s = null;
                    break;
                }
                break;
            case 11:
                if (this.t != null) {
                    F().unregisterListener(this.aq, this.t);
                    this.t = null;
                    break;
                }
                break;
        }
    }

    void a(GeoCoordinate geoCoordinate) {
        synchronized (this.P) {
            if (geoCoordinate != null) {
                if (geoCoordinate.isValid()) {
                    this.N = true;
                    G.a(geoCoordinate.getLatitude());
                    G.b(geoCoordinate.getLongitude());
                    G.c(geoCoordinate.getAltitude());
                    this.K = false;
                }
            }
            this.N = false;
        }
    }

    synchronized void j() {
        q();
        fd fdVar = this.B;
        this.B = null;
        if (fdVar != null) {
            fdVar.a();
        }
        this.a.a();
        this.b.a();
        this.c.a();
        this.d.a();
        this.e.a();
        this.ag = null;
        D();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.ar = null;
    }

    private void a(double d, double d2, double d3) {
        if (!this.M && this.L) {
            final double d4 = d + ((double) (h.e ? this.ae : 0.0f));
            if (h.e) {
                this.ae += this.af;
            }
            final long uptimeMillis = SystemClock.uptimeMillis();
            if (this.B != null && this.B.isAlive()) {
                final double d5 = d2;
                final double d6 = d3;
                this.B.a(new Runnable(this) {
                    final /* synthetic */ ARSensors e;

                    public void run() {
                        this.e.onLocationReading(d4, 0.0f, d5, 0.0f, (float) d6, 0.0f, 0.0f, 0.0f, 0.0f, 0, uptimeMillis);
                        this.e.A();
                    }
                });
            }
        }
    }

    public GeoCoordinateImpl k() {
        if (G.d()) {
            return G;
        }
        return null;
    }

    public GeoCoordinateImpl a(AtomicBoolean atomicBoolean) {
        if (!G.d()) {
            return null;
        }
        atomicBoolean.set(this.K);
        return G;
    }

    public float d(float f) {
        return ((f % 360.0f) + 360.0f) % 360.0f;
    }

    public float l() {
        return 360.0f - d(this.x - A());
    }

    public synchronized int m() {
        return this.R;
    }

    public synchronized int n() {
        return this.Q;
    }

    public synchronized int o() {
        return this.S;
    }

    private void a(SensorEvent sensorEvent) {
        c(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], SystemClock.uptimeMillis());
    }

    private void c(float f, float f2, float f3, long j) {
        if (this.B != null && this.B.isAlive()) {
            final float f4 = f;
            final float f5 = f2;
            final float f6 = f3;
            final long j2 = j;
            this.B.a(new Runnable(this) {
                final /* synthetic */ ARSensors e;

                public void run() {
                    this.e.onGyroscopeReading(f4, f5, f6, j2);
                }
            });
        }
    }

    private void b(SensorEvent sensorEvent) {
        a(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], SystemClock.uptimeMillis());
    }

    public void a(float f, float f2, float f3, long j) {
        float f4 = f * -0.1f;
        float f5 = f2 * -0.1f;
        float f6 = f3 * -0.1f;
        this.Z[0] = f4;
        this.Z[1] = f5;
        this.Z[2] = f6;
        if (!this.ak) {
            a(this.Z, this.aa);
        }
        if (!this.an[SensorType.ACCELEROMETER.ordinal()]) {
            onAccelerometerReading(f4, f5, f6, j);
        } else if (this.B != null && this.B.isAlive()) {
            final float f7 = f4;
            final float f8 = f5;
            final float f9 = f6;
            final long j2 = j;
            this.B.a(new Runnable(this) {
                final /* synthetic */ ARSensors e;

                public void run() {
                    this.e.onAccelerometerReading(f7, f8, f9, j2);
                }
            });
        }
    }

    private void c(SensorEvent sensorEvent) {
        b(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], SystemClock.uptimeMillis());
    }

    public void b(float f, float f2, float f3, long j) {
        float f4 = AutoScrollHelper.NO_MAX;
        this.aa[0] = f;
        this.aa[1] = f2;
        this.aa[2] = f3;
        boolean z = this.ak;
        if (!z) {
            z = a(this.Z, this.aa);
        }
        final float f5 = z ? this.x : AutoScrollHelper.NO_MAX;
        if (z) {
            f4 = l();
        }
        if (this.B != null && this.B.isAlive()) {
            final float f6 = f;
            final float f7 = f2;
            final float f8 = f3;
            final long j2 = j;
            this.B.a(new Runnable(this) {
                final /* synthetic */ ARSensors g;

                public void run() {
                    this.g.onCompassReading(f6, f7, f8, f5, f4, h.f, j2);
                    if (f4 != AutoScrollHelper.NO_MAX) {
                        this.g.b.a(this, Float.valueOf(f4));
                    }
                }
            });
        }
    }

    private void d(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr != null) {
            a(fArr);
        }
    }

    private boolean a(float[] fArr, float[] fArr2) {
        if (!SensorManager.getRotationMatrix(this.W, this.X, fArr, fArr2)) {
            return false;
        }
        y();
        return true;
    }

    private void a(float[] fArr) {
        if (!this.O || fArr.length <= 4) {
            SensorManager.getRotationMatrixFromVector(this.W, fArr);
        } else {
            System.arraycopy(fArr, 0, this.aj, 0, 4);
            SensorManager.getRotationMatrixFromVector(this.W, this.aj);
        }
        this.ak = true;
        y();
    }

    private boolean x() {
        if (this.am == b.ORIENTATION_90 || this.am == b.ORIENTATION_270) {
            SensorManager.remapCoordinateSystem(this.W, 1, 3, this.W);
            return true;
        }
        if (this.am == b.ORIENTATION_180) {
            SensorManager.remapCoordinateSystem(this.W, 1, TransportMediator.KEYCODE_MEDIA_RECORD, this.W);
        }
        return false;
    }

    private void y() {
        boolean x = x();
        SensorManager.getOrientation(this.W, this.Y);
        this.x = d((float) Math.toDegrees((double) this.Y[0]));
        this.y = (float) Math.toDegrees((double) this.Y[1]);
        if (this.ak) {
            this.x = 360.0f - this.x;
            if (x) {
                this.y = 90.0f - this.y;
            } else {
                this.y = -this.y;
            }
        } else if (x) {
            this.y += 90.0f;
        }
        onOrientationReading(l(), this.y);
    }

    private boolean z() {
        try {
            SensorManager.getRotationMatrixFromVector(new float[9], new float[5]);
            return false;
        } catch (IllegalArgumentException e) {
            bj.b(j, "Samsung device throws: " + e.toString() + " due to a bug!", new Object[0]);
            return true;
        }
    }

    private boolean b(final byte[] bArr, final int i, final int i2) {
        if (this.B == null || !this.B.isAlive()) {
            a(bArr);
            return false;
        }
        this.B.a(new Runnable(this) {
            final /* synthetic */ ARSensors d;

            public void run() {
                if (this.d.L && this.d.U != null && this.d.U.b()) {
                    this.d.a(bArr, i, i2);
                    this.d.a(bArr);
                    return;
                }
                this.d.a(bArr);
            }
        });
        return true;
    }

    private void a(byte[] bArr) {
        if (this.U != null) {
            this.U.a(bArr);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (!this.M && this.L) {
            onCameraFrameNative(bArr, i, i2, SystemClock.uptimeMillis());
        }
    }

    public synchronized void a(a aVar, Size size, PointF pointF) {
        if (size != null) {
            if (size.width > 0 && size.height > 0) {
                this.U = aVar;
                setCameraParameters(size.width, size.height, pointF.x, pointF.y);
                int a = a(aVar.a());
                if (a != 0) {
                    setCameraOrientationOffset(a);
                }
            }
        }
    }

    public synchronized void p() {
        if (this.U != null) {
            this.U.c.a(this.as);
        }
    }

    public synchronized void q() {
        if (this.U != null) {
            this.U.c.b(this.as);
            this.U = null;
        }
    }

    boolean r() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.w.getMetrics(displayMetrics);
        int rotation = this.w.getRotation();
        if ((rotation == 0 || rotation == 2) && displayMetrics.widthPixels > displayMetrics.heightPixels) {
            return true;
        }
        if ((rotation == 1 || rotation == 3) && displayMetrics.widthPixels < displayMetrics.heightPixels) {
            return true;
        }
        return false;
    }

    private float A() {
        if (k() != null) {
            if (this.ab == Double.MAX_VALUE) {
                this.ab = B();
                e((float) this.ab);
                this.ad = SystemClock.uptimeMillis();
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (uptimeMillis - this.ad > this.ac) {
                    this.ad = uptimeMillis;
                    double B = B();
                    if (((int) B) != ((int) this.ab)) {
                        this.ab = B;
                        e((float) this.ab);
                    }
                }
            }
            return (float) this.ab;
        } else if (this.ab == Double.MAX_VALUE) {
            return 0.0f;
        } else {
            return (float) this.ab;
        }
    }

    void e(final float f) {
        if (this.B != null && this.B.isAlive()) {
            this.B.a(new Runnable(this) {
                final /* synthetic */ ARSensors b;

                public void run() {
                    this.b.setMagneticDeclination(f);
                }
            });
        }
    }

    private double B() {
        synchronized (this.P) {
            if (G.d()) {
                double declination = (double) new GeomagneticField((float) G.a(), (float) G.b(), (float) G.c(), System.currentTimeMillis()).getDeclination();
                return declination;
            }
            return 0.0d;
        }
    }

    boolean s() {
        if (h.g && !this.an[SensorType.ACCELEROMETER.ordinal()] && this.Q == -1) {
            return false;
        }
        if (h.h && !this.an[SensorType.COMPASS.ordinal()] && this.R == -1) {
            return false;
        }
        return true;
    }

    boolean a(SensorType sensorType, boolean z) {
        if (sensorType.ordinal() < 0 || sensorType.ordinal() >= SensorType.values().length) {
            return false;
        }
        if (this.an[sensorType.ordinal()] == z) {
            return true;
        }
        if (this.L && !this.M) {
            return false;
        }
        if (sensorType == SensorType.COMPASS) {
            this.ab = Double.MAX_VALUE;
        }
        this.an[sensorType.ordinal()] = z;
        return true;
    }

    public synchronized void a(SensorType sensorType, double d, double d2, double d3, long j) {
        if (this.an[sensorType.ordinal()]) {
            if (!this.M && this.L) {
                switch (sensorType) {
                    case ACCELEROMETER:
                        a((float) d, (float) d2, (float) d3, j);
                        break;
                    case GYROSCOPE:
                        c((float) d, (float) d2, (float) d3, j);
                        break;
                    case COMPASS:
                        b((float) d, (float) d2, (float) d3, j);
                        break;
                    case GPS:
                        synchronized (this.P) {
                            if (!this.N) {
                                G.a(d);
                                G.b(d2);
                                G.c(d3);
                                this.K = false;
                            }
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid sensor's type");
                }
            }
        }
    }

    private void C() {
        if (!this.an[SensorType.GPS.ordinal()]) {
            if (this.u != null) {
                this.u.start(LocationMethod.GPS_NETWORK);
            } else if (this.v != null) {
                this.v.start(LocationMethod.GPS_NETWORK);
            }
        }
    }

    private void D() {
        if (!this.an[SensorType.GPS.ordinal()]) {
            if (this.u != null) {
                this.u.stop();
            } else if (this.v != null) {
                this.v.stop();
            }
        }
    }

    private GeoCoordinateImpl E() {
        if (this.N) {
            if (G.d()) {
                return G;
            }
            return null;
        } else if (!this.an[SensorType.GPS.ordinal()]) {
            if (this.u != null) {
                GeoPosition position;
                if (this.u.hasValidPosition()) {
                    position = this.u.getPosition();
                } else {
                    position = this.u.getLastKnownPosition();
                }
                if (position.isValid()) {
                    GeoCoordinate coordinate = position.getCoordinate();
                    G.a(coordinate.getLatitude());
                    G.b(coordinate.getLongitude());
                    G.c(coordinate.getAltitude());
                    this.K = position instanceof MatchedGeoPosition;
                }
            } else if (this.v != null) {
                Location lastKnownLocation = this.v.getLastKnownLocation();
                if (lastKnownLocation != null) {
                    this.J.a(lastKnownLocation.getLatitude());
                    this.J.b(lastKnownLocation.getLongitude());
                    this.J.c(lastKnownLocation.getAltitude());
                    if (this.J.d()) {
                        G.a(this.J.a());
                        G.b(this.J.b());
                        G.c(this.J.c());
                    }
                }
            }
            if (G.d()) {
                return G;
            }
            return null;
        } else if (G.d()) {
            return G;
        } else {
            return null;
        }
    }

    private synchronized SensorManager F() {
        if (this.k == null) {
            this.k = (SensorManager) this.z.getSystemService("sensor");
        }
        return this.k;
    }

    private synchronized void c(int i) {
        this.R = i;
        this.a.a((Object) this, Integer.valueOf(2), Integer.valueOf(i));
    }
}
