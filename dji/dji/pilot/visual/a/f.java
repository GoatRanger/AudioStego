package dji.pilot.visual.a;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.util.SparseBooleanArray;
import com.alipay.sdk.j.i;
import dji.gs.e.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetFocusArea;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TargetObjType;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackException;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackMode;
import dji.midware.data.model.P3.DataEyeSendUserLocation;
import dji.midware.data.model.P3.DataSingleCtrlTrackSelect;
import dji.midware.data.model.P3.DataSingleCtrlTrackSelect.TrackCtrlState;
import dji.midware.data.model.P3.DataSingleMoveTrackSelect;
import dji.midware.data.model.P3.DataSingleMoveTrackSelect.MoveCtrlType;
import dji.midware.data.model.P3.DataSingleSetTrackSelect;
import dji.midware.data.model.P3.DataSingleVisualParam;
import dji.midware.data.model.P3.DataSingleVisualParam.ParamCmdId;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.objects.k;
import dji.pilot.visual.util.c;
import dji.pilot.visual.util.e;

public class f implements Callback, dji.pilot.publics.objects.k.a, g {
    private static final int u = 4096;
    private static final int v = 4097;
    private short a;
    private k b;
    private Context c;
    private final a d;
    private volatile int e;
    private volatile boolean f;
    private volatile float g;
    private final SparseBooleanArray h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean s;
    private boolean t;

    public static class a {
        public TrackMode a = TrackMode.LOST;
        public float b = Float.MIN_VALUE;
        public float c = Float.MIN_VALUE;
        public float d = Float.MIN_VALUE;
        public float e = Float.MIN_VALUE;
        public TrackException f = TrackException.NONE;
        public int g = Integer.MIN_VALUE;
        public boolean h = false;
        public TargetObjType i = TargetObjType.UNKNOWN;
        public dji.pilot.visual.util.b.a j = dji.pilot.visual.util.b.a.NON;
        public TrackingMode k = TrackingMode.g;

