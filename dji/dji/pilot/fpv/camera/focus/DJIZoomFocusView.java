package dji.pilot.fpv.camera.focus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import dji.common.camera.DJICameraSettingsDef.CameraOpticalZoomSpec;
import dji.common.error.DJIError;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.OpticsZommMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.pilot.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;

public class DJIZoomFocusView extends DJILinearLayout implements OnTouchListener, d {
    private static float b = 1.0f;
    private final String a = getClass().getSimpleName();
    private DJITextView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private ZoomFocusType g = ZoomFocusType.OTHER;
    private CameraType h = CameraType.OTHER;
    private CameraOpticalZoomSpec i = new CameraOpticalZoomSpec();

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    public static float getCurScale() {
        return b;
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
    }

    private void a() {
        float c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.b(b.ch));
        float c2 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.b(b.Q));
        if (c * c2 > 30.0f) {
            this.e.setTextColor(getContext().getResources().getColor(R.color.at));
        } else {
            this.e.setTextColor(getContext().getResources().getColor(R.color.om));
        }
        this.e.setText(getContext().getString(R.string.camera_zoom_focus_scale, new Object[]{Float.valueOf(c * c2)}));
        b = c * c2;
    }

    public DJIZoomFocusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        if (getVisibility() != 0) {
            dji.thirdparty.a.c.a().e(a.SHOW);
            setVisibility(0);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            dji.thirdparty.a.c.a().e(a.HIDE);
            setVisibility(8);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        ZoomFocusType zoomFocusType = dataCameraGetPushShotInfo.getZoomFocusType();
        if (zoomFocusType != this.g) {
            this.g = zoomFocusType;
            if (dji.pilot.fpv.d.b.a(zoomFocusType, this.h)) {
                dji.thirdparty.a.c.a().e(a.NEEDSHOW);
            } else {
                go();
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (cameraType != this.h) {
            this.h = cameraType;
            if (dji.pilot.fpv.d.b.a(this.g, cameraType)) {
                dji.thirdparty.a.c.a().e(a.NEEDSHOW);
            } else {
                go();
            }
            b();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.h = CameraType.OTHER;
            this.g = ZoomFocusType.OTHER;
            go();
        }
        b();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.c = (DJITextView) findViewById(R.id.qi);
            this.d = (DJITextView) findViewById(R.id.qk);
            this.e = (DJITextView) findViewById(R.id.qj);
            this.f = (DJITextView) findViewById(R.id.ql);
            this.c.setOnTouchListener(this);
            this.d.setOnTouchListener(this);
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIZoomFocusView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    DJISDKCache.getInstance().performAction(dji.sdksharedlib.a.b.b(b.bS), new dji.sdksharedlib.c.b(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                        }

                        public void a(DJIError dJIError) {
                        }
                    }, new Object[0]);
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(isInEditMode() || dji.thirdparty.a.c.a().c(this))) {
            dji.thirdparty.a.c.a().a(this);
        }
        if (DataCameraGetPushShotInfo.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        }
        post(new Runnable(this) {
            final /* synthetic */ DJIZoomFocusView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        });
    }

    private void b() {
        if (dji.pilot.fpv.d.b.k(null)) {
            dji.sdksharedlib.a.a.b(this, new String[]{b.ch});
            dji.sdksharedlib.a.a.b(this, new String[]{b.Q});
            a();
            this.f.show();
            this.e.show();
            return;
        }
        this.f.go();
        this.e.go();
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
        dji.sdksharedlib.a.a.a(this);
    }

    private void a(boolean z) {
        new DataCameraSetOpticsZoomMode().a(OpticsZommMode.a, ZoomSpeed.a, z ? 1 : 0, 0).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIZoomFocusView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                dji.pilot.fpv.camera.more.a.a("Start Optics zoom success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.pilot.fpv.camera.more.a.a("Start Optics zoom fail-" + aVar);
            }
        });
    }

    private void c() {
        new DataCameraSetOpticsZoomMode().a(OpticsZommMode.c, ZoomSpeed.a, 0, 0).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIZoomFocusView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                dji.pilot.fpv.camera.more.a.a("Stop Optics zoom success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.pilot.fpv.camera.more.a.a("Stop Optics zoom fail-" + aVar);
            }
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getAction()) {
            case 0:
                if (view == this.c) {
                    z = true;
                }
                a(z);
                view.setPressed(true);
                break;
            case 1:
            case 3:
                c();
                view.setPressed(false);
                break;
        }
        return true;
    }
}
