package dji.pilot.visual.radar;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.publics.objects.k;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.d;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$b;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.radar.b.b;
import java.util.Arrays;

public class a implements dji.pilot.publics.objects.k.a, b, dji.pilot.visual.radar.b.a {
    private final Context q;
    private final b r;
    private final k s;
    private final c t = c.getInstance();
    private final int[][] u = new int[][]{new int[4], new int[4], new int[4], new int[4]};
    private final int[] v = new int[4];
    private final boolean[] w = new boolean[]{true, true, true, true};
    private final Callback x = new Callback(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            return false;
        }
    };

    public a(Context context, b bVar) {
        this.q = context;
        this.r = bVar;
        this.s = new k(this, this.x);
    }

    public boolean a(boolean z, int i) {
        if (z) {
            return c();
        }
        g.c b = this.t.b(b(i));
        return b == g.c.e || b == g.c.c;
    }

    public boolean a(int i) {
        return this.t.a(b(i));
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.PARAMETER_UNIT_CHANGED) {
            int length = this.v.length;
            for (int i = 0; i < length; i++) {
                c(i);
            }
        }
    }

    private void c(int i) {
        if (this.w[i]) {
            g$b c = this.t.c(b(i));
            int[] d = d();
            if (c.f != dji.pilot.fpv.model.o.a.RADAR) {
                d = k;
            }
            if (this.v[i] < d[d.length - 1]) {
                this.r.updateNormalDesc(i, d(this.v[i]), 0);
            }
        }
    }

    private void b(boolean z, int i) {
        boolean[] zArr = this.w;
        if (z != zArr[i]) {
            zArr[i] = z;
            if (z) {
                c(i);
            } else {
                this.r.updateNormalDesc(i, this.q.getString(g[i]), 0);
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean z = false;
        boolean isMotorUp = dataOsdGetPushCommon.isMotorUp();
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        boolean z2 = !isMotorUp || (!(FLYC_STATE.GPS_Atti == flycState || FLYC_STATE.NOVICE == flycState) || this.w[0]);
        if (this.w[0] != z2) {
            if (!isMotorUp || this.w[0]) {
                z2 = true;
            } else {
                z2 = false;
            }
            b(z2, 0);
        }
        if (!isMotorUp || (!(FLYC_STATE.GPS_Atti == flycState || FLYC_STATE.NOVICE == flycState) || this.w[1])) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.w[1] != z2) {
            if (!isMotorUp || this.w[1]) {
                z = true;
            }
            b(z, 1);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
    }

    public void onEventMainThread(DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        if (!DataOsdGetPushCommon.getInstance().isMotorUp()) {
            return;
        }
        if (FLYC_STATE.GPS_Atti == DataOsdGetPushCommon.getInstance().getFlycState() || DataOsdGetPushCommon.getInstance().getFlycState() == FLYC_STATE.NOVICE) {
            b(dataEyeGetPushAvoidanceParam.allowFront(), 0);
            b(dataEyeGetPushAvoidanceParam.allowBack(), 1);
        }
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (g$d.AVOID_CHANGED == dji_pilot_visual_a_g_d) {
            this.s.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.onEventMainThread(this.a.t.c(SensorType.Front));
                }
            });
            this.s.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.onEventMainThread(this.a.t.c(SensorType.Back));
                }
            });
            this.s.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.onEventMainThread(this.a.t.c(SensorType.Left));
                }
            });
            this.s.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.onEventMainThread(this.a.t.c(SensorType.Right));
                }
            });
        }
    }

    public void onEventMainThread(dji.logic.g.a.b bVar) {
        SensorType sensorType = null;
        if (dji.logic.g.a.b.a == bVar) {
            sensorType = SensorType.Front;
        } else if (dji.logic.g.a.b.b == bVar) {
            sensorType = SensorType.Back;
        } else if (dji.logic.g.a.b.c == bVar) {
            sensorType = SensorType.Left;
        } else if (dji.logic.g.a.b.d == bVar) {
            sensorType = SensorType.Right;
        }
        if (sensorType != null && e(a(sensorType))) {
            a(this.t.c(sensorType), dji.logic.g.a.getInstance().a(sensorType));
        }
    }

    private void a(g$b dji_pilot_visual_a_g_b, DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance) {
        int a = a(dji_pilot_visual_a_g_b.a);
        if (dataEyeGetPushFrontAvoidance == null || dataEyeGetPushFrontAvoidance.getObserveValues() == null) {
            this.v[a] = Integer.MAX_VALUE;
            this.r.safeToHideView(a, true, 0);
            return;
        }
        int[] observeValues = dataEyeGetPushFrontAvoidance.getObserveValues();
        int[] iArr = this.u[a];
        boolean a2 = dji_pilot_visual_a_g_b.a();
        boolean[] a3 = a(iArr, observeValues, a2);
        if (!a3[0]) {
            int i;
            int i2;
            if (a2) {
                i2 = 0;
                i = Integer.MAX_VALUE;
                while (i2 < observeValues.length && i2 < 4) {
                    this.r.updateRadarImg(a, a(dji_pilot_visual_a_g_b.a, a2, i2, observeValues[i2]), i2, 0);
                    if (observeValues[i2] < i) {
                        i = observeValues[i2];
                    }
                    i2++;
                }
                if (observeValues.length < 4) {
                    for (i2 = observeValues.length; i2 < 4; i2++) {
                        this.r.updateRadarImg(a, 0, i2, 0);
                    }
                }
            } else {
                i2 = 0;
                i = Integer.MAX_VALUE;
                while (i2 < observeValues.length && i2 < 4) {
                    if (observeValues[i2] < i) {
                        i = observeValues[i2];
                    }
                    i2++;
                }
                this.r.updateRadarImg(a, a(dji_pilot_visual_a_g_b.a, a2, 0, i), 0, 0);
                for (i2 = 1; i2 < 4; i2++) {
                    this.r.updateRadarImg(a, 0, i2, 0);
                }
            }
            if (a3[1]) {
                this.v[a] = Integer.MAX_VALUE;
                this.r.safeToHideView(a, !a2, 0);
                return;
            }
            this.v[a] = i;
            this.r.showWarningView(a, 0);
            if (this.w[a]) {
                this.r.updateNormalDesc(a, d(i), 0);
            }
        }
    }

    private String d(int i) {
        float f = ((float) (i / 50)) * d.c;
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            return String.format("%d FT", new Object[]{Integer.valueOf((int) DJIGenSettingDataManager.getInstance().b(f))});
        }
        return String.format("%.1f M", new Object[]{Float.valueOf(f)});
    }

    public int a(SensorType sensorType) {
        if (SensorType.Front == sensorType) {
            return 0;
        }
        if (SensorType.Back == sensorType) {
            return 1;
        }
        if (SensorType.Left == sensorType) {
            return 2;
        }
        if (SensorType.Right == sensorType) {
            return 3;
        }
        return 0;
    }

    public SensorType b(int i) {
        if (i == 0) {
            return SensorType.Front;
        }
        if (1 == i) {
            return SensorType.Back;
        }
        if (2 == i) {
            return SensorType.Left;
        }
        if (3 == i) {
            return SensorType.Right;
        }
        return SensorType.Front;
    }

    private boolean[] a(int[] iArr, int[] iArr2, boolean z) {
        boolean[] zArr = new boolean[]{false, true};
        zArr[0] = Arrays.equals(iArr, iArr2);
        if (!zArr[0]) {
            System.arraycopy(iArr2, 0, iArr, 0, 4);
            int[] d = d();
            if (!z) {
                d = k;
            }
            for (int i : iArr2) {
                if (i <= d[d.length - 1]) {
                    zArr[1] = false;
                }
            }
        }
        return zArr;
    }

    public void onEventMainThread(g$b dji_pilot_visual_a_g_b) {
        if (g.c.e == dji_pilot_visual_a_g_b.c) {
            this.r.showTypeNormalView(a(dji_pilot_visual_a_g_b.a), 0);
            a(dji_pilot_visual_a_g_b, dji.logic.g.a.getInstance().a(dji_pilot_visual_a_g_b.a));
        } else if (g.c.c != dji_pilot_visual_a_g_b.c) {
            this.r.hideTypeView(a(dji_pilot_visual_a_g_b.a), 0);
        } else if (dji_pilot_visual_a_g_b.f == dji.pilot.fpv.model.o.a.RADAR) {
            this.r.showTypeInvalidView(a(dji_pilot_visual_a_g_b.a), this.t.d(dji_pilot_visual_a_g_b.a), 0);
        } else {
            this.r.hideTypeView(a(dji_pilot_visual_a_g_b.a), 0);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK || mode == MODE.DOWNLOAD) {
            this.r.go();
        } else if (a(true, 4)) {
            this.r.show();
        }
    }

    private boolean c() {
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        return (dji.pilot2.simulator.d.h() || !dji.pilot.fpv.d.b.n(null) || mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK || mode == MODE.DOWNLOAD) ? false : true;
    }

    private boolean e(int i) {
        return c() && this.t.b(b(i)) == g.c.e;
    }

    private int[] d() {
        int[] iArr = i;
        ProductType c = i.getInstance().c();
        if (ProductType.Tomato == c || dji.pilot.publics.e.a.c(c)) {
            return j;
        }
        return iArr;
    }

    private int a(SensorType sensorType, int i, int i2) {
        int[][] iArr;
        int[][] iArr2 = (int[][]) null;
        if (SensorType.Front == sensorType) {
            iArr = l;
        } else if (SensorType.Back == sensorType) {
            iArr = m;
        } else if (SensorType.Left == sensorType) {
            iArr = iArr2;
        } else {
            iArr = SensorType.Right == sensorType ? iArr2 : iArr2;
        }
        if (iArr == null) {
            return 0;
        }
        int[] d = d();
        int i3 = 0;
        while (i3 < d.length && i2 > d[i3]) {
            i3++;
        }
        return iArr[i][i3];
    }

    private int b(SensorType sensorType, int i, int i2) {
        int i3 = 0;
        int[] iArr = null;
        if (SensorType.Front != sensorType) {
            if (SensorType.Back == sensorType) {
                iArr = n;
            } else if (SensorType.Left == sensorType) {
                iArr = o;
            } else if (SensorType.Right == sensorType) {
                iArr = p;
            }
        }
        if (iArr == null) {
            return 0;
        }
        while (i3 < k.length && i2 > k[i3]) {
            i3++;
        }
        return iArr[i3];
    }

    private int a(SensorType sensorType, boolean z, int i, int i2) {
        if (z) {
            return a(sensorType, i, i2);
        }
        return b(sensorType, i, i2);
    }

    public boolean isFinished() {
        return !ServiceManager.getInstance().isRemoteOK();
    }

    public void a() {
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        onEventMainThread(g$d.AVOID_CHANGED);
    }

    public void b() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }
}