        public void a() {
            this.a = TrackMode.LOST;
            this.b = Float.MIN_VALUE;
            this.c = Float.MIN_VALUE;
            this.d = Float.MIN_VALUE;
            this.e = Float.MIN_VALUE;
            this.g = Integer.MIN_VALUE;
            this.f = TrackException.NONE;
            this.h = false;
            this.i = TargetObjType.UNKNOWN;
            this.j = dji.pilot.visual.util.b.a.NON;
            this.k = TrackingMode.g;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a.toString()).append(i.b).append(this.b).append(i.b).append(this.c).append(i.b).append(this.d).append(i.b).append(this.e).append(i.b).append(this.f);
            return stringBuilder.toString();
        }
    }

    public boolean isFinished() {
        return false;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                k();
                break;
            case 4097:
                a(message.arg1 != 0);
                break;
        }
        return false;
    }

    f() {
        this.a = (short) 0;
        this.b = null;
        this.c = null;
        this.d = new a();
        this.e = Integer.MIN_VALUE;
        this.f = false;
        this.g = AutoScrollHelper.NO_MAX;
        this.h = new SparseBooleanArray(4);
        this.i = false;
        this.j = false;
        this.k = false;
        this.s = false;
        this.t = false;
        this.b = new k(this, this);
        this.c = DJIApplication.a();
        this.h.put(TrackingMode.a.a(), g.b(this.c, g.q + TrackingMode.a.toString(), true));
        this.h.put(TrackingMode.b.a(), g.b(this.c, g.q + TrackingMode.b.toString(), true));
        this.h.put(TrackingMode.e.a(), g.b(this.c, g.q + TrackingMode.e.toString(), true));
        this.h.put(TrackingMode.d.a(), g.b(this.c, g.q + TrackingMode.d.toString(), true));
    }

    public boolean a(TrackingMode trackingMode) {
        return this.h.get(trackingMode.a());
    }

    public void a(TrackingMode trackingMode, boolean z) {
        if (this.h.get(trackingMode.a()) != z) {
            this.h.put(trackingMode.a(), z);
            g.a(this.c, g.q + trackingMode.toString(), z);
        }
    }

    private void k() {
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (ServiceManager.getInstance().isRemoteOK() && c.b(c)) {
            this.b.sendEmptyMessageDelayed(4096, 50);
            b k = dji.pilot.fpv.control.k.k();
            short s = (dji.a.a.getInstance().a("gps") || dji.a.a.getInstance().a("network")) ? (short) 1 : (short) 0;
            if (s == (short) 0 || k == null || !k.a() || !k.b()) {
                DataEyeSendUserLocation.getInstance().a(0.0d, 0.0d).a(0.0f, 0.0f).a((short) 0).start(null);
            } else {
                DataEyeSendUserLocation.getInstance().a(Math.toRadians(k.c), Math.toRadians(k.b)).a(0.0f, 0.0f).a((short) ((int) k.e)).start(null);
            }
        }
    }

    private void a(final boolean z) {
        this.b.removeMessages(4097);
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (ServiceManager.getInstance().isRemoteOK() && c.c(c)) {
            new DataSingleVisualParam().a(false).a(ParamCmdId.j).c(z).start(new d(this) {
                final /* synthetic */ f b;

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    int i;
                    k a = this.b.b;
                    k a2 = this.b.b;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    a.sendMessageDelayed(a2.obtainMessage(4097, i, 0), 100);
                }
            });
        }
    }

    private void l() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() != MODE.TAKEPHOTO) {
            DataCameraSetMode.getInstance().a(MODE.TAKEPHOTO).start(new d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    void a(c$b dji_pilot_fpv_flightmode_c_b) {
        if (c$b.TRACK_SELFIE == dji_pilot_fpv_flightmode_c_b) {
            l();
            k();
            a(true);
            this.i = true;
        } else if (this.i) {
            this.i = false;
            a(false);
            this.b.removeMessages(4096);
            this.j = false;
            this.k = false;
            this.s = false;
            this.t = false;
        }
    }

    boolean a() {
        return this.i;
    }

    public boolean b() {
        TrackMode trackMode = this.d.a;
        return this.f && (trackMode == TrackMode.NORMAL || trackMode == TrackMode.DETECT_AFTER_LOST || trackMode == TrackMode.TRACKING || trackMode == TrackMode.WEAK);
    }

    public TrackMode c() {
        return this.d.a;
    }

    public a d() {
        return this.d;
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

    void a(DataEyeGetPushException dataEyeGetPushException) {
        if (a()) {
            boolean isAircraftGpsAbnormal = dataEyeGetPushException.isAircraftGpsAbnormal();
            if (this.j != isAircraftGpsAbnormal) {
                if (isAircraftGpsAbnormal) {
                    DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.visual_track_aircraft_gps_abnormal, 0, DJIErrorPopView.c.a, dji.pilot.fpv.view.DJIErrorPopView.f.a);
                }
                this.j = isAircraftGpsAbnormal;
            }
            isAircraftGpsAbnormal = dataEyeGetPushException.isPhoneGpsAbnormal();
            if (this.k != isAircraftGpsAbnormal) {
                if (isAircraftGpsAbnormal) {
                    DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.visual_track_phone_gps_abnormal, 0, DJIErrorPopView.c.a, dji.pilot.fpv.view.DJIErrorPopView.f.a);
                }
                this.k = isAircraftGpsAbnormal;
            }
            isAircraftGpsAbnormal = dataEyeGetPushException.isGpsTrackingFlusionAbnormal();
            if (this.s != isAircraftGpsAbnormal) {
                if (isAircraftGpsAbnormal) {
                    DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.visual_track_flusion_abnormal, 0, DJIErrorPopView.c.a, dji.pilot.fpv.view.DJIErrorPopView.f.a);
                }
                this.s = isAircraftGpsAbnormal;
            }
            isAircraftGpsAbnormal = dataEyeGetPushException.isGpsTrackingEnable();
            if (isAircraftGpsAbnormal != this.t) {
                if (isAircraftGpsAbnormal) {
                    DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.visual_track_gps_tracking_enable, 0, DJIErrorPopView.c.a, dji.pilot.fpv.view.DJIErrorPopView.f.a);
                } else {
                    DJIErrorPopView.b.b(DJIErrorPopView.d.b, R.string.visual_track_gps_tracking_disable, 0, DJIErrorPopView.c.a, dji.pilot.fpv.view.DJIErrorPopView.f.a);
                }
                this.t = isAircraftGpsAbnormal;
            }
        }
    }

    void a(DataEyeGetPushTrackStatus dataEyeGetPushTrackStatus) {
        TrackMode rectMode = dataEyeGetPushTrackStatus.getRectMode();
        float centerX = dataEyeGetPushTrackStatus.getCenterX();
        float centerY = dataEyeGetPushTrackStatus.getCenterY();
        float width = dataEyeGetPushTrackStatus.getWidth();
        float height = dataEyeGetPushTrackStatus.getHeight();
        TrackException exception = dataEyeGetPushTrackStatus.getException();
        short sessionId = dataEyeGetPushTrackStatus.getSessionId();
        TargetObjType targetType = dataEyeGetPushTrackStatus.getTargetType();
        TrackingMode trackingMode = dataEyeGetPushTrackStatus.getTrackingMode();
        Object obj = !this.f ? 1 : null;
        if (rectMode != TrackMode.LOST) {
            this.f = true;
        }
        if (Integer.MIN_VALUE == this.e) {
            this.e = sessionId;
        }
        if (rectMode == TrackMode.CONFIRM || rectMode == TrackMode.LOST || rectMode == TrackMode.PERSON) {
            this.d.f = exception;
        } else {
            this.d.f = TrackException.NONE;
        }
        DataEyeGetPushException instance = DataEyeGetPushException.getInstance();
        dji.pilot.visual.util.b.a aVar = dji.pilot.visual.util.b.a.NON;
        if (instance.detourDownInTracking()) {
            aVar = dji.pilot.visual.util.b.a.DOWN;
        } else if (instance.detourLeftInTracking()) {
            aVar = dji.pilot.visual.util.b.a.LEFT;
        } else if (instance.detourRightInTracking()) {
            aVar = dji.pilot.visual.util.b.a.RIGHT;
        } else if (instance.detourUpInTracking()) {
            aVar = dji.pilot.visual.util.b.a.UP;
        }
        boolean z = this.d.g != sessionId && this.e == sessionId;
        TrackMode trackMode = this.d.a;
        if (z || this.d.a != rectMode || c.a(centerX, this.d.b) || c.a(centerY, this.d.c) || c.a(width, this.d.d) || exception != this.d.f || c.a(height, this.d.e) || targetType != this.d.i || aVar != this.d.j || trackingMode != this.d.k) {
            this.d.a = rectMode;
            this.d.b = centerX;
            this.d.c = centerY;
            this.d.d = width;
            this.d.e = height;
            this.d.g = sessionId;
            this.d.k = trackingMode;
            this.d.j = aVar;
            this.d.i = targetType;
            this.d.h = z;
            dji.thirdparty.a.c.a().e(this.d);
        }
        if (obj != null || trackMode != rectMode) {
            dji.thirdparty.a.c.a().e(g$d.CTRLMODE_CHANGED);
        }
    }

    private int a(int i) {
        if (i >= 512 || i <= 0) {
            return 1;
        }
        return i + 1;
    }

    public void a(float f, float f2, float f3, float f4) {
        int a = a(this.e);
        this.e = a;
        new DataSingleSetTrackSelect().a(this.a).a(f, f2, f3, f4).a((short) a).start(new d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g = 0.0f;
                this.a.a(null, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.b;
                bVar.b = R.string.visual_track_cmd_failed;
                dji.thirdparty.a.c.a().e(bVar);
            }
        });
    }

    public void a(float f, float f2) {
        float[] a = c.getInstance().a(f, f2, 0.0f, 0.0f);
        new DataSingleMoveTrackSelect().a(MoveCtrlType.a).a(a[0], a[1]).b(this.d.d, this.d.e).start(null);
    }

    public void f() {
        if (dji.pilot.publics.e.a.c(dji.midware.data.manager.P3.i.getInstance().c())) {
            DataCameraSetFocusArea.getInstance().a(this.d.b).b(this.d.c).start(new d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.m();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                    bVar.a = DJIErrorPopView.d.b;
                    bVar.b = R.string.visual_track_start_failed;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            });
        } else {
            m();
        }
    }

    private void m() {
        new DataSingleCtrlTrackSelect().a(TrackCtrlState.b).a((short) (this.d.g != Integer.MIN_VALUE ? this.d.g : 0)).start(new d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                e.getInstance().a();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.b;
                bVar.b = R.string.visual_track_start_failed;
                dji.thirdparty.a.c.a().e(bVar);
            }
        });
    }

    public void g() {
        new DataSingleCtrlTrackSelect().a(TrackCtrlState.a).a((short) (this.d.g != Integer.MIN_VALUE ? this.d.g : 0)).start(new d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.b;
                bVar.b = R.string.visual_track_cancel_failed;
                dji.thirdparty.a.c.a().e(bVar);
            }
        });
    }

    public void h() {
        if (b()) {
            new DataSingleCtrlTrackSelect().a(TrackCtrlState.c).a((short) (this.d.g != Integer.MIN_VALUE ? this.d.g : 0)).start(null);
        }
    }

    void i() {
        a(null, true);
    }

    void j() {
        a(false);
        this.b.removeMessages(4096);
    }

    public void a(TrackingMode trackingMode, final d dVar) {
        if (this.d.k != trackingMode) {
            new DataSingleVisualParam().a(false).a(ParamCmdId.a).a(trackingMode).start(new d(this) {
                final /* synthetic */ f b;

                public void onSuccess(Object obj) {
                    if (dVar != null) {
                        dVar.onSuccess(obj);
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (dVar != null) {
                        dVar.onFailure(aVar);
                    }
                }
            });
        }
    }

    public float a(final d dVar, boolean z) {
        if (AutoScrollHelper.NO_MAX != this.g) {
            return this.g;
        }
        if (z) {
            final DataSingleVisualParam dataSingleVisualParam = new DataSingleVisualParam();
            dataSingleVisualParam.a(true).a(ParamCmdId.h).start(new d(this) {
                final /* synthetic */ f c;

                public void onSuccess(Object obj) {
                    this.c.g = dataSingleVisualParam.f();
                    if (dVar != null) {
                        dVar.onSuccess(obj);
                    }
                    c.a("get tracking circleY=" + this.c.g);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (dVar != null) {
                        dVar.onFailure(aVar);
                    }
                    c.a("get tracking circleY fail=" + aVar);
                }
            });
        }
        return 0.0f;
    }

    public void a(final float f, final d dVar) {
        new DataSingleVisualParam().a(false).a(ParamCmdId.h).c(f).start(new d(this) {
            final /* synthetic */ f c;

            public void onSuccess(Object obj) {
                this.c.g = f;
                dVar.onSuccess(obj);
                c.a("set tracking circleY=" + this.c.g);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dVar.onFailure(aVar);
                c.a("set tracking circleY fail=" + aVar);
            }
        });
    }

    private void b(boolean z) {
        boolean z2 = this.f;
        this.f = false;
        this.d.a();
        this.e = Integer.MIN_VALUE;
        this.g = AutoScrollHelper.NO_MAX;
        if (z && this.f != z2) {
            dji.thirdparty.a.c.a().e(g$d.CTRLMODE_CHANGED);
        }
    }
}
