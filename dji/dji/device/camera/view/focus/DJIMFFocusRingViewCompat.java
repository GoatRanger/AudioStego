package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.widget.DJIRulerView;
import dji.device.widget.LonganPopWarnView;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo$ShotFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusStroke;
import dji.pilot.fpv.R;
import dji.pilot.visual.a.d;
import dji.publics.layout.DJIDragLayout;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

public class DJIMFFocusRingViewCompat extends DJIDragLayout implements OnClickListener {
    protected static final int a = 4097;
    protected static final int b = 4098;
    protected static final int c = 4099;
    protected static final long d = 100;
    protected static final long e = 50;
    protected static final long f = 1000;
    private float A = Float.MIN_VALUE;
    private int B = 0;
    private FuselageFocusMode C = FuselageFocusMode.OTHER;
    private volatile int D = -1;
    private CameraType E = null;
    private FuselageFocusMode F = FuselageFocusMode.OTHER;
    DJIStateImageViewCompat g;
    DJIStateImageViewCompat h;
    int i = -1;
    Context j;
    private DJIRulerView q = null;
    private OnClickListener r = null;
    private dji.device.widget.DJIRulerView.a s = null;
    private dji.device.widget.DJIRulerView.b t = null;
    private OnTouchListener u = null;
    private boolean v = false;
    private b w = null;
    private final DecimalFormat x = new DecimalFormat("#.#");
    private boolean y = false;
    private int z = 0;

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    private static final class b extends Handler {
        private final WeakReference<DJIMFFocusRingViewCompat> a;

        private b(DJIMFFocusRingViewCompat dJIMFFocusRingViewCompat) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIMFFocusRingViewCompat);
        }

        public void handleMessage(Message message) {
            DJIMFFocusRingViewCompat dJIMFFocusRingViewCompat = (DJIMFFocusRingViewCompat) this.a.get();
            if (dJIMFFocusRingViewCompat != null) {
                switch (message.what) {
                    case 4097:
                        if (message.arg1 == 0) {
                            sendEmptyMessageDelayed(4097, DJIMFFocusRingViewCompat.d);
                        }
                        dJIMFFocusRingViewCompat.a(dJIMFFocusRingViewCompat.B);
                        return;
                    case 4098:
                        dJIMFFocusRingViewCompat.v = false;
                        dJIMFFocusRingViewCompat.onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                        return;
                    case 4099:
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIMFFocusRingViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoRotate);
        this.i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AutoRotate_landscapeMargin_Left, -1);
        obtainStyledAttributes.recycle();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        a(getResources().getConfiguration());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        a(getResources().getConfiguration());
        super.onLayout(z, i, i2, i3, i4);
    }

    private void a(Configuration configuration) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (configuration.orientation == 2) {
            setTranslationX((float) (this.i - (getWidth() / 2)));
            setTranslationY((float) ((DJIPreviewActivityLongan.mScreenHeight / 2) - (getHeight() / 2)));
            setRotation(0.0f);
            this.g.setRotation(0.0f);
            this.h.setRotation(0.0f);
            return;
        }
        setTranslationX((float) ((DJIPreviewActivityLongan.mScreenWidth / 2) - (getWidth() / 2)));
        setTranslationY((float) ((DJIPreviewActivityLongan.mScreenHeight - this.i) - (getHeight() / 2)));
        setRotation(-90.0f);
        this.g.setRotation(90.0f);
        this.h.setRotation(90.0f);
    }

    public void showView() {
        if (!isShown()) {
            this.z = DJICameraDataManagerCompat.getInstance().getDemarcateValue();
            c.a().e(a.SHOW);
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            show();
        }
    }

    public void show() {
        if (dji.logic.f.b.a(this.E) && !DJIPreviewActivityLongan.isHideAll && a(this.C)) {
            super.show();
        }
    }

    public void hideView() {
        if (isShown()) {
            this.D = -1;
            this.w.removeMessages(4097);
            c.a().e(a.HIDE);
            go();
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            this.C = FuselageFocusMode.OTHER;
            this.D = -1;
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.C = FuselageFocusMode.OTHER;
        }
    }

    public void onEventMainThread(dji.device.camera.datamanager.DJICameraDataManagerCompat.a aVar) {
        if (dji.device.camera.datamanager.DJICameraDataManagerCompat.a.CAMERAINFO_GETTED == aVar && isShown()) {
            int demarcateValue = DJICameraDataManagerCompat.getInstance().getDemarcateValue();
            if (this.z != demarcateValue) {
                this.z = demarcateValue;
                this.q.setMaxSize(DataCameraGetPushShotInfo.getInstance().getShotFocusMaxStroke() - demarcateValue);
                int shotFocusCurStroke = DataCameraGetPushShotInfo.getInstance().getShotFocusCurStroke();
                if (!this.v) {
                    if (this.D == -1 || this.D == shotFocusCurStroke) {
                        this.q.setCurSize(shotFocusCurStroke - demarcateValue);
                        this.B = shotFocusCurStroke;
                        this.D = -1;
                    }
                }
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.isGetted()) {
            CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
            if (cameraType != this.E) {
                this.E = cameraType;
                showView();
            }
        }
    }

    private boolean a(FuselageFocusMode fuselageFocusMode) {
        if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
            return true;
        }
        return false;
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
        if (fuselageFocusMode != this.C) {
            this.C = fuselageFocusMode;
            if (a(fuselageFocusMode)) {
                show();
            } else {
                go();
            }
        }
        if (isShown()) {
            this.q.setMaxSize(dataCameraGetPushShotInfo.getShotFocusMaxStroke() - this.z);
            int shotFocusCurStroke = dataCameraGetPushShotInfo.getShotFocusCurStroke();
            if (!(this.B == shotFocusCurStroke || this.v || (this.D != -1 && this.D != shotFocusCurStroke))) {
                this.q.setCurSize(shotFocusCurStroke - this.z);
                this.B = shotFocusCurStroke;
                this.D = -1;
            }
            float objDistance = dataCameraGetPushShotInfo.getObjDistance();
            if (objDistance != this.A) {
                this.A = objDistance;
            }
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.HIDE) {
            showView();
        } else if (aVar == dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.NEEDSHOW) {
            hideView();
        }
    }

    private float a(float f) {
        if (f < d.c) {
            return d.c;
        }
        if (f < 1.0f) {
            return 1.0f;
        }
        if (((double) f) < 1.5d) {
            return dji.midware.util.a.b.c;
        }
        if (f < 2.0f) {
            return 2.0f;
        }
        if (f < 3.0f) {
            return 3.0f;
        }
        if (f < 5.0f) {
            return 5.0f;
        }
        if (f < 10.0f) {
            return 10.0f;
        }
        return f < 20.0f ? 20.0f : AutoScrollHelper.NO_MAX;
    }

    private void a() {
        this.w = new b();
        this.r = new OnClickListener(this) {
            final /* synthetic */ DJIMFFocusRingViewCompat a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.camera_focus_ring_max_img == id) {
                    this.a.q.stepPrev();
                } else if (R.id.camera_focus_ring_zero_img == id) {
                    this.a.q.stepNext();
                }
            }
        };
        this.u = new OnTouchListener(this) {
            final /* synthetic */ DJIMFFocusRingViewCompat a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.isEnabled()) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        this.a.y = false;
                        view.setPressed(true);
                        this.a.w.sendMessageDelayed(this.a.w.obtainMessage(4099, view), 1000);
                    } else if (action == 1) {
                        view.setPressed(false);
                        if (!this.a.y) {
                            this.a.w.removeMessages(4099);
                        }
                    } else if (action == 3) {
                        view.setPressed(false);
                        this.a.w.removeMessages(4099);
                    }
                }
                return true;
            }
        };
        this.s = new dji.device.widget.DJIRulerView.a(this) {
            final /* synthetic */ DJIMFFocusRingViewCompat a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView, int i, int i2, boolean z) {
                this.a.B = this.a.z + i;
                if (!z && !this.a.v) {
                    this.a.w.removeMessages(4097);
                    this.a.w.sendMessageDelayed(this.a.w.obtainMessage(4097, 1, 0), 50);
                }
            }
        };
        this.t = new dji.device.widget.DJIRulerView.b(this) {
            final /* synthetic */ DJIMFFocusRingViewCompat a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView) {
                if (DataCameraGetPushShotInfo.getInstance().getShotFocusMode() == DataCameraGetPushShotInfo$ShotFocusMode.Manual) {
                    LonganPopWarnView.getInstance(this.a.getContext()).pop(R.drawable.longan_notice, R.string.fpv_shottype_mf_tip, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
                    return;
                }
                this.a.w.removeMessages(4098);
                this.a.v = true;
                this.a.w.sendEmptyMessageDelayed(4097, DJIMFFocusRingViewCompat.d);
                if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD && dji.device.common.a.a.a()) {
                    LonganPopWarnView.getInstance(this.a.j).pop(R.drawable.longan_notice, R.string.fpv_cant_focus_aid_tip, dji.device.widget.LonganPopWarnView.a.LENGTH_LONG);
                }
            }

            public void b(DJIRulerView dJIRulerView) {
                this.a.w.removeMessages(4097);
                this.a.a(dJIRulerView.getCurSize() + this.a.z);
                this.a.w.sendEmptyMessageDelayed(4098, 50);
                this.a.b();
            }
        };
    }

    private void a(final int i) {
        if (dji.device.camera.view.focus.a.c.getInstance().b() == 1) {
            dji.device.camera.view.focus.a.c.getInstance().a(1);
        } else {
            new DataCameraSetFocusStroke().a(i).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIMFFocusRingViewCompat b;

                public void onSuccess(Object obj) {
                    this.b.D = i;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
        if (!isInEditMode()) {
            this.q = (DJIRulerView) findViewById(R.id.camera_focus_ring_ruler);
            this.g = (DJIStateImageViewCompat) findViewById(R.id.longan_focal_min_iv);
            this.g.setOnClickListener(this);
            this.h = (DJIStateImageViewCompat) findViewById(R.id.longan_focal_max_iv);
            this.h.setOnClickListener(this);
            this.q.setOnChangeListener(this.s);
            this.q.setOnScrollListener(this.t);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isShown()) {
            return super.onTouchEvent(motionEvent);
        }
        int x = (int) motionEvent.getX();
        int width = getWidth();
        if (width - this.q.getWidth() > x || x > width) {
            return false;
        }
        return true;
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.EXIT_SETTING) {
            show();
        } else if (mVar == m.SHOW_MENU || mVar == m.SHOW_PASM) {
            hide();
        } else if (mVar == m.HIDE_ALL) {
            hide();
        } else if (mVar == m.SHOW_ALL) {
            show();
        }
    }

    private void b() {
        boolean z;
        boolean z2 = true;
        DJIStateImageViewCompat dJIStateImageViewCompat = this.g;
        if (this.q.getCurSize() != this.z) {
            z = true;
        } else {
            z = false;
        }
        dJIStateImageViewCompat.setEnabled(z);
        DJIStateImageViewCompat dJIStateImageViewCompat2 = this.h;
        if (this.q.getCurSize() == this.q.getMaxSize()) {
            z2 = false;
        }
        dJIStateImageViewCompat2.setEnabled(z2);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_focal_min_iv) {
            a(this.z);
        } else if (id == R.id.longan_focal_max_iv) {
            a(this.q.getMaxSize());
        }
    }
}
