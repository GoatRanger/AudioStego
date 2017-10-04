package dji.pilot.visual.a;

import android.content.Context;
import android.support.v4.widget.AutoScrollHelper;
import android.util.SparseBooleanArray;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataEyeGetPushPointState;
import dji.midware.data.model.P3.DataEyeGetPushPointState.PointMode;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataSingleCommonCtrl;
import dji.midware.data.model.P3.DataSingleCommonCtrl.CtrlState;
import dji.midware.data.model.P3.DataSingleSetPointPos;
import dji.midware.data.model.P3.DataSingleSetPointPos.TapMode;
import dji.midware.data.model.P3.DataSingleVisualParam;
import dji.midware.data.model.P3.DataSingleVisualParam.ParamCmdId;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import java.util.Arrays;

public class d implements g {
    protected static final String a = d.class.getSimpleName();
    public static final float b = 3.0f;
    public static final float c = 0.5f;
    private final c d;
    private final b e;
    private final float[] f;
    private final float[] g;
    private volatile boolean h;
    private volatile int i;
    private a j;
    private volatile int k;
    private volatile float s;
    private TapMode t;
    private Context u;
    private final SparseBooleanArray v;

    public enum a {
        NONE,
        OBSTACLE,
        VISION_ENV,
        UNDER_EXPOSURE,
        OVER_EXPOSURE,
        LOW_LIMIT_DOWN,
        HALT,
        DEFAULT_EXIT
    }

    public static class b {
        public float a = Float.MIN_VALUE;

        public void a() {
            this.a = Float.MIN_VALUE;
        }
    }

    public static class c {
        public PointMode a = PointMode.HIDE;
        public float b = Float.MIN_VALUE;
        public float c = Float.MIN_VALUE;
        public dji.pilot.visual.util.b.a d = dji.pilot.visual.util.b.a.NON;
        public dji.pilot.visual.util.b.a e = dji.pilot.visual.util.b.a.NON;
        public boolean f = false;
        public boolean g = false;
        public boolean h = false;

        public void a() {
            this.a = PointMode.HIDE;
            this.b = Float.MIN_VALUE;
            this.c = Float.MIN_VALUE;
            this.d = dji.pilot.visual.util.b.a.NON;
            this.e = dji.pilot.visual.util.b.a.NON;
            this.f = false;
            this.g = false;
            this.h = false;
        }
    }

    d() {
        this.d = new c();
        this.e = new b();
        this.f = new float[]{AutoScrollHelper.NO_MAX, AutoScrollHelper.NO_MAX, AutoScrollHelper.NO_MAX};
        this.g = new float[2];
        this.h = false;
        this.i = Integer.MIN_VALUE;
        this.j = a.NONE;
        this.k = Integer.MIN_VALUE;
        this.s = Float.MIN_VALUE;
        this.t = TapMode.a;
        this.u = null;
        this.v = new SparseBooleanArray(2);
        this.u = DJIApplication.a();
        this.v.put(TapMode.b.a(), g.b(this.u, g.r + TapMode.b.toString(), true));
        this.v.put(TapMode.d.a(), g.b(this.u, g.r + TapMode.d.toString(), true));
    }

    public boolean a(TapMode tapMode) {
        return this.v.get(tapMode.a());
    }

    public void a(TapMode tapMode, boolean z) {
        int a = tapMode.a();
        if (this.v.get(a) != z) {
            this.v.put(a, z);
            g.a(this.u, g.r + tapMode.toString(), z);
        }
    }

    public boolean a() {
        return this.h;
    }

    public void b(TapMode tapMode) {
        if (tapMode != this.t) {
            this.t = tapMode;
            new DataSingleCommonCtrl().a(CtrlState.b).start(null);
        }
    }

    public TapMode b() {
        if (dji.pilot.visual.util.c.d(i.getInstance().c())) {
            return this.t;
        }
        return TapMode.a;
    }

    public float c() {
        return this.e.a;
    }

    public float d() {
        return this.s != Float.MIN_VALUE ? this.s : 3.0f;
    }

    void a(g$f dji_pilot_visual_a_g_f) {
        if (dji_pilot_visual_a_g_f == g$f.NONE) {
            b(false);
        }
    }

    void e() {
        b(true);
        dji.thirdparty.a.c.a().e(this.d);
    }

