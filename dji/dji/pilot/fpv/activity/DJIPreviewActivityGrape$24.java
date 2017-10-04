package dji.pilot.fpv.activity;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.pilot.R;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.fpv.rightbar.DJISwitchModeView.a;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.thirdparty.a.c;

class DJIPreviewActivityGrape$24 implements OnGestureListener {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$24(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public boolean onSingleTapUp(final MotionEvent motionEvent) {
        boolean z;
        DJIPreviewActivityGrape.h(this.a, !DJIPreviewActivityGrape.F(this.a));
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onSingleTapUp");
        if (DJIPreviewActivityGrape.r(this.a).c()) {
            z = true;
        } else {
            DJIPreviewActivityGrape.r(this.a).b();
            z = false;
        }
        if (DJIPreviewActivityGrape.l(this.a).isShown()) {
            DJIPreviewActivityGrape.l(this.a).hideMenu(false);
            z = false;
        }
        if (z && DataCameraGetPushShotParams.getInstance().isGetted()) {
            if (DJISwitchModeView.a == a.METER) {
                b bVar;
                if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.e) {
                    bVar = new b();
                    bVar.b = R.string.fpv_cant_metering_mmode;
                    bVar.a = d.b;
                    bVar.g = f.a;
                    c.a().e(bVar);
                } else if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                    bVar = new b();
                    bVar.b = R.string.fpv_cant_metering_ae;
                    bVar.a = d.b;
                    bVar.g = f.a;
                    c.a().e(bVar);
                } else {
                    DJILogHelper.getInstance().LOGD(this.a.TAG, "ex=" + motionEvent.getX() + " left=" + DJIPreviewActivityGrape.G(this.a)[0] + " right=" + DJIPreviewActivityGrape.G(this.a)[1], false, true);
                    if (motionEvent.getX() >= ((float) DJIPreviewActivityGrape.G(this.a)[0]) && motionEvent.getX() <= ((float) DJIPreviewActivityGrape.G(this.a)[1]) && motionEvent.getY() >= ((float) DJIPreviewActivityGrape.H(this.a)[0]) && motionEvent.getY() <= ((float) DJIPreviewActivityGrape.H(this.a)[1])) {
                        int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                        DJILogHelper.getInstance().LOGD(this.a.TAG, "click 测光=" + metering);
                        if (metering == 0) {
                            DJIPreviewActivityGrape.I(this.a).a(dji.midware.data.config.P3.b.a.y).a(2).start(new dji.midware.e.d(this) {
                                final /* synthetic */ DJIPreviewActivityGrape$24 b;

                                public void onSuccess(Object obj) {
                                    DJIPreviewActivityGrape.a(this.b.a, motionEvent);
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                            e.c(s.dn);
                        } else if (metering == 2) {
                            DJIPreviewActivityGrape.a(this.a, motionEvent);
                        }
                    }
                }
            } else if (DJISwitchModeView.a == a.FOCUS) {
            }
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onShowPress");
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onLongPress");
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }
}
