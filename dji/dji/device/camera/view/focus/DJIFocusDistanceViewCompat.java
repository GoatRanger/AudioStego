package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.widget.DJIRulerView;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusDistance;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

public class DJIFocusDistanceViewCompat extends DJIRelativeLayout {
    protected static final int a = 4097;
    protected static final int b = 4098;
    protected static final long c = 100;
    protected static final long d = 50;
    private DJITextView e = null;
    private DJIImageView f = null;
    private DJIImageView g = null;
    private DJIRulerView h = null;
    private OnClickListener i = null;
    private dji.device.widget.DJIRulerView.a j = null;
    private dji.device.widget.DJIRulerView.b k = null;
    private boolean l = false;
    private b m = null;
    private final DecimalFormat n = new DecimalFormat("#.#");
    private int o = 0;
    private CameraType p = CameraType.OTHER;
    private ZoomFocusType q = ZoomFocusType.OTHER;
    private int r = 0;
    private int s = 0;
    private int t = 1;
    private volatile int u = -1;

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    private static final class b extends Handler {
        private final WeakReference<DJIFocusDistanceViewCompat> a;

        private b(DJIFocusDistanceViewCompat dJIFocusDistanceViewCompat) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFocusDistanceViewCompat);
        }

        public void handleMessage(Message message) {
            DJIFocusDistanceViewCompat dJIFocusDistanceViewCompat = (DJIFocusDistanceViewCompat) this.a.get();
            if (dJIFocusDistanceViewCompat != null) {
                switch (message.what) {
                    case 4097:
                        if (message.arg1 == 0) {
                            sendEmptyMessageDelayed(4097, DJIFocusDistanceViewCompat.c);
                        }
                        dJIFocusDistanceViewCompat.b(dJIFocusDistanceViewCompat.o);
                        return;
                    case 4098:
                        dJIFocusDistanceViewCompat.l = false;
                        dJIFocusDistanceViewCompat.onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFocusDistanceViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            postDelayed(new Runnable(this) {
                final /* synthetic */ DJIFocusDistanceViewCompat a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                        this.a.onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                    }
                }
            }, c);
        }
        a(getResources().getConfiguration());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void showView() {
        if (getVisibility() != 0) {
            animShow();
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            this.m.removeMessages(4097);
            animGo();
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration);
    }

    private void a(Configuration configuration) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (configuration.orientation == 2) {
            layoutParams.rightMargin = 100;
            layoutParams.topMargin = 210;
            return;
        }
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = (int) (((float) (DJIPreviewActivityLongan.mScreenHeight - DJIPreviewActivityLongan.mVideoHeight)) / 2.0f);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.p = CameraType.OTHER;
            this.q = ZoomFocusType.OTHER;
            hideView();
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        ZoomFocusType zoomFocusType = dataCameraGetPushShotInfo.getZoomFocusType();
        if (zoomFocusType != this.q) {
            this.q = zoomFocusType;
            if (zoomFocusType == ZoomFocusType.AutoZoomFocus) {
                CameraType cameraType;
                if (this.p != CameraType.OTHER) {
                    cameraType = this.p;
                } else {
                    cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
                }
                if (dji.logic.f.b.n(cameraType)) {
                    showView();
                }
            }
            hideView();
        }
        if (getVisibility() == 0) {
            int minFocusDistance = dataCameraGetPushShotInfo.getMinFocusDistance();
            int maxFocusDistance = dataCameraGetPushShotInfo.getMaxFocusDistance();
            if (!(this.r == minFocusDistance && maxFocusDistance == this.s)) {
                this.r = minFocusDistance;
                this.s = maxFocusDistance;
                this.h.setMaxSize(maxFocusDistance - minFocusDistance);
            }
            maxFocusDistance = dataCameraGetPushShotInfo.getMinFocusDistanceStep();
            if (this.t != maxFocusDistance) {
                this.t = maxFocusDistance;
            }
            maxFocusDistance = dataCameraGetPushShotInfo.getCurFocusDistance();
            if (this.o != maxFocusDistance && !this.l) {
                if (this.u == -1 || this.u == maxFocusDistance) {
                    this.o = maxFocusDistance;
                    this.h.setCurSize(maxFocusDistance - minFocusDistance);
                    this.u = -1;
                    a(maxFocusDistance);
                }
            }
        }
    }

    private void a(int i) {
        this.e.setText(this.n.format((double) ((((float) i) * 1.0f) / 10.0f)) + "mm");
    }

    private int a(int i, int i2) {
        int i3 = i % i2;
        if (i3 != 0) {
            return (i - i3) + (Math.round((((float) i3) * 1.0f) / ((float) i2)) * i2);
        }
        return i;
    }

    private void a() {
        this.m = new b();
        this.i = new OnClickListener(this) {
            final /* synthetic */ DJIFocusDistanceViewCompat a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.camera_focus_distance_zero_img == id) {
                    this.a.h.stepUp(this.a.t);
                } else if (R.id.camera_focus_distance_max_img == id) {
                    this.a.h.stepDown(this.a.t);
                }
            }
        };
        this.j = new dji.device.widget.DJIRulerView.a(this) {
            final /* synthetic */ DJIFocusDistanceViewCompat a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView, int i, int i2, boolean z) {
                int a = this.a.a(i, this.a.t);
                this.a.o = a;
                this.a.a(a + this.a.r);
                if (!z && !this.a.l) {
                    this.a.m.removeMessages(4097);
                    this.a.m.sendMessageDelayed(this.a.m.obtainMessage(4097, 1, 0), 50);
                }
            }
        };
        this.k = new dji.device.widget.DJIRulerView.b(this) {
            final /* synthetic */ DJIFocusDistanceViewCompat a;

            {
                this.a = r1;
            }

            public void a(DJIRulerView dJIRulerView) {
                this.a.m.removeMessages(4098);
                this.a.l = true;
                this.a.m.sendEmptyMessageDelayed(4097, DJIFocusDistanceViewCompat.c);
            }

            public void b(DJIRulerView dJIRulerView) {
                this.a.m.removeMessages(4097);
                this.a.m.sendEmptyMessageDelayed(4098, 50);
                this.a.b(this.a.o);
            }
        };
    }

    private void b(int i) {
        final int i2 = this.r + i;
        DataCameraSetFocusDistance.getInstance().a(i2).b(2).start(new d(this) {
            final /* synthetic */ DJIFocusDistanceViewCompat b;

            public void onSuccess(Object obj) {
                this.b.u = i2;
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void onFinishInflate() {
        a();
        if (!isInEditMode()) {
            this.e = (DJITextView) findViewById(R.id.camera_focus_distance_size_tv);
            this.f = (DJIImageView) findViewById(R.id.camera_focus_distance_zero_img);
            this.g = (DJIImageView) findViewById(R.id.camera_focus_distance_max_img);
            this.h = (DJIRulerView) findViewById(R.id.camera_focus_distance_ruler);
            this.f.setOnClickListener(this.i);
            this.g.setOnClickListener(this.i);
            this.h.setOnChangeListener(this.j);
            this.h.setOnScrollListener(this.k);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
