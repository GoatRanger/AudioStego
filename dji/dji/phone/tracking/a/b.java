package dji.phone.tracking.a;

import android.graphics.Rect;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.phone.tracking.DJINativeTracking;
import dji.phone.tracking.e;
import dji.pilot.phonecamera.d;
import dji.thirdparty.a.c;

public class b extends Thread {
    public static b a = b.NONE;
    private static final String j = "DJILPTrackingThread";
    private static final boolean k = false;
    private boolean A;
    private int B;
    private long C;
    private int D = 0;
    private int E = 0;
    private boolean F = true;
    private Rect G = new Rect((this.c / 2) - 200, (this.d / 2) - 200, (this.c / 2) + 200, (this.d / 2) + 200);
    private int H;
    e b = new e();
    int c = dji.phone.preview.a.e;
    int d = dji.phone.preview.a.f;
    float e = 400.0f;
    public Object f = new Object();
    public Object g = new Object();
    long h = 0;
    int i = 0;
    private dji.phone.tracking.a l = new dji.phone.tracking.a();
    private byte[] m;
    private byte[] n;
    private int[] o = new int[5];
    private float p;
    private float q;
    private boolean r;
    private float s;
    private float t;
    private int u;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    private static class a {
        private static float a;
        private static float b;

        private a() {
        }
    }

    public enum b {
        WAIT_INIT,
        WAIT_DATA,
        TRACKING,
        NONE
    }

