package dji.pilot.fpv.rightbar;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.R;
import dji.pilot.newfpv.f.k;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.visual.a.g$f;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class DJISwitchModeView extends DJIStateImageView {
    public static a a = a.METER;
    Handler b = new Handler(this) {
        final /* synthetic */ DJISwitchModeView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            c.a().e(k.CAMERA_STOP_FOCUS);
            this.a.setImageResource(R.drawable.camera_switch_focus_gray);
        }
    };
    private final ArrayList<a> c = new ArrayList(3);
    private CameraType d = CameraType.OTHER;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;

    public enum a {
        METER,
        FOCUS,
        ZOOM
    }

    public enum b {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    public DJISwitchModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJISwitchModeView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a();
                }
            });
        }
    }

    public boolean needShow() {
        if ((!dji.logic.c.b.getInstance().a(null) || dji.pilot.visual.a.c.getInstance().l()) && this.c.size() <= 1) {
            return false;
        }
        return true;
    }

    public a getSwitchMode() {
        return a;
    }

    public void switchMode(a aVar) {
        if (a != aVar && this.c.contains(aVar)) {
            if (this.c.contains(aVar)) {
                a = aVar;
            } else {
                a = (a) this.c.get(0);
            }
            a(a);
            c.a().e(a);
        }
    }

    private void a(a aVar) {
        if (aVar == a.FOCUS) {
            if (!dji.logic.c.b.getInstance().a(null)) {
                setImageResource(R.drawable.camera_switch_focus);
            } else if (this.f) {
                setImageResource(R.drawable.camera_switch_focus);
            } else {
                setImageResource(R.drawable.camera_switch_focus_gray);
            }
        } else if (aVar == a.METER) {
            setImageResource(R.drawable.camera_switch_meter);
        } else if (aVar == a.ZOOM) {
            setImageResource(R.drawable.camera_switch_zoom);
        }
    }

    public boolean isFocusKumquat() {
        return this.f;
    }

    private void a() {
        if (dji.logic.c.b.getInstance().a(null)) {
            a = a.FOCUS;
            if (!this.f && !dji.pilot.visual.a.c.getInstance().l()) {
                this.f = true;
                setImageResource(R.drawable.camera_switch_focus);
                c.a().e(a);
                c.a().e(k.CAMERA_ENABLE_FOCUS);
                return;
            } else if (this.f && !dji.pilot.visual.a.c.getInstance().l()) {
                this.f = false;
                setImageResource(R.drawable.camera_switch_focus_gray);
                c.a().e(k.CAMERA_STOP_FOCUS);
                return;
            } else {
                return;
            }
        }
        a aVar;
        a aVar2 = a.METER;
        int size = this.c.size();
        int i = 0;
        while (i < size) {
            if (a == ((a) this.c.get(i))) {
                aVar = i >= size + -1 ? (a) this.c.get(0) : (a) this.c.get(i + 1);
                switchMode(aVar);
            }
            i++;
        }
        aVar = aVar2;
        switchMode(aVar);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            c.a().e(b.SHOW);
        }
    }

    public void hide() {
        if (getVisibility() != 4) {
            c.a().e(b.HIDE);
            setVisibility(4);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (this.f) {
            int focusStatus = dataCameraGetPushShotInfo.getFocusStatus();
            if ((focusStatus == 2 || focusStatus == 3) && this.g) {
                this.b.sendEmptyMessageDelayed(0, 20);
                this.f = false;
                this.g = false;
            } else if (focusStatus == 1 && !this.g) {
                c.a().e(k.CAMERA_START_FOCUS);
                this.g = true;
            }
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            this.d = CameraType.OTHER;
            this.e = false;
            go();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.d = CameraType.OTHER;
            this.e = false;
            go();
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.d != cameraType) {
            this.d = cameraType;
            a(true);
        }
        boolean beInTrackingMode = dataCameraGetPushStateInfo.beInTrackingMode();
        if (this.e != beInTrackingMode) {
            this.e = beInTrackingMode;
            if (beInTrackingMode) {
                go();
            } else {
                a(false);
            }
        }
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        if (dji.pilot.visual.a.c.getInstance().l()) {
            go();
        } else {
            a(false);
        }
    }

    private boolean b() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            if (a == this.c.get(i)) {
                return true;
            }
        }
        return false;
    }

    private void a(boolean z) {
        if (dji.logic.c.b.getInstance().a(null)) {
            if (dji.pilot.visual.a.c.getInstance().l()) {
                go();
            } else {
                show();
                this.c.clear();
                this.c.add(a.FOCUS);
                switchMode(a.FOCUS);
                c.a().e(a.FOCUS);
            }
            setImageResource(R.drawable.camera_switch_focus_gray);
            c.a().e(k.CAMERA_STOP_FOCUS);
            return;
        }
        boolean b = dji.pilot.fpv.camera.a.a.b();
        boolean c = dji.pilot.fpv.camera.a.a.c();
        dji.pilot.visual.a.c.getInstance().l();
        this.c.clear();
        if (b) {
            this.c.add(a.FOCUS);
        } else if (c) {
            this.c.add(a.ZOOM);
        }
        this.c.add(a.METER);
        if (needShow()) {
            a aVar = (a) this.c.get(0);
            if (aVar == a.FOCUS) {
                if (b && z && a != aVar) {
                    switchMode(a.FOCUS);
                    c.a().e(a);
                }
            } else if (aVar == a.ZOOM && c && z && a != aVar) {
                switchMode(a.ZOOM);
                c.a().e(a);
            }
            if (!(b() || a == a.METER)) {
                switchMode(a.METER);
            }
            c.a().e(b.NEEDSHOW);
            return;
        }
        go();
        if (!this.c.isEmpty()) {
            switchMode((a) this.c.get(0));
        } else if (a != a.METER) {
            switchMode(a.METER);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                postDelayed(new Runnable(this) {
                    final /* synthetic */ DJISwitchModeView a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                            this.a.onEventMainThread(DataCameraGetPushStateInfo.getInstance());
                            this.a.a(DJISwitchModeView.a);
                        }
                    }
                }, 100);
            }
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.a aVar) {
        if (aVar == dji.pilot.fpv.model.n.a.e) {
            this.f = false;
            setImageResource(R.drawable.camera_switch_focus_gray);
        } else if (aVar == dji.pilot.fpv.model.n.a.f) {
            if (dji.logic.c.b.getInstance().a(null)) {
                this.f = true;
            } else {
                this.f = false;
            }
            setImageResource(R.drawable.camera_switch_focus);
            c.a().e(k.CAMERA_ENABLE_FOCUS);
        }
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    private void a(String str) {
    }
}
