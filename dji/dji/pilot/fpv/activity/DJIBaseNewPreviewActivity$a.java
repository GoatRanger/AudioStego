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
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.protocol.e;
import java.lang.ref.WeakReference;

final class DJIBaseNewPreviewActivity$a extends Handler {
    private final WeakReference<DJIBaseNewPreviewActivity> a;

    public DJIBaseNewPreviewActivity$a(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIBaseNewPreviewActivity);
    }

    public void handleMessage(Message message) {
        int i = 0;
        if (this.a != null) {
            DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity = (DJIBaseNewPreviewActivity) this.a.get();
            if (dJIBaseNewPreviewActivity != null && !dJIBaseNewPreviewActivity.isFinishing()) {
                switch (message.what) {
                    case 4096:
                        DJIBaseNewPreviewActivity.A(dJIBaseNewPreviewActivity);
                        return;
                    case 8192:
                        DJIBaseNewPreviewActivity.c(dJIBaseNewPreviewActivity, true);
                        return;
                    case e.ap /*12288*/:
                        if (!hasMessages(e.ap)) {
                            if (message.arg1 != 0) {
                                dJIBaseNewPreviewActivity.f();
                                return;
                            } else if (i.getInstance().c() != ProductType.litchiS || i.getInstance().c() != ProductType.litchiC) {
                                dJIBaseNewPreviewActivity.f();
                                return;
                            } else {
                                return;
                            }
                        }
                        return;
                    case 16384:
                        dJIBaseNewPreviewActivity.l();
                        DJIBaseNewPreviewActivity.c(dJIBaseNewPreviewActivity, false);
                        return;
                    case 20480:
                        PointF pointF = (PointF) message.obj;
                        DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).setBackgroundResource(R.drawable.fpv_spot_metering);
                        Drawable background = DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).getBackground();
                        float intrinsicWidth = (((float) background.getIntrinsicWidth()) * 1.0f) / 2.0f;
                        float intrinsicHeight = (((float) background.getIntrinsicHeight()) * 1.0f) / 2.0f;
                        DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).setX(pointF.x - intrinsicWidth);
                        DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).setY(pointF.y - intrinsicHeight);
                        DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).setX((intrinsicWidth + pointF.x) - ((float) DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).getWidth()));
                        DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).setY(pointF.y - intrinsicHeight);
                        DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).setX(pointF.x - ((float) (DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).getWidth() / 2)));
                        DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).setY((pointF.y + intrinsicHeight) + 5.0f);
                        if (DJIBaseNewPreviewActivity.E(dJIBaseNewPreviewActivity) == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                            DJIBaseNewPreviewActivity.a(dJIBaseNewPreviewActivity, DataCameraGetPushShotParams.getInstance().getExposureMode());
                            DJIBaseNewPreviewActivity.e(dJIBaseNewPreviewActivity, DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0);
                        }
                        if (DJIBaseNewPreviewActivity.E(dJIBaseNewPreviewActivity) != ExposureMode.i && ExposureMode.e != DJIBaseNewPreviewActivity.E(dJIBaseNewPreviewActivity) && DJIBaseNewPreviewActivity.F(dJIBaseNewPreviewActivity) == 0) {
                            DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).animShow();
                            DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).animShow();
                            DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).animShow();
                            return;
                        }
                        return;
                    case e.ax /*24576*/:
                        DJIBaseNewPreviewActivity.d(dJIBaseNewPreviewActivity, true);
                        return;
                    case 28672:
                        DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).hide();
                        DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).setBackgroundResource(R.drawable.fpv_center_metering);
                        Drawable background2 = DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).getBackground();
                        int intrinsicWidth2 = background2.getIntrinsicWidth();
                        int intrinsicHeight2 = background2.getIntrinsicHeight();
                        DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).setX((float) ((DJIBaseActivity.screenWidth - intrinsicWidth2) / 2));
                        DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).setY((float) ((DJIBaseActivity.screenHeight - intrinsicHeight2) / 2));
                        DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).setX((float) ((DJIBaseActivity.screenWidth - DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).getWidth()) / 2));
                        DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).setY((float) (((intrinsicHeight2 + DJIBaseActivity.screenHeight) / 2) + 5));
                        return;
                    case 32768:
                        if (message.arg1 == 0) {
                            DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).hide();
                            DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).hide();
                            DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).hide();
                            return;
                        }
                        if (DJIBaseNewPreviewActivity.E(dJIBaseNewPreviewActivity) == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                            DJIBaseNewPreviewActivity.a(dJIBaseNewPreviewActivity, DataCameraGetPushShotParams.getInstance().getExposureMode());
                            if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                                i = 1;
                            }
                            DJIBaseNewPreviewActivity.e(dJIBaseNewPreviewActivity, i);
                        }
                        if (DJIBaseNewPreviewActivity.E(dJIBaseNewPreviewActivity) != ExposureMode.i && ExposureMode.e != DJIBaseNewPreviewActivity.E(dJIBaseNewPreviewActivity) && DJIBaseNewPreviewActivity.F(dJIBaseNewPreviewActivity) == 0) {
                            if (DataCameraGetPushShotParams.getInstance().getMetering() != 2 || message.arg2 == 1) {
                                DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).hide();
                            } else {
                                DJIBaseNewPreviewActivity.C(dJIBaseNewPreviewActivity).show();
                            }
                            DJIBaseNewPreviewActivity.B(dJIBaseNewPreviewActivity).show();
                            DJIBaseNewPreviewActivity.D(dJIBaseNewPreviewActivity).show();
                            return;
                        }
                        return;
                    case 36864:
                        dJIBaseNewPreviewActivity.C();
                        return;
                    case 36865:
                        DJIBaseNewPreviewActivity.G(dJIBaseNewPreviewActivity).a();
                        return;
                    case 36866:
                        DJIBaseNewPreviewActivity.H(dJIBaseNewPreviewActivity);
                        return;
                    case 36867:
                        DJIBaseNewPreviewActivity.I(dJIBaseNewPreviewActivity);
                        return;
                    case 36868:
                        dJIBaseNewPreviewActivity.T = false;
                        return;
                    case 36869:
                        DJIBaseNewPreviewActivity.J(dJIBaseNewPreviewActivity);
                        DJILogHelper.getInstance().LOGD("", "lose_osd osdchecktime=" + DJIBaseNewPreviewActivity.K(dJIBaseNewPreviewActivity) + " osdstatus=" + dJIBaseNewPreviewActivity.T, false, true);
                        if (DJIBaseNewPreviewActivity.K(dJIBaseNewPreviewActivity) == 9) {
                            dJIBaseNewPreviewActivity.b(0);
                            return;
                        } else if (dJIBaseNewPreviewActivity.T) {
                            sendEmptyMessageDelayed(36869, 1000);
                            return;
                        } else {
                            dJIBaseNewPreviewActivity.b(1);
                            return;
                        }
                    case 36870:
                        dJIBaseNewPreviewActivity.i();
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
