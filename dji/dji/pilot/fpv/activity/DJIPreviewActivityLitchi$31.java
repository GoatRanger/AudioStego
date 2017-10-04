package dji.pilot.fpv.activity;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.groundStation.a.a;
import dji.pilot.visual.a.c;

class DJIPreviewActivityLitchi$31 implements OnGestureListener {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$31(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        boolean z;
        DJIPreviewActivityLitchi.i(this.a, !DJIPreviewActivityLitchi.aa(this.a));
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onSingleTapUp");
        if (DJIPreviewActivityLitchi.E(this.a).c()) {
            z = true;
        } else {
            DJIPreviewActivityLitchi.E(this.a).b();
            z = false;
        }
        a instance = a.getInstance(null);
        if (instance == null || !instance.p()) {
            DJIPreviewActivityLitchi.m(this.a).b();
        }
        if (DJIPreviewActivityLitchi.p(this.a).isShown()) {
            DJIPreviewActivityLitchi.p(this.a).hideView();
        }
        if (DJIPreviewActivityLitchi.u(this.a).isShowingMenu()) {
            DJIPreviewActivityLitchi.u(this.a).hideMenu(false);
            z = false;
        }
        if (z && DataCameraGetPushShotParams.getInstance().isGetted() && !c.getInstance().l()) {
            DJILogHelper.getInstance().LOGD(this.a.TAG, "ex=" + motionEvent.getX() + " left=" + DJIPreviewActivityLitchi.ab(this.a)[0] + " right=" + DJIPreviewActivityLitchi.ab(this.a)[1], false, true);
            if (motionEvent.getX() < ((float) DJIPreviewActivityLitchi.ab(this.a)[0]) || motionEvent.getX() > ((float) DJIPreviewActivityLitchi.ab(this.a)[1]) || motionEvent.getY() < ((float) DJIPreviewActivityLitchi.ac(this.a)[0]) || motionEvent.getY() > ((float) DJIPreviewActivityLitchi.ac(this.a)[1])) {
                DJIPreviewActivityLitchi.ad(this.a);
            } else if (DJISwitchModeView.a == DJISwitchModeView.a.METER) {
                DJIPreviewActivityLitchi.b(this.a, motionEvent.getX(), motionEvent.getY());
            } else if (DJISwitchModeView.a == DJISwitchModeView.a.FOCUS) {
                DJIPreviewActivityLitchi.ae(this.a).handleMotion(motionEvent);
            }
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onShowPress");
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!DJIPreviewActivityLitchi.E(this.a).c()) {
            DJIPreviewActivityLitchi.E(this.a).b();
        }
        if (DJIPreviewActivityLitchi.S(this.a).c) {
            DJIPreviewActivityLitchi.S(this.a).b(motionEvent2);
            DJIPreviewActivityLitchi.S(this.a).a(motionEvent2.getX() - motionEvent.getX(), motionEvent2.getY() - motionEvent.getY());
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        DJIPreviewActivityLitchi.af(this.a);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onLongPress");
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        DJIPreviewActivityLitchi.a(this.a, motionEvent);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onDown " + System.currentTimeMillis());
        DJIPreviewActivityLitchi.c(this.a).sendEmptyMessageDelayed(36864, 500);
        return false;
    }
}
