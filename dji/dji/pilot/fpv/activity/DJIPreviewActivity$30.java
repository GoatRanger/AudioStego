package dji.pilot.fpv.activity;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.groundStation.a.a;
import dji.thirdparty.a.c;

class DJIPreviewActivity$30 implements OnGestureListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$30(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public boolean onSingleTapUp(final MotionEvent motionEvent) {
        boolean z;
        DJIPreviewActivity.j(this.a, !DJIPreviewActivity.aj(this.a));
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onSingleTapUp");
        if (DJIPreviewActivity.M(this.a).c()) {
            z = true;
        } else {
            DJIPreviewActivity.M(this.a).b();
            z = false;
        }
        a instance = a.getInstance(null);
        if ((instance == null || !instance.p()) && DJIPreviewActivity.r(this.a)) {
            DJIPreviewActivity.t(this.a).b();
        }
        if (DJIPreviewActivity.p(this.a).isShown()) {
            DJIPreviewActivity.p(this.a).hideView();
        }
        if (DJIPreviewActivity.B(this.a) != null) {
            DJIPreviewActivity.B(this.a).go();
        }
        if (DJIPreviewActivity.y(this.a).isShowingMenu()) {
            DJIPreviewActivity.y(this.a).hideMenu(false);
            z = false;
        }
        if (z && (b.j(DataCameraGetPushStateInfo.getInstance().getCameraType()) || DataCameraGetPushShotParams.getInstance().isGetted())) {
            if (!b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                DJILogHelper.getInstance().LOGD(this.a.TAG, "ex=" + motionEvent.getX() + " left=" + DJIPreviewActivity.ak(this.a)[0] + " right=" + DJIPreviewActivity.ak(this.a)[1], false, true);
                if (motionEvent.getX() < ((float) DJIPreviewActivity.al(this.a)[0]) || motionEvent.getX() > ((float) DJIPreviewActivity.al(this.a)[1]) || motionEvent.getY() < ((float) DJIPreviewActivity.am(this.a)[0]) || motionEvent.getY() > ((float) DJIPreviewActivity.am(this.a)[1])) {
                    DJIPreviewActivity.an(this.a);
                } else if (DJIPreviewActivity.ao(this.a) == 1) {
                    r0 = new DJIErrorPopView.b();
                    r0.b = R.string.fpv_cant_mffocusing_tap;
                    r0.a = d.b;
                    r0.g = f.a;
                    c.a().e(r0);
                } else if (DJISwitchModeView.a == DJISwitchModeView.a.METER) {
                    if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.e) {
                        r0 = new DJIErrorPopView.b();
                        r0.b = R.string.fpv_cant_metering_mmode;
                        r0.a = d.b;
                        r0.g = f.a;
                        c.a().e(r0);
                    } else if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                        r0 = new DJIErrorPopView.b();
                        r0.b = R.string.fpv_cant_metering_ae;
                        r0.a = d.b;
                        r0.g = f.a;
                        c.a().e(r0);
                    } else {
                        int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                        DJILogHelper.getInstance().LOGD(this.a.TAG, "click 测光=" + metering);
                        if (metering == 0) {
                            DJIPreviewActivity.ap(this.a).a(dji.midware.data.config.P3.b.a.y).a(2).start(new dji.midware.e.d(this) {
                                final /* synthetic */ DJIPreviewActivity$30 b;

                                public void onSuccess(Object obj) {
                                    DJIPreviewActivity.a(this.b.a, motionEvent.getX(), motionEvent.getY());
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                            e.c(s.dn);
                        } else if (metering == 2) {
                            DJIPreviewActivity.a(this.a, motionEvent.getX(), motionEvent.getY());
                        }
                    }
                } else if (DJISwitchModeView.a == DJISwitchModeView.a.FOCUS) {
                    DJIPreviewActivity.aq(this.a).handleMotion(motionEvent);
                } else if (DJISwitchModeView.a == DJISwitchModeView.a.ZOOM) {
                    if (DJIPreviewActivity.ar(this.a) == null) {
                        DJIPreviewActivity.as(this.a);
                    }
                    DJIPreviewActivity.b(this.a, motionEvent.getX(), motionEvent.getY());
                }
            } else if (DataCameraGetPushTauParam.getInstance().supportSpotThermometric() && DataCameraGetPushTauParam.getInstance().getThermometricType() == ThermometricType.b) {
                DJIPreviewActivity.E(this.a).handleMotion(motionEvent);
            }
        }
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onShowPress");
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!DJIPreviewActivity.M(this.a).c()) {
            DJIPreviewActivity.M(this.a).b();
        }
        if (DJIPreviewActivity.ab(this.a).c) {
            DJIPreviewActivity.ab(this.a).b(motionEvent2);
            DJIPreviewActivity.ab(this.a).a(motionEvent2.getX() - motionEvent.getX(), motionEvent2.getY() - motionEvent.getY());
        }
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (!DJIPreviewActivity.ab(this.a).c) {
            DJIPreviewActivity.a(this.a, motionEvent);
            this.a.d();
        }
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        DJIPreviewActivity.a(this.a, motionEvent);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onDown " + System.currentTimeMillis());
        return false;
    }
}
