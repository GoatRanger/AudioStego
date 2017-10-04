package dji.pilot.fpv.camera.focus;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusStroke;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.camera.widget.DJIRulerView;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.e.e;
import dji.pilot.usercenter.protocol.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

public class DJIFocusRingView extends DJILinearLayout {
    protected static final int a = 4097;
    protected static final int b = 4098;
    protected static final int c = 4099;
    protected static final int d = 4100;
    protected static final long e = 100;
    protected static final long f = 100;
    protected static final long g = 1000;
    protected static final long h = 1500;
    private CameraType A = CameraType.OTHER;
    private volatile int B = -1;
    private DJILinearLayout i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DJIImageView l = null;
    private DJIImageView m = null;
    private DJIRulerView n = null;
    private OnClickListener o = null;
    private dji.pilot.fpv.camera.widget.DJIRulerView.a p = null;
    private dji.pilot.fpv.camera.widget.DJIRulerView.b q = null;
    private OnTouchListener r = null;
    private boolean s = false;
    private b t = null;
    private final DecimalFormat u = new DecimalFormat("#.#");
    private boolean v = false;
    private int w = 0;
    private float x = Float.MIN_VALUE;
    private int y = 0;
    private FuselageFocusMode z = FuselageFocusMode.OTHER;

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    private static final class b extends Handler {
        private final WeakReference<DJIFocusRingView> a;

        private b(DJIFocusRingView dJIFocusRingView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFocusRingView);
        }

        public void handleMessage(Message message) {
            DJIFocusRingView dJIFocusRingView = (DJIFocusRingView) this.a.get();
            if (dJIFocusRingView != null) {
                switch (message.what) {
                    case 4097:
                        if (message.arg1 == 0) {
                            sendEmptyMessageDelayed(4097, 100);
                        }
                        dJIFocusRingView.b(dJIFocusRingView.y);
                        return;
                    case 4098:
                        dJIFocusRingView.s = false;
                        dJIFocusRingView.onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                        return;
                    case 4099:
                        if (message.obj != dJIFocusRingView.l && message.obj == dJIFocusRingView.m) {
                            return;
                        }
                        return;
                    case 4100:
                        if (!dJIFocusRingView.s) {
                            dJIFocusRingView.B = -1;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFocusRingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnCreate() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            postDelayed(new Runnable(this) {
                final /* synthetic */ DJIFocusRingView a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                        this.a.onEventMainThread(DataCameraGetPushStateInfo.getInstance());
                    }
                }
            }, 100);
        }
    }

    public void dispatchOnDestroy() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void showView() {
        if (getVisibility() != 0) {
            setVisibility(0);
            this.w = dji.pilot.fpv.camera.more.a.getInstance().h();
            c.a().e(a.SHOW);
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            animShow();
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            setVisibility(8);
            this.B = -1;
            this.t.removeMessages(4097);
            c.a().e(a.HIDE);
            animGo();
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            this.z = FuselageFocusMode.OTHER;
            this.A = CameraType.OTHER;
            this.B = -1;
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.z = FuselageFocusMode.OTHER;
            this.A = CameraType.OTHER;
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.A != cameraType) {
            this.A = cameraType;
            if (dji.pilot.fpv.d.b.e(cameraType)) {
                c.a().e(a.NEEDSHOW);
            } else {
                hideView();
            }
        }
    }

