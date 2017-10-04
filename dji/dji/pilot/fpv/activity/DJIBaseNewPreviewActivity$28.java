package dji.pilot.fpv.activity;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.groundStation.a.a;
import dji.pilot.visual.a.c;

class DJIBaseNewPreviewActivity$28 implements OnGestureListener {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$28(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        boolean z;
        DJIBaseNewPreviewActivity.e(this.a, !DJIBaseNewPreviewActivity.N(this.a));
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onSingleTapUp");
        if (this.a.r.c()) {
            z = true;
        } else {
            this.a.r.b();
            z = false;
        }
        a instance = a.getInstance(null);
        if ((instance == null || !instance.p()) && !this.a.c()) {
            DJIBaseNewPreviewActivity.m(this.a).show();
        }
        if (DJIBaseNewPreviewActivity.q(this.a).isShown()) {
            DJIBaseNewPreviewActivity.q(this.a).hideView();
            z = false;
        }
        if (DJIBaseNewPreviewActivity.p(this.a).isShowingMenu()) {
            DJIBaseNewPreviewActivity.p(this.a).hideMenu(false);
            z = false;
        }
        if (z && DataCameraGetPushShotParams.getInstance().isGetted() && !c.getInstance().l() && (!b.getInstance().a(null) || this.a.g.isFocusKumquat())) {
            if (motionEvent.getX() < ((float) DJIBaseNewPreviewActivity.O(this.a)[0]) || motionEvent.getX() > ((float) DJIBaseNewPreviewActivity.O(this.a)[1]) || motionEvent.getY() < ((float) DJIBaseNewPreviewActivity.P(this.a)[0]) || motionEvent.getY() > ((float) DJIBaseNewPreviewActivity.P(this.a)[1])) {
                DJIBaseNewPreviewActivity.Q(this.a);
            } else if (DJISwitchModeView.a == DJISwitchModeView.a.METER) {
                DJIBaseNewPreviewActivity.b(this.a, motionEvent.getX(), motionEvent.getY());
            } else if (DJISwitchModeView.a == DJISwitchModeView.a.FOCUS) {
                this.a.i.handleMotion(motionEvent);
            }
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onShowPress");
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.a.r.c()) {
            this.a.r.b();
        }
        if (DJIBaseNewPreviewActivity.G(this.a).c) {
            DJIBaseNewPreviewActivity.G(this.a).b(motionEvent2);
            DJIBaseNewPreviewActivity.G(this.a).a(motionEvent2.getX() - motionEvent.getX(), motionEvent2.getY() - motionEvent.getY());
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        DJIBaseNewPreviewActivity.R(this.a);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onLongPress");
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onFling", false, true);
        if (motionEvent2.getPointerCount() > 1) {
            return false;
        }
        if (Math.abs(f) > Math.abs(f2) && this.a.r.c() && motionEvent2.getX() - motionEvent.getX() <= (-this.a.N)) {
            this.a.n.animShow();
            return true;
        } else if (!this.a.U) {
            return false;
        } else {
            this.a.d();
            return true;
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        DJIBaseNewPreviewActivity.a(this.a, motionEvent);
        this.a.U = this.a.r.c();
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onDown " + System.currentTimeMillis());
        DJIBaseNewPreviewActivity.c(this.a).sendEmptyMessageDelayed(36864, 500);
        return false;
    }
}
