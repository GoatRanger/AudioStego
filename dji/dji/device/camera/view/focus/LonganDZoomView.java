package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.view.common.DJICameraControlViewLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.log.DJILogHelper;
import dji.logic.f.b;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.OpticsZommMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.midware.data.model.P3.DataCameraSetZoomParams;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.a;
import dji.pilot.set.g;
import dji.thirdparty.a.c;

public class LonganDZoomView extends DJIRoundLinearLayoutCompat implements OnClickListener, OnTouchListener {
    public static int b = 0;
    protected CameraType a = CameraType.OTHER;
    ZoomFocusType c = ZoomFocusType.OTHER;
    DataCameraSetOpticsZoomMode d = null;
    private final String e = LonganDZoomView.class.getSimpleName();
    private Button f;
    private Button g;
    private View h;
    private TextView i;

    public LonganDZoomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setRadius(10);
        c.a().a(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = findViewById(R.id.longan_dzoom_divider);
        this.i = (TextView) findViewById(R.id.longan_dzoom_value_tv);
        this.f = (Button) findViewById(R.id.longan_dzoom_t_btn);
        this.f.setOnClickListener(this);
        this.g = (Button) findViewById(R.id.longan_dzoom_w_btn);
        this.g.setOnClickListener(this);
        this.f.setOnTouchListener(this);
        this.g.setOnTouchListener(this);
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        if (a.b(getContext(), g.k) && !e()) {
            c.a().e(m.CHANGE_DZOOM);
        }
        a(getResources().getConfiguration());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration);
    }

    private void a(Configuration configuration) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.bottomMargin = 0;
        layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp_70_in_sw320dp);
        if (configuration.orientation == 2) {
            layoutParams.addRule(12, 0);
            layoutParams.addRule(9);
            layoutParams.addRule(14, 0);
            setRotation(0.0f);
            this.f.setRotation(0.0f);
            this.g.setRotation(0.0f);
            return;
        }
        layoutParams.addRule(9, 0);
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        setRotation(-90.0f);
        this.f.setRotation(90.0f);
        this.g.setRotation(90.0f);
    }

    public void onClick(View view) {
        if (!dji.device.common.a.a.b()) {
            return;
        }
        if (dji.device.camera.view.focus.a.c.getInstance().b() == 2) {
            dji.device.camera.view.focus.a.c.getInstance().a(2);
        } else if (dji.device.common.a.a.b()) {
            int id = view.getId();
            if (id == R.id.longan_dzoom_t_btn) {
                a();
            } else if (id == R.id.longan_dzoom_w_btn) {
                b();
            }
        } else {
            DJIPreviewActivityLongan.popDZoomWarning(getContext());
        }
    }

    private void a() {
        a(b + 10);
    }

    private void b() {
        a(b - 10);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int digitalZoomScale = dataCameraGetPushShotParams.getDigitalZoomScale();
        if (b != digitalZoomScale) {
            b = digitalZoomScale;
        }
    }

    private void a(int i) {
        if (i > 200) {
            i = 200;
        } else if (i < 100) {
            i = 100;
        }
        DJILogHelper.getInstance().LOGD("scale value", "value:" + i, false, true);
        if (dji.device.common.a.a.b()) {
            DataCameraSetZoomParams.getInstance().c(i).start(new d(this) {
                final /* synthetic */ LonganDZoomView a;

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

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case CHANGE_DZOOM:
                if (getVisibility() == 8) {
                    setVisibility(0);
                    a.a(getContext(), g.k, true);
                    return;
                }
                setVisibility(8);
                a.a(getContext(), g.k, false);
                return;
            case HIDE_ALL:
                hide();
                return;
            case SHOW_ALL:
                show();
                return;
            case SHOW_PASM:
            case SHOW_MENU:
                hide();
                return;
            case HIDE_PASM:
            case HIDE_MENU:
                show();
                return;
            default:
                return;
        }
    }

    public void hide() {
        if (isShown()) {
            setVisibility(4);
        }
    }

    public void show() {
        postDelayed(new Runnable(this) {
            final /* synthetic */ LonganDZoomView a;

            {
                this.a = r1;
            }

            public void run() {
                if (!DJICameraControlViewLongan.getIsSettingParms() && this.a.getVisibility() == 4) {
                    if ((this.a.c() || this.a.e()) && this.a.d()) {
                        this.a.setVisibility(0);
                    }
                }
            }
        }, 100);
    }

    private boolean c() {
        return a.b(getContext(), g.k) && b.e(this.a);
    }

    private boolean d() {
        dji.device.camera.a.c.b c = dji.device.camera.a.c.getInstance().c();
        if (dji.device.camera.a.a.getInstance().d() == dji.device.camera.a.a.a.TAKEPHOTO && (c == dji.device.camera.a.c.b.PANO || c == dji.device.camera.a.c.b.TIMELAPSE)) {
            return false;
        }
        return true;
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
            if (cameraType != this.a) {
                this.a = cameraType;
                if (!(b.e(this.a) || e() || !isShown())) {
                    setVisibility(8);
                    a.a(getContext(), g.k, false);
                }
                if (this.a == CameraType.DJICameraTypeCV600) {
                    this.h.setVisibility(8);
                    this.i.setVisibility(0);
                    return;
                }
                this.h.setVisibility(0);
                this.i.setVisibility(8);
            }
        }
    }

    private boolean e() {
        if (this.c == ZoomFocusType.AutoZoomFocus) {
            CameraType cameraType;
            if (this.a != CameraType.OTHER) {
                cameraType = this.a;
            } else {
                cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            }
            if (b.n(cameraType)) {
                return true;
            }
        }
        return false;
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        ZoomFocusType zoomFocusType = dataCameraGetPushShotInfo.getZoomFocusType();
        if (zoomFocusType != this.c) {
            this.c = zoomFocusType;
            if (e()) {
                setVisibility(4);
                show();
                this.d = new DataCameraSetOpticsZoomMode();
            }
        }
        float curFocusDistance = (float) dataCameraGetPushShotInfo.getCurFocusDistance();
        if (curFocusDistance <= 41.0f) {
            curFocusDistance = 40.0f;
        } else if (curFocusDistance >= 129.0f) {
            curFocusDistance = 140.0f;
        }
        this.i.setText(String.format("%.1f", new Object[]{Float.valueOf((float) (((double) curFocusDistance) * 0.55d))}) + "mm");
    }

    private void a(boolean z) {
        if (z) {
            if (this.d != null) {
                this.d.a(OpticsZommMode.a, ZoomSpeed.a, 1, 0).start(null);
            }
        } else if (this.d != null) {
            this.d.a(OpticsZommMode.a, ZoomSpeed.a, 0, 0).start(null);
        }
    }

    private void f() {
        if (this.d != null) {
            this.d.a(OpticsZommMode.c, ZoomSpeed.a, 0, 0).start(null);
        }
    }

    private boolean a(View view) {
        if (view.getId() == R.id.longan_dzoom_t_btn) {
            return true;
        }
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (dji.device.camera.view.focus.a.c.getInstance().b() != 2) {
                    if (e()) {
                        a(a(view));
                        break;
                    }
                }
                dji.device.camera.view.focus.a.c.getInstance().a(2);
                break;
                break;
            case 1:
            case 3:
                if (e()) {
                    f();
                    break;
                }
                break;
        }
        return false;
    }
}
