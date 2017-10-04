package dji.pilot.fpv.camera.focus;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusDistance;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.camera.widget.DJIRulerView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

public class DJIFocusDistanceView extends DJIRelativeLayout {
    protected static final int a = 4097;
    protected static final int b = 4098;
    protected static final int c = 4099;
    protected static final long d = 70;
    protected static final long e = 100;
    protected static final long f = 1500;
    private DJITextView g = null;
    private DJIImageView h = null;
    private DJIImageView i = null;
    private DJIRulerView j = null;
    private OnClickListener k = null;
    private dji.pilot.fpv.camera.widget.DJIRulerView.a l = null;
    private dji.pilot.fpv.camera.widget.DJIRulerView.b m = null;
    private boolean n = false;
    private b o = null;
    private final DecimalFormat p = new DecimalFormat("#.#");
    private int q = 0;
    private CameraType r = CameraType.OTHER;
    private ZoomFocusType s = ZoomFocusType.OTHER;
    private int t = 0;
    private int u = 0;
    private int v = 1;
    private volatile int w = -1;

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    private static final class b extends Handler {
        private final WeakReference<DJIFocusDistanceView> a;

        private b(DJIFocusDistanceView dJIFocusDistanceView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFocusDistanceView);
        }

        public void handleMessage(Message message) {
            DJIFocusDistanceView dJIFocusDistanceView = (DJIFocusDistanceView) this.a.get();
            if (dJIFocusDistanceView != null) {
                switch (message.what) {
                    case 4097:
                        if (message.arg1 == 0) {
                            sendEmptyMessageDelayed(4097, DJIFocusDistanceView.d);
                        }
                        dJIFocusDistanceView.b(dJIFocusDistanceView.q, message.arg1 == 0 ? 1 : 2);
                        return;
                    case 4098:
                        dJIFocusDistanceView.n = false;
                        dJIFocusDistanceView.onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                        return;
                    case 4099:
                        if (!dJIFocusDistanceView.n) {
                            dJIFocusDistanceView.w = -1;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFocusDistanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnCreate() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            postDelayed(new Runnable(this) {
                final /* synthetic */ DJIFocusDistanceView a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                        this.a.onEventMainThread(DataCameraGetPushStateInfo.getInstance());
                    }
                }
            }, e);
        }
    }

    public void dispatchOnDestroy() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void showView() {
        if (getVisibility() != 0) {
            c.a().e(a.SHOW);
            setVisibility(0);
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            this.w = -1;
            c.a().e(a.HIDE);
            this.o.removeMessages(4097);
            setVisibility(8);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.w = -1;
            this.r = CameraType.OTHER;
            this.s = ZoomFocusType.OTHER;
            hideView();
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.r != cameraType) {
            this.r = cameraType;
            if (dji.pilot.fpv.d.b.a(cameraType, this.s)) {
                c.a().e(a.NEEDSHOW);
            } else {
                hideView();
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        ZoomFocusType zoomFocusType = dataCameraGetPushShotInfo.getZoomFocusType();
        if (zoomFocusType != this.s) {
            this.s = zoomFocusType;
            if (dji.pilot.fpv.d.b.a(this.r, zoomFocusType)) {
                c.a().e(a.NEEDSHOW);
            } else {
                hideView();
            }
        }
        if (getVisibility() == 0) {
            int minFocusDistance = dataCameraGetPushShotInfo.getMinFocusDistance();
            int maxFocusDistance = dataCameraGetPushShotInfo.getMaxFocusDistance();
            if (!(this.t == minFocusDistance && maxFocusDistance == this.u)) {
                this.t = minFocusDistance;
                this.u = maxFocusDistance;
                this.j.setMaxSize(maxFocusDistance - minFocusDistance);
            }
            maxFocusDistance = dataCameraGetPushShotInfo.getMinFocusDistanceStep();
            if (this.v != maxFocusDistance) {
                this.v = maxFocusDistance;
            }
            maxFocusDistance = dataCameraGetPushShotInfo.getCurFocusDistance();
            if (this.q != maxFocusDistance && !this.n) {
                if (this.w == -1 || this.w == maxFocusDistance) {
                    this.q = maxFocusDistance;
                    this.j.setCurSize(maxFocusDistance - minFocusDistance);
                    this.w = -1;
                    a(maxFocusDistance);
                }
            }
        }
    }

    private void a(int i) {
        this.g.setText(this.p.format((double) ((((float) i) * 1.0f) / 10.0f)) + "mm");
    }

    private int a(int i, int i2) {
        int i3 = i % i2;
        if (i3 != 0) {
            return (i - i3) + (Math.round((((float) i3) * 1.0f) / ((float) i2)) * i2);
        }
        return i;
    }

    private void a() {
        this.o = new b();
        this.k = new OnClickListener(this) {
            final /* synthetic */ DJIFocusDistanceView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.iw == id) {
                    this.a.b(this.a.j.stepUp(this.a.v) + this.a.t, 2);
                } else if (R.id.iy == id) {
                    this.a.b(this.a.j.stepDown(this.a.v) + this.a.t, 2);
                }
            }
        };
        this.l = new dji.pilot.fpv.camera.widget.DJIRulerView.a(this) {
            final /* synthetic */ DJIFocusDistanceView a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView, int i, int i2, boolean z) {
                int b = this.a.a(i, this.a.v);
                this.a.q = this.a.t + b;
                this.a.a(b + this.a.t);
                if (!z && !this.a.n) {
                    this.a.o.removeMessages(4097);
                    this.a.o.sendMessageDelayed(this.a.o.obtainMessage(4097, 1, 0), DJIFocusDistanceView.e);
                }
            }
        };
        this.m = new dji.pilot.fpv.camera.widget.DJIRulerView.b(this) {
            final /* synthetic */ DJIFocusDistanceView a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView) {
                this.a.o.removeMessages(4098);
                this.a.n = true;
                this.a.o.sendEmptyMessageDelayed(4097, DJIFocusDistanceView.d);
            }

            public void b(DJIRulerView dJIRulerView) {
                this.a.o.removeMessages(4097);
                this.a.b(dJIRulerView.getCurSize() + this.a.t, 2);
                this.a.o.sendEmptyMessageDelayed(4098, DJIFocusDistanceView.e);
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
        if (!isInEditMode()) {
            this.g = (DJITextView) findViewById(R.id.iz);
            this.h = (DJIImageView) findViewById(R.id.iw);
            this.i = (DJIImageView) findViewById(R.id.iy);
            this.j = (DJIRulerView) findViewById(R.id.ix);
            this.h.setOnClickListener(this.k);
            this.i.setOnClickListener(this.k);
            this.j.setOnChangeListener(this.l);
            this.j.setOnScrollListener(this.m);
        }
    }

    private void b(final int i, int i2) {
        DataCameraSetFocusDistance.getInstance().a(i).b(i2).start(new d(this) {
            final /* synthetic */ DJIFocusDistanceView b;

            public void onSuccess(Object obj) {
                this.b.w = i;
                this.b.o.sendEmptyMessageDelayed(4099, DJIFocusDistanceView.f);
                dji.pilot.fpv.camera.more.a.a("set Distance success[" + i + dji.pilot.usercenter.protocol.d.H);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.pilot.fpv.camera.more.a.a("set Distance failed[" + i + dji.pilot.usercenter.protocol.d.H);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
