package dji.pilot.fpv.activity;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.fpv.rightbar.DJISwitchModeView.a;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.protocol.e;
import java.lang.ref.WeakReference;

final class DJIPreviewActivity$a extends Handler {
    private final WeakReference<DJIPreviewActivity> a;

    public DJIPreviewActivity$a(DJIPreviewActivity dJIPreviewActivity) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIPreviewActivity);
    }

    public void handleMessage(Message message) {
        int i = 0;
        DJIPreviewActivity dJIPreviewActivity = (DJIPreviewActivity) this.a.get();
        if (dJIPreviewActivity != null && !dJIPreviewActivity.isFinishing()) {
            switch (message.what) {
                case 11:
                    i.getInstance().a(ProductType.litchiS);
                    return;
                case 4096:
                    DJIPreviewActivity.U(dJIPreviewActivity);
                    return;
                case 8192:
                    DJIPreviewActivity.h(dJIPreviewActivity, true);
                    return;
                case e.ap /*12288*/:
                    if (message.arg1 == 0) {
                        DJIPreviewActivity.V(dJIPreviewActivity);
                        return;
                    } else {
                        DJIPreviewActivity.a(dJIPreviewActivity, message.arg1, message.arg2);
                        return;
                    }
                case 16384:
                    dJIPreviewActivity.l();
                    DJIPreviewActivity.h(dJIPreviewActivity, false);
                    return;
                case 20480:
                    if (!b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                        PointF pointF = (PointF) message.obj;
                        DJIPreviewActivity.W(dJIPreviewActivity).setBackgroundResource(R.drawable.fpv_spot_metering);
                        Drawable background = DJIPreviewActivity.W(dJIPreviewActivity).getBackground();
                        float intrinsicWidth = (((float) background.getIntrinsicWidth()) * 1.0f) / 2.0f;
                        float intrinsicHeight = (((float) background.getIntrinsicHeight()) * 1.0f) / 2.0f;
                        DJIPreviewActivity.W(dJIPreviewActivity).setX(pointF.x - intrinsicWidth);
                        DJIPreviewActivity.W(dJIPreviewActivity).setY(pointF.y - intrinsicHeight);
                        DJIPreviewActivity.X(dJIPreviewActivity).setX((intrinsicWidth + pointF.x) - ((float) DJIPreviewActivity.X(dJIPreviewActivity).getWidth()));
                        DJIPreviewActivity.X(dJIPreviewActivity).setY(pointF.y - intrinsicHeight);
                        DJIPreviewActivity.Y(dJIPreviewActivity).setX(pointF.x - ((float) (DJIPreviewActivity.Y(dJIPreviewActivity).getWidth() / 2)));
                        DJIPreviewActivity.Y(dJIPreviewActivity).setY((pointF.y + intrinsicHeight) + 5.0f);
                        if (DJIPreviewActivity.Z(dJIPreviewActivity) == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                            DJIPreviewActivity.a(dJIPreviewActivity, DataCameraGetPushShotParams.getInstance().getExposureMode());
                            DJIPreviewActivity.e(dJIPreviewActivity, DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0);
                        }
                        if (DJISwitchModeView.a == a.METER && DJIPreviewActivity.Z(dJIPreviewActivity) != ExposureMode.i && ExposureMode.e != DJIPreviewActivity.Z(dJIPreviewActivity) && DJIPreviewActivity.aa(dJIPreviewActivity) == 0) {
                            DJIPreviewActivity.W(dJIPreviewActivity).animShow();
                            DJIPreviewActivity.Y(dJIPreviewActivity).animShow();
                            DJIPreviewActivity.X(dJIPreviewActivity).animShow();
                            return;
                        }
                        return;
                    }
                    return;
                case e.ax /*24576*/:
                    DJIPreviewActivity.i(dJIPreviewActivity, true);
                    return;
                case 28672:
                    if (!b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                        DJIPreviewActivity.X(dJIPreviewActivity).hide();
                        DJIPreviewActivity.W(dJIPreviewActivity).setBackgroundResource(R.drawable.fpv_center_metering);
                        Drawable background2 = DJIPreviewActivity.W(dJIPreviewActivity).getBackground();
                        int intrinsicWidth2 = background2.getIntrinsicWidth();
                        int intrinsicHeight2 = background2.getIntrinsicHeight();
                        DJIPreviewActivity.W(dJIPreviewActivity).setX((float) ((DJIBaseActivity.screenWidth - intrinsicWidth2) / 2));
                        DJIPreviewActivity.W(dJIPreviewActivity).setY((float) ((DJIBaseActivity.screenHeight - intrinsicHeight2) / 2));
                        DJIPreviewActivity.Y(dJIPreviewActivity).setX((float) ((DJIBaseActivity.screenWidth - DJIPreviewActivity.Y(dJIPreviewActivity).getWidth()) / 2));
                        DJIPreviewActivity.Y(dJIPreviewActivity).setY((float) (((intrinsicHeight2 + DJIBaseActivity.screenHeight) / 2) + 5));
                        return;
                    }
                    return;
                case 32768:
                    if (!b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                        if (message.arg1 == 0) {
                            DJIPreviewActivity.X(dJIPreviewActivity).hide();
                            DJIPreviewActivity.Y(dJIPreviewActivity).hide();
                            DJIPreviewActivity.W(dJIPreviewActivity).hide();
                            return;
                        } else if (DJISwitchModeView.a == a.METER) {
                            if (DJIPreviewActivity.Z(dJIPreviewActivity) == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                                DJIPreviewActivity.a(dJIPreviewActivity, DataCameraGetPushShotParams.getInstance().getExposureMode());
                                if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                                    i = 1;
                                }
                                DJIPreviewActivity.e(dJIPreviewActivity, i);
                            }
                            if (DJIPreviewActivity.Z(dJIPreviewActivity) != ExposureMode.i && ExposureMode.e != DJIPreviewActivity.Z(dJIPreviewActivity) && DJIPreviewActivity.aa(dJIPreviewActivity) == 0) {
                                if (DataCameraGetPushShotParams.getInstance().getMetering() != 2 || message.arg2 == 1) {
                                    DJIPreviewActivity.X(dJIPreviewActivity).hide();
                                } else {
                                    DJIPreviewActivity.X(dJIPreviewActivity).show();
                                }
                                DJIPreviewActivity.W(dJIPreviewActivity).show();
                                DJIPreviewActivity.Y(dJIPreviewActivity).show();
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                case 36864:
                    return;
                case 36865:
                    DJIPreviewActivity.ab(dJIPreviewActivity).a();
                    return;
                case 36866:
                    DJIGimbalType dJIGimbalType = DJIGimbalType.OTHER;
                    if (DataGimbalGetPushType.getInstance().isGetted()) {
                        dJIGimbalType = DataGimbalGetPushType.getInstance().getType();
                    }
                    if (!b.r(null) || (r1 != DJIGimbalType.Ronin && r1 != DJIGimbalType.Z15)) {
                        DJIPreviewActivity.ac(dJIPreviewActivity);
                        return;
                    }
                    return;
                case 36867:
                    DJIPreviewActivity.ad(dJIPreviewActivity);
                    return;
                case 36868:
                    dJIPreviewActivity.r = false;
                    return;
                case 36869:
                    DJIPreviewActivity.ae(dJIPreviewActivity);
                    DJILogHelper.getInstance().LOGD("", "lose_osd osdchecktime=" + DJIPreviewActivity.af(dJIPreviewActivity) + " osdstatus=" + dJIPreviewActivity.r, false, true);
                    if (DJIPreviewActivity.af(dJIPreviewActivity) == 9) {
                        dJIPreviewActivity.a(0);
                        return;
                    } else if (dJIPreviewActivity.r) {
                        sendEmptyMessageDelayed(36869, 1000);
                        return;
                    } else {
                        dJIPreviewActivity.a(1);
                        return;
                    }
                case 36870:
                    DJIPreviewActivity.ag(dJIPreviewActivity);
                    return;
                case 36872:
                    dJIPreviewActivity.a(true);
                    return;
                case 36874:
                    dji.pilot.fpv.control.i.getInstance().a();
                    return;
                default:
                    return;
            }
        }
    }
}
