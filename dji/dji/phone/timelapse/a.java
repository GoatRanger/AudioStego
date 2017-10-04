package dji.phone.timelapse;

import android.os.Handler;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushTimelapseStatus;
import dji.midware.data.model.P3.DataGimbalSetTimelapseParams;
import dji.pilot.fpv.R;
import dji.pilot.visual.a.d;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;

public class a {
    private static final String d = "DJILPTimelapsePresenter";
    private static a m = new a();
    private static Runnable n = new Runnable() {
        public void run() {
            c.a().e(new dji.phone.e.b(dji.phone.e.a.a.i, dji.phone.e.a.c.i));
        }
    };
    private static final float o = 0.1f;
    private static final float p = 50.0f;
    ArrayList<a> a;
    ArrayList<LpTlpPhotoView> b;
    DataGimbalSetTimelapseParams c;
    private DJILPTimelapseSetView e;
    private float f = d.c;
    private int g = 0;
    private long h = 60000;
    private boolean i;
    private boolean j;
    private boolean k;
    private Handler l;

    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[dji.pilot.phonecamera.a.a.values().length];
        static final /* synthetic */ int[] c = new int[dji.phone.e.a.a.values().length];

        static {
            try {
                c[dji.phone.e.a.a.j.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[dji.phone.e.a.a.l.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            b = new int[b.values().length];
            try {
                b[b.TLP_OK.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[b.TLP_GIMBAL_SLEEPING.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[b.TLP_NOTCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[b.TLP_SPEED_FAST.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[b.TLP_SPEED_SLOW.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[b.TLP_POSITION_NEAR.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[dji.pilot.phonecamera.a.a.f.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    private class a {
        public int a;
        public int b;
        public int c;
        final /* synthetic */ a d;

        public a(a aVar, int i, int i2, int i3) {
            this.d = aVar;
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public enum b {
        TLP_OK,
        TLP_GIMBAL_SLEEPING,
        TLP_NOTCONNECTED,
        TLP_SPEED_FAST,
        TLP_SPEED_SLOW,
        TLP_POSITION_NEAR
    }

    public static a getInstance() {
        return m;
    }

    public void a(DJILPTimelapseSetView dJILPTimelapseSetView) {
        this.e = dJILPTimelapseSetView;
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new DataGimbalSetTimelapseParams();
        dji.f.a.a(this);
        this.l = new Handler();
    }

    public void a() {
        this.e = null;
        dji.f.a.b(this);
    }

    public DataGimbalSetTimelapseParams b() {
        return this.c;
    }

    public b c() {
        b r = r();
        if (r == b.TLP_OK) {
            int i;
            if (j()) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    this.c.a(this.h, aVar.a, aVar.b, aVar.c);
                }
            }
            DataGimbalSetTimelapseParams b = b();
            if (j()) {
                i = 0;
            } else {
                i = 1;
            }
            b.a(i).b(this.g).c(0).start(new dji.midware.e.d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    dji.pilot.phonecamera.a.c.a().a(this.a.f);
                    int b = (int) (this.a.h / 1000);
                    DJILogHelper.getInstance().LOGD(a.d, "onSuccess: ", false, dji.phone.a.a);
                    if (this.a.j() || this.a.e.isTripod()) {
                        this.a.j = true;
                        dji.phone.k.b.showShort(this.a.e.getContext().getString(R.string.longan_timelapse2_preparing));
                        if (this.a.j()) {
                            b--;
                        }
                    } else {
                        this.a.o();
                        this.a.n();
                    }
                    dji.pilot.phonecamera.a.c.a().n(b);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD(a.d, "onFailure: " + aVar, false, dji.phone.a.a);
                }
            });
        }
        return r;
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(float f) {
        this.f = f;
    }

    public void a(long j) {
        this.h = (1000 * j) * 60;
    }

    public float d() {
        return this.f;
    }

    public int e() {
        return (int) (this.h / 1000);
    }

    public b a(LpTlpPhotoView lpTlpPhotoView, int i) {
        if (!dji.phone.bluetooth.c.getInstance().b()) {
            return b.TLP_NOTCONNECTED;
        }
        int yaw = DataGimbalGetPushParams.getInstance().getYaw();
        int pitch = DataGimbalGetPushParams.getInstance().getPitch();
        this.a.add(new a(this, DataGimbalGetPushParams.getInstance().getRoll(), pitch, yaw));
        this.b.add(lpTlpPhotoView);
        return b.TLP_OK;
    }

    public int f() {
        return this.a.size();
    }

    public int g() {
        Iterator it = this.a.iterator();
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            int i4 = aVar.c;
            int i5 = aVar.b;
            if (i2 != Integer.MAX_VALUE) {
                i3 += Math.abs(i4 - i2);
            } else {
                i2 = i4;
            }
            if (i != Integer.MAX_VALUE) {
                int i6 = i;
                i = Math.abs(i5 - i) + i3;
                i5 = i6;
            } else {
                i = i3;
            }
            i3 = i;
            i = i5;
        }
        return i3;
    }

    public LpTlpPhotoView b(int i) {
        return (LpTlpPhotoView) this.b.get(i);
    }

    public void c(int i) {
        this.a.remove(i);
        this.b.remove(i);
    }

    private void n() {
        this.l.postDelayed(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.j()) {
                    this.a.h();
                } else if (this.a.i) {
                    this.a.q();
                }
            }
        }, (long) (e() * 1000));
    }

    public void onEventBackgroundThread(DataGimbalGetPushTimelapseStatus dataGimbalGetPushTimelapseStatus) {
        DJILogHelper.getInstance().LOGD(d, "DataGimbalGetPushTimelapseStatus:" + dataGimbalGetPushTimelapseStatus.getTimelapseStatus(), false, true);
        if (dataGimbalGetPushTimelapseStatus.getTimelapseStatus() == 2) {
            if (!this.i && this.j) {
                this.j = false;
                o();
                n();
            }
        } else if (dataGimbalGetPushTimelapseStatus.getTimelapseStatus() == 3 && this.i) {
            q();
        }
    }

    private void o() {
        if (!j()) {
            dji.phone.c.a.c().a(true);
            this.k = false;
        }
        c.a().e(dji.phone.b.a.CMD_START_RECORD);
        this.i = true;
        this.e.post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e.clearGallery();
                this.a.e.hide();
            }
        });
        p();
        dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_TIME_LAPSE, true);
    }

    private void p() {
        if ((j() || this.g == 1) && this.i) {
            this.e.removeCallbacks(n);
            this.e.postDelayed(n, 30000);
        }
    }

    private void q() {
        this.i = false;
        this.a.clear();
        this.b.clear();
        if (this.e != null) {
            this.e.onTimelapseOver();
            this.e.removeCallbacks(n);
        }
        c.a().e(new dji.phone.e.b(dji.phone.e.a.a.j, dji.phone.e.a.c.i));
        c.a().e(new dji.phone.e.b(dji.phone.e.a.a.d, dji.phone.e.a.c.i));
        dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_TIME_LAPSE, false);
        if (this.k) {
            dji.phone.c.a.c().a(false);
            this.k = false;
        }
    }

    public void h() {
        this.c.c(1).start(new dji.midware.e.d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(a.d, "tlp debugonSuccess", false, true);
                this.a.q();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.d, "tlp debugonFailure:" + aVar, false, true);
            }
        });
        this.j = false;
    }

    public void onEventBackgroundThread(dji.pilot.phonecamera.a.a aVar) {
        switch (AnonymousClass6.a[aVar.ordinal()]) {
            case 1:
                DJILogHelper.getInstance().LOGD(d, "tlp debugRECORD_STOP", false, true);
                if (this.i) {
                    h();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(dji.midware.b.a.c cVar) {
        if (cVar == dji.midware.b.a.c.BLE_DEVICE_DISCONNECTED && this.i) {
            h();
        }
    }

    private b r() {
        if (!dji.phone.bluetooth.c.getInstance().b()) {
            return b.TLP_NOTCONNECTED;
        }
        if (dji.phone.f.a.getInstance().c()) {
            return b.TLP_GIMBAL_SLEEPING;
        }
        if (j()) {
            float g = ((float) g()) / ((float) e());
            if (g < o) {
                return b.TLP_SPEED_SLOW;
            }
            if (g > p) {
                return b.TLP_SPEED_FAST;
            }
        }
        return b.TLP_OK;
    }

    public boolean i() {
        if (this.a.isEmpty()) {
            return true;
        }
        return Math.abs(DataGimbalGetPushParams.getInstance().getYaw() - ((a) this.a.get(this.a.size() + -1)).c) >= 12 || Math.abs(DataGimbalGetPushParams.getInstance().getPitch() - ((a) this.a.get(this.a.size() - 1)).b) >= 12;
    }

    public boolean j() {
        return dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_MOTION;
    }

    public boolean k() {
        return this.e.isTripod();
    }

    public boolean l() {
        return (j() || k()) ? false : true;
    }

    public int[] m() {
        r0 = new int[2];
        int g = g();
        r0[0] = (int) (((float) g) / p);
        r0[1] = (int) (((float) g) / o);
        return r0;
    }

    public int[] a(int[] iArr) {
        int i;
        int[] m = m();
        int i2 = m[0] / 60;
        int i3 = m[1] / 60;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        for (i = 0; i < iArr.length; i++) {
            int i6 = iArr[i];
            if (i6 >= i2 && i5 == Integer.MIN_VALUE) {
                i5 = i;
            }
            if (i6 < i3) {
                i4 = i;
            }
        }
        int[] iArr2 = new int[2];
        if (i5 == Integer.MIN_VALUE) {
            i5 = 0;
        }
        i = iArr.length - 1;
        if (i4 == Integer.MAX_VALUE) {
            i4 = i;
        }
        iArr2[0] = i5;
        iArr2[1] = i4;
        return iArr2;
    }

    public static String b(long j) {
        String str;
        String str2;
        int i = ((int) j) / 3600;
        int i2 = (((int) j) % 3600) / 60;
        int i3 = ((int) j) % 60;
        if (i3 == 0) {
            i3 = 1;
        }
        String str3 = "";
        str3 = "";
        str3 = "";
        if (i <= 9) {
            str3 = "0" + i;
        } else {
            str3 = "" + i;
        }
        if (i2 <= 9) {
            str = "0" + i2;
        } else {
            str = "" + i2;
        }
        if (i3 <= 9) {
            str2 = "0" + i3;
        } else {
            str2 = "" + i3;
        }
        return "" + str3 + ":" + str + ":" + str2;
    }

    public void a(b bVar) {
        switch (bVar) {
            case TLP_OK:
                this.e.hide();
                return;
            case TLP_GIMBAL_SLEEPING:
                dji.phone.k.b.showShort(R.string.lp_gimbal_sleep_warning);
                return;
            case TLP_NOTCONNECTED:
                dji.phone.k.b.showShort(R.string.longan_error_notconnected);
                return;
            case TLP_SPEED_FAST:
                dji.phone.k.b.showShort(R.string.longan_timelapse2_error_dur_short);
                return;
            case TLP_SPEED_SLOW:
                dji.phone.k.b.showShort(R.string.longan_timelapse2_error_dur_long);
                return;
            case TLP_POSITION_NEAR:
                dji.phone.k.b.showShort(R.string.longan_timelapse2_error_position_near);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(dji.phone.e.b bVar) {
        switch (AnonymousClass6.c[bVar.b.ordinal()]) {
            case 1:
                p();
                return;
            case 2:
                if (this.i) {
                    h();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(dji.phone.e.a.a aVar) {
        if (aVar == dji.phone.e.a.a.n) {
            a(c());
        }
    }
}
