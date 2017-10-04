package dji.pilot.fpv.camera.roi;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraTauAreaAxis;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.c;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIAreaMeasureView extends DJIRelativeLayout implements a {
    private static final int H = 4096;
    private static final long I = 50;
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 20;
    private float A = AutoScrollHelper.NO_MAX;
    private int B = Integer.MIN_VALUE;
    private int C = Integer.MIN_VALUE;
    private int D = Integer.MIN_VALUE;
    private int E = Integer.MIN_VALUE;
    private int F = Integer.MAX_VALUE;
    private k G = null;
    private Callback J = new Callback(this) {
        final /* synthetic */ DJIAreaMeasureView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    DataCameraGetPushTauParam instance = DataCameraGetPushTauParam.getInstance();
                    if (this.a.q == 0 && !this.a.a(instance.getAreaThermometricLeft(), instance.getAreaThermometricTop(), instance.getAreaThermometricRight(), instance.getAreaThermometricBottom())) {
                        this.a.e();
                        break;
                    }
            }
            return false;
        }
    };
    private DJIAreaRoiView e = null;
    private DJIImageView f = null;
    private DJIImageView g = null;
    private DJILinearLayout h = null;
    private DJITextView i = null;
    private DJILinearLayout j = null;
    private DJITextView k = null;
    private DJITextView l = null;
    private DJITextView m = null;
    private final Context n;
    private OnClickListener o = null;
    private final c p = dji.pilot.fpv.camera.more.a.getInstance().aH();
    private int q = 0;
    private float r = 0.0f;
    private float s = 0.0f;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private boolean w = true;
    private Rect x = new Rect();
    private float y = AutoScrollHelper.NO_MAX;
    private float z = AutoScrollHelper.NO_MAX;

    public DJIAreaMeasureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = context;
        this.G = new k(this, this.J);
        Drawable drawable = context.getResources().getDrawable(R.drawable.camera_roi_max_temp);
        this.u = drawable.getIntrinsicHeight();
        this.t = drawable.getIntrinsicWidth();
        this.v = this.t / 3;
    }

    public void setMapMode(boolean z) {
        this.w = z;
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        a(false, dataCameraGetPushTauParam);
    }

    public void onEventMainThread(c.a aVar) {
        if (c.a.TEMPALERT_CHANGED == aVar) {
            a();
        } else if (c.a.TEMPALERT_THRESHOLD_CHANGED == aVar) {
            a();
        }
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (DJIGenSettingDataManager$b.TEMPERATURE_UNIT_CHANGED == dJIGenSettingDataManager$b) {
            if (this.i.getVisibility() == 0) {
                this.i.setText(b.a(this.n, "%.0f", (float) this.F, true));
            }
            a(true, DataCameraGetPushTauParam.getInstance());
        }
    }

    public void onEventMainThread(p pVar) {
        if (p.a == pVar) {
            b();
        }
    }

    public void onEventMainThread(o oVar) {
        if (o.a == oVar) {
            b();
        }
    }

    private void a() {
        boolean b = this.p.b();
        if (b) {
            this.F = this.p.c();
            this.i.show();
            this.i.setText(b.a(this.n, "%.0f", (float) this.F, true));
            this.i.setBackgroundResource(R.drawable.left_corner_halfblack_bg);
            this.j.setBackgroundResource(R.drawable.right_corner_halfblack_bg);
        } else {
            this.i.go();
            this.i.setBackgroundResource(0);
            this.j.setBackgroundResource(0);
            this.F = Integer.MAX_VALUE;
            DJIErrorPopView.b.b(d.b, R.string.tau_high_temp_title, R.string.tau_high_temp_desc, DJIErrorPopView.c.c, f.b);
        }
        DataCameraGetPushTauParam instance = DataCameraGetPushTauParam.getInstance();
        ThermometricType thermometricType = instance.getThermometricType();
        if (instance.isGetted() && ThermometricType.c == thermometricType && b) {
            if (this.y == AutoScrollHelper.NO_MAX) {
                this.y = instance.getAreaThermometricMax();
                this.k.setText(b.a(this.n, "%.0f", this.y, true));
            }
            if (this.y >= ((float) this.F)) {
                this.i.setBackgroundResource(R.drawable.left_corner_red_bg);
                DJIErrorPopView.b.b(d.b, R.string.tau_high_temp_title, R.string.tau_high_temp_desc, DJIErrorPopView.c.c, f.a);
                return;
            }
            this.i.setBackgroundResource(R.drawable.left_corner_halfblack_bg);
        }
    }

    private boolean a(int i, int i2, int i3, int i4) {
        return i == this.x.left && this.x.top == i2 && this.x.right == i3 && this.x.bottom == i4;
    }

    private void a(boolean z, DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        if (ThermometricType.c == dataCameraGetPushTauParam.getThermometricType()) {
            show();
            int areaThermometricLeft = dataCameraGetPushTauParam.getAreaThermometricLeft();
            int areaThermometricTop = dataCameraGetPushTauParam.getAreaThermometricTop();
            int areaThermometricRight = dataCameraGetPushTauParam.getAreaThermometricRight();
            int areaThermometricBottom = dataCameraGetPushTauParam.getAreaThermometricBottom();
            int width = getWidth();
            int height = getHeight();
            boolean z2 = false;
            if (this.x.isEmpty() || z) {
                this.x.set(areaThermometricLeft, areaThermometricTop, areaThermometricRight, areaThermometricBottom);
                if (!this.x.isEmpty()) {
                    this.e.updateParam((((float) (areaThermometricLeft * width)) * 1.0f) / 10000.0f, (((float) (areaThermometricTop * height)) * 1.0f) / 10000.0f, (int) ((((float) ((areaThermometricRight - areaThermometricLeft) * width)) * 1.0f) / 10000.0f), (int) ((((float) ((areaThermometricBottom - areaThermometricTop) * height)) * 1.0f) / 10000.0f));
                    z2 = true;
                }
            } else {
                z2 = a(areaThermometricLeft, areaThermometricTop, areaThermometricRight, areaThermometricBottom);
            }
            if (z2 && dataCameraGetPushTauParam.isThermometricValid()) {
                float f;
                float f2;
                RectF localRect = this.e.getLocalRect();
                localRect.inset((float) (-this.v), (float) (-this.v));
                areaThermometricLeft = dataCameraGetPushTauParam.getAreaThermometricMinX();
                areaThermometricTop = dataCameraGetPushTauParam.getAreaThermometricMinY();
                if (!(this.B == areaThermometricLeft && this.C == areaThermometricTop && !z)) {
                    this.B = areaThermometricLeft;
                    this.C = areaThermometricTop;
                    f = (((float) (areaThermometricLeft * width)) * 1.0f) / 10000.0f;
                    f2 = (((float) (areaThermometricTop * height)) * 1.0f) / 10000.0f;
                    if (localRect.contains(f, f2)) {
                        this.g.show();
                        this.g.setX(f - ((float) (this.t / 2)));
                        this.g.setY(f2 - ((float) (this.u / 2)));
                    }
                }
                areaThermometricLeft = dataCameraGetPushTauParam.getAreaThermometricMaxX();
                areaThermometricTop = dataCameraGetPushTauParam.getAreaThermometricMaxY();
                if (!(this.D == areaThermometricLeft && this.E == areaThermometricTop && !z)) {
                    this.D = areaThermometricLeft;
                    this.E = areaThermometricTop;
                    f = (((float) (areaThermometricLeft * width)) * 1.0f) / 10000.0f;
                    f2 = (((float) (areaThermometricTop * height)) * 1.0f) / 10000.0f;
                    if (localRect.contains(f, f2)) {
                        this.f.show();
                        this.f.setX(f - ((float) (this.t / 2)));
                        this.f.setY(f2 - ((float) (this.u / 2)));
                    }
                }
                float areaThermometricMax = dataCameraGetPushTauParam.getAreaThermometricMax();
                if (a(this.y, areaThermometricMax) || z) {
                    this.y = areaThermometricMax;
                    this.k.setText(b.a(this.n, "%.0f", areaThermometricMax, true));
                    if (areaThermometricMax >= ((float) this.F)) {
                        this.i.setBackgroundResource(R.drawable.left_corner_red_bg);
                        DJIErrorPopView.b.b(d.b, R.string.tau_high_temp_title, R.string.tau_high_temp_desc, DJIErrorPopView.c.c, f.a);
                    } else if (this.p.b()) {
                        this.i.setBackgroundResource(R.drawable.left_corner_halfblack_bg);
                    }
                }
                areaThermometricMax = dataCameraGetPushTauParam.getAreaThermometricMin();
                if (a(this.z, areaThermometricMax) || z) {
                    this.z = areaThermometricMax;
                    this.l.setText(b.a(this.n, "%.0f", areaThermometricMax, true));
                }
                areaThermometricMax = dataCameraGetPushTauParam.getAreaThermometricAverage();
                if (a(this.A, areaThermometricMax) || z) {
                    this.A = areaThermometricMax;
                    this.m.setText(b.a(this.n, "%.0f", areaThermometricMax, true));
                    return;
                }
                return;
            }
            return;
        }
        b();
    }

    private void b() {
        this.x.setEmpty();
        this.y = AutoScrollHelper.NO_MAX;
        this.z = AutoScrollHelper.NO_MAX;
        this.A = AutoScrollHelper.NO_MAX;
        this.B = Integer.MIN_VALUE;
        this.C = Integer.MIN_VALUE;
        this.D = Integer.MIN_VALUE;
        this.E = Integer.MIN_VALUE;
        this.f.go();
        this.g.go();
        this.e.go();
        go();
    }

    private boolean a(float f, float f2) {
        return ((double) Math.abs(f2 - f)) >= 0.5d;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            c();
            this.e = (DJIAreaRoiView) findViewById(R.id.jt);
            this.f = (DJIImageView) findViewById(R.id.ju);
            this.g = (DJIImageView) findViewById(R.id.jv);
            this.h = (DJILinearLayout) findViewById(R.id.jw);
            this.i = (DJITextView) findViewById(R.id.jx);
            this.j = (DJILinearLayout) findViewById(R.id.jy);
            this.k = (DJITextView) findViewById(R.id.k1);
            this.l = (DJITextView) findViewById(R.id.k4);
            this.m = (DJITextView) findViewById(R.id.k7);
            this.e.setExpandListener(this.o);
        }
    }

    private void c() {
        this.o = new OnClickListener(this) {
            final /* synthetic */ DJIAreaMeasureView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (R.id.k8 == view.getId()) {
                    this.a.e.updateLocation(0.0f, 0.0f, (float) this.a.getWidth(), (float) this.a.getHeight());
                    this.a.f.go();
                    this.a.g.go();
                    this.a.e();
                }
            }
        };
    }

    private boolean d() {
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        boolean z = DataCameraGetPushTauParam.getInstance().getThermometricType() == ThermometricType.c;
        if (ServiceManager.getInstance().isRemoteOK() && this.w && z && mode != MODE.PLAYBACK && mode != MODE.NEW_PLAYBACK) {
            return true;
        }
        return false;
    }

    private void a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (1 == this.q) {
            this.f.go();
            this.g.go();
            this.e.dragByDelta(x - this.r, y - this.s, getWidth(), getHeight());
            this.r = x;
            this.s = y;
        } else if (2 == this.q) {
            this.e.updateLocation(this.r, this.s, x, y);
        } else if (this.q == 0) {
            float abs = Math.abs(x - this.r);
            float abs2 = Math.abs(y - this.s);
            if (abs >= ((float) DJIAreaRoiView.a) && abs2 >= ((float) DJIAreaRoiView.b)) {
                this.q = 2;
                this.f.go();
                this.g.go();
                this.e.updateLocation(this.r, this.s, x, y);
            }
        }
    }

    private void b(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (1 == this.q) {
            this.e.dragByDelta(x - this.r, y - this.s, getWidth(), getHeight());
            e();
        } else if (2 == this.q) {
            this.e.updateLocation(this.r, this.s, x, y);
            e();
        }
    }

    private void e() {
        RectF localRect = this.e.getLocalRect();
        int width = getWidth();
        int height = getHeight();
        this.x.setEmpty();
        this.x.left = (int) ((localRect.left * 10000.0f) / ((float) width));
        this.x.top = (int) ((localRect.top * 10000.0f) / ((float) height));
        this.x.right = (int) ((localRect.right * 10000.0f) / ((float) width));
        this.x.bottom = (int) ((localRect.bottom * 10000.0f) / ((float) height));
        new DataCameraTauAreaAxis().a((short) this.x.left, (short) this.x.top, (short) this.x.right, (short) this.x.bottom).b(false).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIAreaMeasureView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.G.sendEmptyMessageDelayed(4096, 50);
            }
        });
    }

    private boolean f() {
        return this.x.left <= 20 && this.x.top <= 20 && this.x.right >= getWidth() - 20 && this.x.bottom >= getHeight() - 20;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!d()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.r = motionEvent.getX();
                this.s = motionEvent.getY();
                if (this.e.pointInView2(this.r, this.s) && !f()) {
                    this.q = 1;
                    break;
                }
            case 1:
            case 3:
                b(motionEvent);
                this.q = 0;
                break;
            case 2:
                a(motionEvent);
                break;
        }
        return true;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a(true, DataCameraGetPushTauParam.getInstance());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (DataCameraGetPushTauParam.getInstance().isGetted()) {
                onEventMainThread(DataCameraGetPushTauParam.getInstance());
            }
            a();
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
        }
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    public boolean isFinished() {
        return ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushTauParam.getInstance().getThermometricType() == ThermometricType.c;
    }
}