    private void a(DataEyeGetPushPointState dataEyeGetPushPointState, boolean z) {
        a aVar = a.NONE;
        if (dataEyeGetPushPointState.cantDetour()) {
            aVar = a.OBSTACLE;
        } else if (dataEyeGetPushPointState.isFrontImageOverExposure()) {
            aVar = a.OVER_EXPOSURE;
        } else if (dataEyeGetPushPointState.isFrontImageUnderExposure()) {
            aVar = a.UNDER_EXPOSURE;
        } else if (dataEyeGetPushPointState.isFrontImageDiff() || dataEyeGetPushPointState.isFrontDemarkError()) {
            aVar = a.VISION_ENV;
        } else if (dataEyeGetPushPointState.isOutOfRange()) {
            aVar = a.LOW_LIMIT_DOWN;
        } else if (!dataEyeGetPushPointState.isNonInFlying()) {
            if (dataEyeGetPushPointState.isPaused() || dataEyeGetPushPointState.isUserQuickPullPitch()) {
                aVar = a.HALT;
            } else if (z && !this.h) {
            }
        }
        if (this.j != aVar) {
            if (aVar != a.NONE) {
                dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
                bVar.b = R.string.visual_point_fail_title;
                if (aVar == a.OBSTACLE) {
                    bVar.d = R.string.visual_point_cant_detour_tip;
                } else if (aVar == a.OVER_EXPOSURE) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_over_exposure;
                } else if (aVar == a.UNDER_EXPOSURE) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_under_exposure;
                } else if (aVar == a.VISION_ENV) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_no_radar;
                } else if (aVar == a.LOW_LIMIT_DOWN) {
                    bVar.d = R.string.visual_point_outofrange;
                } else if (aVar == a.DEFAULT_EXIT) {
                    bVar.b = R.string.visual_point_exit_default;
                    dji.thirdparty.a.c.a().e(bVar);
                } else if (aVar == a.HALT) {
                    bVar.b = R.string.visual_point_paused_tip;
                    dji.thirdparty.a.c.a().e(bVar);
                }
                if (bVar.d != 0) {
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            this.j = aVar;
        }
    }

    void a(c$a dji_pilot_visual_a_c_a) {
        if (this.d.a != PointMode.HIDE) {
            float[] a = dji.pilot.visual.util.d.a(this.f, c.getInstance().i, c.getInstance().j);
            this.d.b = a[0];
            this.d.c = a[1];
            dji.thirdparty.a.c.a().e(this.d);
        }
    }

    void a(DataEyeGetPushPointState dataEyeGetPushPointState) {
        boolean z = false;
        PointMode tragetMode = dataEyeGetPushPointState.getTragetMode();
        boolean z2 = this.h;
        if (tragetMode != PointMode.HIDE) {
            this.h = true;
            if (!z2) {
                a(null, true);
            }
        } else if (this.d.a != PointMode.HIDE) {
            this.h = false;
        }
        int sessionId = dataEyeGetPushPointState.getSessionId();
        if (Integer.MIN_VALUE == this.i) {
            this.i = sessionId;
        }
        if (this.i == sessionId) {
            boolean z3;
            dji.pilot.visual.util.b.a aVar = dji.pilot.visual.util.b.a.NON;
            if (dataEyeGetPushPointState.detourLeft()) {
                aVar = dji.pilot.visual.util.b.a.LEFT;
            } else if (dataEyeGetPushPointState.detourRight()) {
                aVar = dji.pilot.visual.util.b.a.RIGHT;
            } else if (dataEyeGetPushPointState.detourUp()) {
                aVar = dji.pilot.visual.util.b.a.UP;
            }
            dji.pilot.visual.util.b.a aVar2 = dji.pilot.visual.util.b.a.NON;
            float zSpeed = ((float) DataOsdGetPushCommon.getInstance().getZSpeed()) * 0.1f;
            if (zSpeed > c) {
                aVar2 = dji.pilot.visual.util.b.a.DOWN;
            } else if (zSpeed < -0.5f) {
                aVar2 = dji.pilot.visual.util.b.a.UP;
            }
            if (this.k != sessionId) {
                this.k = sessionId;
                this.j = a.NONE;
            }
            if (this.h != z2) {
                z3 = true;
            } else {
                z3 = false;
            }
            a(dataEyeGetPushPointState, z3);
            float axisX = dataEyeGetPushPointState.getAxisX();
            zSpeed = dataEyeGetPushPointState.getAxisY();
            float axisZ = dataEyeGetPushPointState.getAxisZ();
            if (Math.abs(axisX - this.f[0]) > 0.005f || Math.abs(zSpeed - this.f[1]) > 0.005f || Math.abs(axisZ - this.f[2]) > 0.005f) {
                this.f[0] = axisX;
                this.f[1] = zSpeed;
                this.f[2] = axisZ;
                float[] a = dji.pilot.visual.util.d.a(this.f, c.getInstance().i, c.getInstance().j);
                this.d.b = a[0];
                this.d.c = a[1];
                z3 = true;
            } else {
                z3 = false;
            }
            boolean cantDetour = dataEyeGetPushPointState.cantDetour();
            boolean isTerrianFollow = dataEyeGetPushPointState.isTerrianFollow();
            if (dataEyeGetPushPointState.isPaused() || dataEyeGetPushPointState.isUserQuickPullPitch()) {
                z = true;
            }
            if (!(!z3 && this.d.a == tragetMode && aVar == this.d.d && cantDetour == this.d.f && isTerrianFollow == this.d.g && z == this.d.h && aVar2 == this.d.e)) {
                this.d.a = tragetMode;
                this.d.d = aVar;
                this.d.e = aVar2;
                this.d.f = cantDetour;
                this.d.g = isTerrianFollow;
                this.d.h = z;
                dji.thirdparty.a.c.a().e(this.d);
            }
            float maxSpeed = dataEyeGetPushPointState.getMaxSpeed();
            if (a() && this.e.a != maxSpeed) {
                this.e.a = maxSpeed;
                dji.thirdparty.a.c.a().e(this.e);
            }
        }
        if (z2 != this.h) {
            c.getInstance().q();
            dji.thirdparty.a.c.a().e(g$d.CTRLMODE_CHANGED);
        }
    }

    public void a(final float f, final dji.midware.e.d dVar) {
        new DataSingleVisualParam().a(false).a(ParamCmdId.k).d(f).start(new dji.midware.e.d(this) {
            final /* synthetic */ d c;

            public void onSuccess(Object obj) {
                this.c.s = f;
                dVar.onSuccess(obj);
                dji.thirdparty.a.c.a().e(g$d.USERSPEED_CHANGED);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dVar.onFailure(aVar);
            }
        });
    }

    public void a(final dji.midware.e.d dVar, boolean z) {
        if (Float.MIN_VALUE == this.s || z) {
            final DataSingleVisualParam a = new DataSingleVisualParam().a(true).a(ParamCmdId.k);
            a.start(new dji.midware.e.d(this) {
                final /* synthetic */ d c;

                public void onSuccess(Object obj) {
                    this.c.s = a.i();
                    if (dVar != null) {
                        dVar.onSuccess(null);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (dVar != null) {
                        dVar.onSuccess(null);
                    }
                }
            });
        } else if (dVar != null) {
            dVar.onSuccess(null);
        }
    }

    public void a(boolean z) {
        int i = 0;
        DataSingleVisualParam a = new DataSingleVisualParam().a(false).a(ParamCmdId.m);
        if (z) {
            i = 1;
        }
        a.c(i).start(null);
    }

    private int a(int i) {
        if (i >= 512 || i <= 0) {
            return 1;
        }
        return i + 1;
    }

    public void a(float f, float f2) {
        dji.pilot.visual.util.d.a(this.g, f, f2, c.getInstance().i, c.getInstance().j);
        int a = a(this.i);
        this.i = a;
        dji.pilot.visual.util.c.a("SendTapMode-" + b());
        new DataSingleSetPointPos().a(this.g[0], this.g[1]).a((short) a).a(b()).start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
                bVar.b = R.string.visual_point_fail_title;
                dji.thirdparty.a.c.a().e(bVar);
            }
        });
    }

    private void b(boolean z) {
        boolean z2 = this.h;
        this.h = false;
        this.k = Integer.MIN_VALUE;
        this.j = a.NONE;
        this.s = Float.MIN_VALUE;
        this.d.a();
        this.e.a();
        Arrays.fill(this.f, AutoScrollHelper.NO_MAX);
        if (z && z2 != this.h) {
            c.getInstance().q();
            dji.thirdparty.a.c.a().e(g$d.CTRLMODE_CHANGED);
        }
    }
}