    public b(String str) {
        super(str);
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.n = bArr;
            if (dji.phone.tracking.b.e && !this.F) {
                synchronized (this.g) {
                    this.F = true;
                    this.g.notify();
                }
            }
        }
    }

    public void a() {
        synchronized (this.g) {
            this.g.notify();
        }
    }

    public void a(boolean z) {
        this.r = z;
        if (this.r) {
            dji.f.a.a(this);
        } else {
            dji.f.a.b(this);
        }
    }

    public void a(boolean z, Rect rect) {
        if (a == b.WAIT_INIT && !this.y) {
            this.x = z;
            if (z) {
                int i = (rect.top + rect.bottom) / 2;
                this.s = (float) ((rect.left + rect.right) / 2);
                this.t = (float) i;
            } else {
                this.s = (float) rect.left;
                this.t = (float) rect.top;
            }
            this.u = Math.abs(rect.left - rect.right);
            this.v = Math.abs(rect.top - rect.bottom);
            a(b.WAIT_DATA);
            this.y = true;
            synchronized (this.f) {
                this.f.notify();
            }
        }
    }

    public void run() {
        super.run();
        DJILogHelper.getInstance().LOGD(j, "start run");
        while (this.r) {
            if (this.y) {
                if (a == b.WAIT_DATA) {
                    synchronized (this.f) {
                        if (this.n == null) {
                            try {
                                DJILogHelper.getInstance().LOGD(j, "wait data");
                                this.f.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        this.m = this.n;
                    }
                    this.p = ((float) this.c) / ((float) dji.phone.tracking.b.c);
                    this.q = ((float) this.d) / ((float) dji.phone.tracking.b.d);
                    int i = (int) (this.s / this.p);
                    int i2 = (int) (this.t / this.q);
                    int i3 = (int) (((float) this.u) / this.p);
                    int i4 = (int) (((float) this.v) / this.q);
                    if (this.x) {
                        this.w = DJINativeTracking.tapInit(this.m, dji.phone.tracking.b.c, dji.phone.tracking.b.d, this.m.length, i, i2);
                    } else {
                        this.w = DJINativeTracking.areaInit(this.m, dji.phone.tracking.b.c, dji.phone.tracking.b.d, this.m.length, i, i2, i3, i4);
                    }
                    if (this.w == 0) {
                        a(b.TRACKING);
                        dji.publics.b.b.a.getInstance().d("createtime", System.currentTimeMillis() + "", false).d("status", "1", false).d("device_type", i.getInstance().c()._name(), true);
                    } else {
                        this.y = false;
                        a(this.w);
                        synchronized (this.f) {
                            try {
                                a(b.WAIT_INIT);
                                DJILogHelper.getInstance().LOGD(j, "init failed");
                                this.f.wait();
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                if (a == b.TRACKING) {
                    int a;
                    this.m = this.n;
                    int track = DJINativeTracking.track(this.m, this.m.length, this.o);
                    Rect rect = new Rect((int) (((float) (this.o[0] - (this.o[2] / 2))) * this.p), (int) (((float) (this.o[1] - (this.o[3] / 2))) * this.q), (int) (((float) (this.o[0] + (this.o[2] / 2))) * this.p), (int) (((float) (this.o[1] + (this.o[3] / 2))) * this.q));
                    int i5 = (int) (((float) this.o[1]) * this.q);
                    a.a = (((float) ((int) (((float) this.o[0]) * this.p))) - ((((float) this.c) / 2.0f) + ((float) this.D))) / (((float) this.c) / 2.0f);
                    a.b = (((float) i5) - ((((float) this.d) / 2.0f) + ((float) this.E))) / (((float) this.d) / 2.0f);
                    if (((double) a.a) > 0.8d - ((double) (((float) this.D) / (((float) this.c) / 2.0f))) || ((double) a.a) < (-(0.8d + ((double) (((float) this.D) / (((float) this.c) / 2.0f)))))) {
                        this.z = true;
                    } else {
                        this.z = false;
                    }
                    if (((double) a.a) > 0.33d + ((double) (((float) this.D) / (((float) this.c) / 2.0f)))) {
                        a = (int) (((3.0f * a.a) * a.a) * this.e);
                    } else if (((double) a.a) < (-(0.33d + ((double) (((float) this.D) / (((float) this.c) / 2.0f)))))) {
                        a = (int) (((-3.0f * a.a) * a.a) * this.e);
                    } else {
                        a = (int) (a.a * this.e);
                    }
                    if (((double) a.b) > 0.33d) {
                        i5 = (int) (((-3.0f * a.b) * a.b) * this.e);
                    } else if (((double) a.b) < -0.33d) {
                        i5 = (int) (((3.0f * a.b) * a.b) * this.e);
                    } else {
                        i5 = -((int) (a.b * this.e));
                    }
                    if ((d.a().k() == d.a().j() ? 1 : null) != null) {
                        a = -a;
                        i5 = -i5;
                    }
                    if (track != 0) {
                        this.A = false;
                        this.l.a(a, i5);
                        this.B = a;
                        this.l.a();
                    } else if (!this.A) {
                        this.A = true;
                        this.C = System.currentTimeMillis();
                    } else if (this.z) {
                        g();
                    }
                    if (this.h == 0) {
                        this.h = System.currentTimeMillis();
                    }
                    a = (int) (System.currentTimeMillis() - this.h);
                    this.i++;
                    if (a > 1000) {
                        this.b.d = this.i;
                        this.i = 0;
                        this.h = System.currentTimeMillis();
                    }
                    this.b.b = track;
                    this.b.c = this.o[4];
                    if (track == 0) {
                        this.H++;
                        if (this.H > 30) {
                            this.b.a = this.G;
                            this.H = 0;
                        }
                    } else {
                        this.H = 0;
                        this.b.a = rect;
                    }
                    c.a().e(this.b);
                }
                if (dji.phone.tracking.b.e && this.r) {
                    synchronized (this.g) {
                        try {
                            this.F = false;
                            if (this.r) {
                                this.g.wait();
                            }
                        } catch (InterruptedException e22) {
                            e22.printStackTrace();
                        }
                    }
                }
            } else {
                synchronized (this.f) {
                    try {
                        a(b.WAIT_INIT);
                        DJILogHelper.getInstance().LOGD(j, "data null, will wait. status:" + a);
                        this.f.wait();
                    } catch (InterruptedException e222) {
                        e222.printStackTrace();
                    }
                }
            }
        }
        a(b.NONE);
        DJILogHelper.getInstance().LOGD(j, "status to none");
    }

    private void a(int i) {
        int i2 = 3;
        if (i == 1) {
            c.a().e(dji.phone.tracking.c.TK_INIT_FAILED_BIG);
        } else if (i == 2) {
            c.a().e(dji.phone.tracking.c.TK_INIT_FAILED_SMALL);
        } else if (i == 3) {
            c.a().e(dji.phone.tracking.c.TK_INIT_FAILED_NO_TARGET);
            i2 = 4;
        }
        dji.publics.b.b.a.getInstance().d("createtime", System.currentTimeMillis() + "", false).d("status", i2 + "", false).d("device_type", i.getInstance().c()._name() + "", false).d("device_ver", dji.device.common.b.getInstance().b(), false).d("pro_id", dji.publics.b.b.a.a, true);
    }

    private void g() {
        if (this.B != 0 && !DataGimbalGetPushAbnormalStatus.getInstance().isYawLimit()) {
            int abs = Math.abs(this.B);
            int currentTimeMillis = (int) (((float) abs) - ((((float) abs) / 1000.0f) * ((float) (System.currentTimeMillis() - this.C))));
            if (this.y && currentTimeMillis >= 0) {
                this.l.a((abs / this.B) * currentTimeMillis, 0).a();
                DJILogHelper.getInstance().LOGD(j, "slowDownWhenLost:" + currentTimeMillis, false, true);
            }
        }
    }

    public void a(b bVar) {
        a = bVar;
        c.a().e(a);
        DJILogHelper.getInstance().LOGD("", "post tk status" + bVar);
        if (bVar == b.TRACKING) {
            dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_TRACK, true);
        } else {
            dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_TRACK, false);
        }
    }

    public boolean b() {
        return a == b.WAIT_DATA;
    }

    public boolean c() {
        return a == b.WAIT_INIT;
    }

    public boolean d() {
        return a == b.TRACKING;
    }

    public boolean e() {
        return a == b.NONE;
    }

    public void f() {
        a(b.WAIT_INIT);
        this.y = false;
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams.getJoystickHorDirection() == 1) {
            this.D -= 10;
        } else if (dataGimbalGetPushParams.getJoystickHorDirection() == 2) {
            this.D += 10;
        }
        if (dataGimbalGetPushParams.getJoystickVerDirection() == 1) {
            this.E -= 10;
        } else if (dataGimbalGetPushParams.getJoystickVerDirection() == 2) {
            this.E += 10;
        }
        if (dataGimbalGetPushParams.isDoubleClick()) {
            this.D = 0;
            this.E = 0;
        }
    }
}