    public void onEventMainThread(a$a dji_pilot_fpv_camera_more_a_a) {
        if (a$a.CAMERAINFO_GETTED == dji_pilot_fpv_camera_more_a_a && this.i.getVisibility() == 0) {
            int h = dji.pilot.fpv.camera.more.a.getInstance().h();
            if (this.w != h) {
                this.w = h;
                this.n.setMaxSize(DataCameraGetPushShotInfo.getInstance().getShotFocusMaxStroke() - h);
                int shotFocusCurStroke = DataCameraGetPushShotInfo.getInstance().getShotFocusCurStroke();
                if (!this.s) {
                    if (this.B == -1 || this.B == shotFocusCurStroke) {
                        this.n.setCurSize(a(shotFocusCurStroke - h));
                        this.y = shotFocusCurStroke;
                        this.B = -1;
                    }
                }
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        dji.pilot.fpv.camera.more.a.a("MF Demarcate value[" + dataCameraGetPushShotInfo.getShotFocusCurStroke() + d.H);
        FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
        if (fuselageFocusMode != this.z) {
            this.z = fuselageFocusMode;
            if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                this.i.show();
            } else {
                this.i.go();
            }
        }
        if (this.i.getVisibility() == 0) {
            this.n.setMaxSize(dataCameraGetPushShotInfo.getShotFocusMaxStroke() - this.w);
            int shotFocusCurStroke = dataCameraGetPushShotInfo.getShotFocusCurStroke();
            if (!(this.y == shotFocusCurStroke || this.s || (this.B != -1 && this.B != shotFocusCurStroke))) {
                this.n.setCurSize(a(shotFocusCurStroke - this.w));
                this.y = shotFocusCurStroke;
                this.B = -1;
            }
            float objDistance = dataCameraGetPushShotInfo.getObjDistance();
            if (objDistance != this.x) {
                this.x = objDistance;
                b(objDistance);
            }
        }
    }

    private int a(int i) {
        if (!dji.pilot.publics.e.a.c(null)) {
            return i;
        }
        int maxSize = this.n.getMaxSize();
        if (i > maxSize) {
            i = maxSize;
        } else if (i < 0) {
            i = 0;
        }
        return this.n.getMaxSize() - i;
    }

    private float a(float f) {
        if (f < dji.pilot.visual.a.d.c) {
            return dji.pilot.visual.a.d.c;
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

    private void b(float f) {
        float a = a(f);
        if (AutoScrollHelper.NO_MAX == a) {
            this.j.setText(R.string.camera_infinity);
            return;
        }
        CharSequence charSequence;
        if (DJIGenSettingDataManager.getInstance().z()) {
            charSequence = this.u.format((double) a) + "m";
        } else {
            charSequence = this.u.format((long) ((int) e.f(a))) + "ft";
        }
        this.j.setText(charSequence);
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        b(this.x);
    }

    private void a() {
        this.t = new b();
        this.o = new OnClickListener(this) {
            final /* synthetic */ DJIFocusRingView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.j5 == id) {
                    this.a.n.stepPrev();
                } else if (R.id.j3 == id) {
                    this.a.n.stepNext();
                }
            }
        };
        this.r = new OnTouchListener(this) {
            final /* synthetic */ DJIFocusRingView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.isEnabled()) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        this.a.v = false;
                        view.setPressed(true);
                        this.a.t.sendMessageDelayed(this.a.t.obtainMessage(4099, view), 1000);
                    } else if (action == 1) {
                        view.setPressed(false);
                        if (!this.a.v) {
                            this.a.t.removeMessages(4099);
                            if (view == this.a.m) {
                                this.a.n.setCurSize(0);
                                this.a.b(this.a.w + 0);
                            } else if (view == this.a.l) {
                                this.a.n.setCurSize(this.a.n.getMaxSize());
                                this.a.b(this.a.n.getMaxSize() + this.a.w);
                            }
                        }
                    } else if (action == 3) {
                        view.setPressed(false);
                        this.a.t.removeMessages(4099);
                    }
                }
                return true;
            }
        };
        this.p = new dji.pilot.fpv.camera.widget.DJIRulerView.a(this) {
            final /* synthetic */ DJIFocusRingView a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView, int i, int i2, boolean z) {
                this.a.y = this.a.w + i;
                this.a.k.setText(String.valueOf(this.a.y));
                if (!(z || this.a.s)) {
                    this.a.t.removeMessages(4097);
                    this.a.t.sendMessageDelayed(this.a.t.obtainMessage(4097, 1, 0), 100);
                }
                if (!this.a.s) {
                    this.a.b();
                }
            }
        };
        this.q = new dji.pilot.fpv.camera.widget.DJIRulerView.b(this) {
            final /* synthetic */ DJIFocusRingView a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView) {
                this.a.t.removeMessages(4098);
                this.a.s = true;
                this.a.t.sendEmptyMessageDelayed(4097, 100);
                if (DataCameraGetPushShotInfo.getInstance().isDigitalFocusAEnable() && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD && dji.pilot.fpv.d.b.a()) {
                    dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.b = R.string.fpv_cant_focus_aid_tip;
                    bVar.a = DJIErrorPopView.d.b;
                    bVar.f = DJIErrorPopView.c.c;
                    bVar.g = f.a;
                    c.a().e(bVar);
                }
            }

            public void b(DJIRulerView dJIRulerView) {
                this.a.t.removeMessages(4097);
                this.a.b(dJIRulerView.getCurSize() + this.a.w);
                this.a.b();
                this.a.t.sendEmptyMessageDelayed(4098, 100);
            }
        };
    }

    private void b() {
        boolean z;
        boolean z2 = true;
        DJIImageView dJIImageView = this.m;
        if (this.n.isInMin()) {
            z = false;
        } else {
            z = true;
        }
        dJIImageView.setEnabled(z);
        DJIImageView dJIImageView2 = this.l;
        if (this.n.isInMax()) {
            z2 = false;
        }
        dJIImageView2.setEnabled(z2);
    }

    private void b(final int i) {
        int maxSize;
        if (dji.pilot.publics.e.a.c(null)) {
            maxSize = (this.n.getMaxSize() - (i - this.w)) + this.w;
        } else {
            maxSize = i;
        }
        dji.pilot.fpv.camera.more.a.a("===== Ring[" + i + d.H);
        new DataCameraSetFocusStroke().a(maxSize).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIFocusRingView b;

            public void onSuccess(Object obj) {
                this.b.B = i;
                this.b.t.sendEmptyMessageDelayed(4100, DJIFocusRingView.h);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
        if (!isInEditMode()) {
            this.i = (DJILinearLayout) findViewById(R.id.j1);
            this.j = (DJITextView) findViewById(R.id.j6);
            this.k = (DJITextView) findViewById(R.id.j2);
            this.l = (DJIImageView) findViewById(R.id.j3);
            this.m = (DJIImageView) findViewById(R.id.j5);
            this.n = (DJIRulerView) findViewById(R.id.j4);
            this.l.setOnClickListener(this.o);
            this.m.setOnClickListener(this.o);
            this.n.setOnChangeListener(this.p);
            this.n.setOnScrollListener(this.q);
            this.l.setOnTouchListener(this.r);
            this.m.setOnTouchListener(this.r);
            this.k.go();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.i.isShown()) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int width = getWidth();
        if (width - this.n.getWidth() > x || x > width) {
            return false;
        }
        return true;
    }
}
