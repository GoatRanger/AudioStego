package dji.pilot.newfpv.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycGetIoc.MODE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.pilot.R;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.view.DJIGimbalYawView;
import dji.pilot.publics.widget.DJIClipView;
import dji.pilot.visual.view.DJIVisualObstacleRadarView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.d.a;
import lecho.lib.hellocharts.model.l;

public class AttitudeBallView extends DJIRelativeLayout implements d {
    private static final int[] E = new int[]{5, 26, 78, 100};
    private static final int[] F = new int[]{30, 75, 270, 330};
    private static final int[] G = new int[]{0, 0, 1, 1};
    private final int A = 256;
    private final int B = 257;
    private final long C = 200;
    private DJIClipView D;
    private float H = 32767.0f;
    private Handler I = new Handler(new Callback(this) {
        final /* synthetic */ AttitudeBallView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 256:
                    this.a.d();
                    break;
                case 257:
                    this.a.h();
                    break;
            }
            return false;
        }
    });
    private DJIRelativeLayout a;
    private ProgressBar b;
    private DJIVisualObstacleRadarView c;
    private DJIGimbalYawView d;
    private DJIImageView e;
    private DJIImageView f;
    private DJIImageView g;
    private DJIImageView h;
    private DJIImageView i;
    private ImageView j;
    private float k = AutoScrollHelper.NO_MAX;
    private double l = Double.MAX_VALUE;
    private float m = AutoScrollHelper.NO_MAX;
    private Drawable n = null;
    private Drawable o = null;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private boolean s = false;
    private double t = 0.0d;
    private double u = 0.0d;
    private final float v = 0.0f;
    private final int w = 400;
    private final int x = 2000;
    private final float y = l.n;
    private boolean z = false;

    public AttitudeBallView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIRelativeLayout) findViewById(R.id.ts);
            this.b = (ProgressBar) findViewById(R.id.tt);
            this.c = (DJIVisualObstacleRadarView) findViewById(R.id.tu);
            this.c.setPathEffect();
            this.d = (DJIGimbalYawView) findViewById(R.id.tv);
            this.e = (DJIImageView) findViewById(R.id.tx);
            this.f = (DJIImageView) findViewById(R.id.ty);
            this.g = (DJIImageView) findViewById(R.id.u0);
            this.h = (DJIImageView) findViewById(R.id.u1);
            this.i = (DJIImageView) findViewById(R.id.u2);
            this.j = (ImageView) findViewById(R.id.tz);
            Resources resources = getContext().getResources();
            this.o = resources.getDrawable(R.drawable.osd_attitude_aircraft);
            this.n = resources.getDrawable(R.drawable.osd_attitude_gimbal);
            this.p = resources.getDimensionPixelSize(R.dimen.iq);
            a();
        }
    }

    private void a() {
        a(90.0f, 0.0f);
        b(0.0f, 0.0f);
        this.d.setYaw(0.0f);
        a(MODE.OTHER, 0);
        this.k = AutoScrollHelper.NO_MAX;
        this.l = Double.MAX_VALUE;
        this.m = AutoScrollHelper.NO_MAX;
        b();
        c();
    }

    private void b() {
        this.s = false;
        this.t = 0.0d;
        this.u = 0.0d;
    }

    private void c() {
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.addRule(13);
        this.i.setLayoutParams(layoutParams);
        this.i.setRotation(0.0f);
        this.i.setScaleX(1.0f);
        this.i.setScaleY(1.0f);
        layoutParams = (LayoutParams) this.h.getLayoutParams();
        layoutParams.addRule(13, 0);
        layoutParams.leftMargin = (this.p - this.n.getIntrinsicWidth()) / 2;
        layoutParams.topMargin = (this.p / 2) - this.n.getIntrinsicHeight();
        this.h.setLayoutParams(layoutParams);
        this.h.setRotation(0.0f);
        this.h.setScaleX(1.0f);
        this.h.setScaleY(1.0f);
        layoutParams = (LayoutParams) this.g.getLayoutParams();
        layoutParams.addRule(13);
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = 0;
        this.g.setLayoutParams(layoutParams);
        this.g.go();
        layoutParams = (LayoutParams) this.f.getLayoutParams();
        layoutParams.addRule(13);
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = 0;
        this.f.setLayoutParams(layoutParams);
        this.f.show();
    }

    public void onValueChange(c cVar, a aVar, a aVar2) {
        if (!this.I.hasMessages(256)) {
            d();
            this.I.sendEmptyMessageDelayed(256, 200);
        }
    }

    private void d() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        a((float) (90.0d - dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.X))), (float) dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.Y)));
        float a = (float) (dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.g(f.d)) * 10);
        this.d.setYaw(a);
        double d = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.al));
        double d2 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.am));
        double d3 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.R));
        double d4 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.S));
        if (b.a(d) && b.b(d2) && b.a(d3) && b.b(d4)) {
            float[] c = b.c(d, d2, d3, d4);
            float f6 = c[0];
            float f7 = c[1];
            if (this.s && e() && b.a(this.t) && b.b(this.u)) {
                c = b.c(this.t, this.u, d3, d4);
                float f8 = c[0];
                f = c[1];
                f2 = f7;
                f3 = f6;
                f4 = f8;
            } else {
                f2 = f7;
                f3 = f6;
                f4 = 0.0f;
                f = 0.0f;
            }
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
            f4 = 0.0f;
            f = 0.0f;
        }
        if (f2 < f) {
            f5 = f;
        } else {
            f5 = f2;
        }
        if (f5 < 400.0f) {
            f5 = 400.0f;
        }
        double d5 = dji.sdksharedlib.a.a.d(dji.sdksharedlib.a.a.e(e.Z));
        b((float) (d5 - ((double) this.r)), f5);
        a(f4, f, f3, f2, f5, (float) d5, (float) ((((double) a) + d5) + 180.0d));
    }

    private void a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        int measuredWidth = this.a.getMeasuredWidth();
        if (measuredWidth > 0) {
            int round;
            int round2;
            double d;
            this.f.show();
            LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
            layoutParams.addRule(13);
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 0;
            this.f.setLayoutParams(layoutParams);
            if (this.s && e()) {
                this.g.show();
                layoutParams = (LayoutParams) this.g.getLayoutParams();
                layoutParams.addRule(13, 0);
                round = Math.round(((float) measuredWidth) * 0.0f);
                round2 = Math.round(((float) ((measuredWidth - round) - this.g.getWidth())) / 2.0f);
                int round3 = Math.round(((float) ((measuredWidth - round) - this.g.getHeight())) / 2.0f);
                round = Math.round(((float) round) / 2.0f);
                double toRadians = Math.toRadians((double) (((float) this.r) + f));
                if (f2 == f5) {
                    layoutParams.leftMargin = (int) (((double) (round + round2)) + (Math.cos(toRadians) * ((double) round2)));
                    layoutParams.topMargin = (int) (((double) (round + round3)) - (((double) round3) * Math.sin(toRadians)));
                } else {
                    layoutParams.leftMargin = (int) (((double) (round + round2)) + (((((double) f2) * Math.cos(toRadians)) * ((double) round2)) / ((double) f5)));
                    d = (double) round3;
                    layoutParams.topMargin = (int) (((double) (round + round3)) - ((d * (Math.sin(toRadians) * ((double) f2))) / ((double) f5)));
                }
                this.g.setLayoutParams(layoutParams);
            } else {
                this.g.go();
            }
            float a = a(f4);
            this.i.setScaleX(a);
            this.i.setScaleY(a);
            this.h.setScaleX(a);
            this.h.setScaleY(a);
            if (!this.z) {
                this.h.setPivotY(this.h.getPivotY() * 2.0f);
                this.z = true;
            }
            if (a(f4, f5, (double) (((float) this.r) + f3))) {
                layoutParams = (LayoutParams) this.i.getLayoutParams();
                layoutParams.addRule(13, 0);
                round = Math.round(((float) measuredWidth) * 0.0f);
                round2 = Math.round(((float) ((measuredWidth - round) - this.o.getIntrinsicWidth())) / 2.0f);
                measuredWidth = Math.round(((float) ((measuredWidth - round) - this.o.getIntrinsicHeight())) / 2.0f);
                round = Math.round(((float) round) / 2.0f);
                d = Math.toRadians((double) (((float) this.r) + f3));
                if (f4 >= f5) {
                    layoutParams.leftMargin = (int) (((double) (round + round2)) + (Math.cos(d) * ((double) round2)));
                    layoutParams.topMargin = (int) (((double) (round + measuredWidth)) - (Math.sin(d) * ((double) measuredWidth)));
                } else {
                    layoutParams.leftMargin = (int) (((double) (round + round2)) + (((((double) f4) * Math.cos(d)) * ((double) round2)) / ((double) f5)));
                    layoutParams.topMargin = (int) (((double) (round + measuredWidth)) - (((Math.sin(d) * ((double) f4)) * ((double) measuredWidth)) / ((double) f5)));
                }
                this.i.setLayoutParams(layoutParams);
                this.j.setX((this.i.getX() + (((float) this.o.getIntrinsicWidth()) / 2.0f)) - (((float) this.j.getWidth()) / 2.0f));
                this.j.setY((this.i.getY() + (((float) this.o.getIntrinsicHeight()) / 2.0f)) - (((float) this.j.getHeight()) / 2.0f));
                LayoutParams layoutParams2 = (LayoutParams) this.h.getLayoutParams();
                layoutParams2.addRule(13, 0);
                layoutParams2.leftMargin = ((layoutParams.leftMargin + (this.o.getIntrinsicWidth() / 2)) - (this.n.getIntrinsicWidth() / 2)) - 1;
                layoutParams2.topMargin = ((layoutParams.topMargin + (this.o.getIntrinsicHeight() / 2)) - this.n.getIntrinsicHeight()) - 2;
                this.h.setLayoutParams(layoutParams2);
            }
            this.h.setRotation((f7 - ((float) this.r)) + 180.0f);
            this.i.setRotation(f6 - ((float) this.r));
        }
    }

    private boolean a(float f, float f2, double d) {
        if (Math.abs(this.k - f) <= 10.0f && Math.abs(this.m - f2) <= 10.0f && Math.abs(this.l - d) <= 0.1d) {
            return false;
        }
        this.k = f;
        this.m = f2;
        this.l = d;
        return true;
    }

    private float a(float f) {
        if (f >= 2000.0f) {
            return l.n;
        }
        return f > 400.0f ? (l.n * ((2000.0f - f) / 1600.0f)) + 0.39999998f : 1.0f;
    }

    private boolean e() {
        return b.a(null);
    }

    private void a(float f, float f2) {
        int i = 100;
        int i2 = (int) ((DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * f) / 180.0f);
        if (i2 < 0) {
            i = 0;
        } else if (i2 <= 100) {
            i = i2;
        }
        if (this.b.getProgress() != i) {
            this.b.setProgress(i);
        }
        this.b.setRotation(f2);
    }

    private void b(float f, float f2) {
        this.c.setDistance(f2);
    }

    private void f() {
        dji.gs.e.b k = k.k();
        if (k != null && b.a(k.b) && b.b(k.c)) {
            this.s = true;
            this.t = k.b;
            this.u = k.c;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (isShown()) {
                g();
            }
            dji.thirdparty.a.c.a().a(this);
            f();
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!isInEditMode()) {
            if (i == 0) {
                g();
            } else {
                dji.sdksharedlib.a.a.a(this);
            }
        }
    }

    protected void onDetachedFromWindow() {
        dji.thirdparty.a.c.a().d(this);
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void g() {
        dji.sdksharedlib.a.a.g(this, new String[]{e.X, e.Y, e.Z, e.al, e.am, e.R, e.S});
        dji.sdksharedlib.a.a.c(this, new String[]{f.d});
    }

    public void onEventBackgroundThread(float[] fArr) {
        int i = 0;
        int toDegrees = (int) Math.toDegrees((double) fArr[0]);
        if (toDegrees - this.q > 2 || this.q - toDegrees > 2) {
            i = 1;
        }
        if (i != 0) {
            this.q = toDegrees;
            if (dji.pilot.fpv.model.b.c(getContext()) == 3) {
                toDegrees += 180;
            }
            this.r = toDegrees + 90;
            this.I.sendEmptyMessage(256);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            this.I.removeMessages(257);
        } else if (oVar == o.a) {
            this.I.removeMessages(256);
            this.I.sendEmptyMessageDelayed(257, 200);
        }
    }

    public void onEventMainThread(dji.gs.e.b bVar) {
        if (bVar != null && b.a(bVar.b) && b.b(bVar.c)) {
            this.s = true;
            this.t = bVar.b;
            this.u = bVar.c;
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        if (flycState == FLYC_STATE.Atti_CL || flycState == FLYC_STATE.GPS_CL || flycState == FLYC_STATE.TRACK_HEADLOCK) {
            float toDegrees = (float) Math.toDegrees((double) ((((float) Short.valueOf(dji.sdksharedlib.a.a.f(dji.sdksharedlib.a.a.e(e.aF))).shortValue()) * 1.0f) / 1000.0f));
            this.j.setVisibility(0);
            this.j.setRotation(toDegrees - ((float) this.r));
            return;
        }
        this.j.setVisibility(4);
    }

    private void a(MODE mode, int i) {
        boolean c = dji.pilot.groundStation.a.a.getInstance(getContext()).c();
        dji.pilot.groundStation.a.a.d z = dji.pilot.groundStation.a.a.getInstance(getContext()).z();
        if (c && z == dji.pilot.groundStation.a.a.d.COURSE_LOCK && mode == MODE.CourseLock) {
            float toDegrees = (float) Math.toDegrees((double) ((((float) i) * 1.0f) / 1000.0f));
            this.e.show();
            this.e.setRotation(toDegrees - ((float) this.r));
            return;
        }
        this.e.go();
    }

    private void h() {
        this.I.removeMessages(257);
        a();
    }
}
