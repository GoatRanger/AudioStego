package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.logic.f.b;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganFocusExposureSwitchView extends DJIStateImageViewCompat {
    public static a a = a.METER;
    int b;
    int c;
    int d;
    int e;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    private CameraType i = CameraType.OTHER;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            b = new int[m.values().length];
            try {
                b[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[m.SHOW_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum a {
        METER,
        FOCUS
    }

    public LonganFocusExposureSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public a getSwitchMode() {
        return a;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    private void a() {
        if (a == a.METER) {
            setImageResource(R.drawable.camera_switch_meter);
        } else {
            setImageResource(R.drawable.longan_camera_switch_focus);
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            postDelayed(new Runnable(this) {
                final /* synthetic */ LonganFocusExposureSwitchView a;

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
        a(getResources().getConfiguration());
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void switchMode(a aVar) {
        if (a != aVar) {
            a = aVar;
            if (a == a.FOCUS) {
                setImageResource(R.drawable.camera_switch_focus);
            } else {
                setImageResource(R.drawable.camera_switch_meter);
            }
            c.a().e(a);
        }
    }

    private void b() {
        if (a == a.FOCUS) {
            a = a.METER;
            setImageResource(R.drawable.camera_switch_meter);
        } else {
            a = a.FOCUS;
            setImageResource(R.drawable.camera_switch_focus);
        }
        c.a().e(a);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b = i;
        this.c = i2;
    }

    protected void a(MotionEvent motionEvent) {
        a(motionEvent.getRawX(), motionEvent.getRawY());
    }

    protected void a(float f, float f2) {
        setX(f - ((float) (this.b / 2)));
        setY(f2 - ((float) (this.c / 2)));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (!this.g) {
                    this.g = true;
                    this.d = (int) motionEvent.getX();
                    this.e = (int) motionEvent.getY();
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            case 1:
            case 3:
                if (!this.h) {
                    b();
                }
                if (this.g) {
                    this.g = false;
                    this.h = false;
                    break;
                }
                break;
            case 2:
                break;
        }
        if (this.g && getMoveDiatance(motionEvent) > 25 && this.f) {
            this.h = true;
            a(motionEvent);
        }
        return this.g;
    }

    public int getMoveDiatance(MotionEvent motionEvent) {
        return (int) (Math.abs(motionEvent.getX() - ((float) this.d)) + Math.abs(motionEvent.getY() - ((float) this.e)));
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration);
    }

    private void a(Configuration configuration) {
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.i != cameraType) {
            this.i = cameraType;
            if (b.n(cameraType)) {
                show();
                return;
            }
            go();
            c.a().e(a);
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.SHOW) {
            switchMode(a.FOCUS);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass3.a[oVar.ordinal()]) {
            case 1:
                post(new Runnable(this) {
                    final /* synthetic */ LonganFocusExposureSwitchView a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.switchMode(a.METER);
                    }
                });
                return;
            default:
                return;
        }
    }

    public void show() {
        if (b.n(this.i) && !DJIPreviewActivityLongan.isHideAll) {
            super.show();
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                hide();
                return;
            case SHOW_ALL:
                show();
                return;
            default:
                return;
        }
    }
}
