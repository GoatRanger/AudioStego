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

final class DJIPreviewActivityLitchi$a extends Handler {
    private final WeakReference<DJIPreviewActivityLitchi> a;

    public DJIPreviewActivityLitchi$a(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIPreviewActivityLitchi);
    }

    public void handleMessage(Message message) {
        int i = 0;
        if (this.a != null) {
            DJIPreviewActivityLitchi dJIPreviewActivityLitchi = (DJIPreviewActivityLitchi) this.a.get();
            if (dJIPreviewActivityLitchi != null && !dJIPreviewActivityLitchi.isFinishing()) {
                switch (message.what) {
                    case 4096:
                        DJIPreviewActivityLitchi.L(dJIPreviewActivityLitchi);
                        return;
                    case 8192:
                        DJIPreviewActivityLitchi.g(dJIPreviewActivityLitchi, true);
                        return;
                    case e.ap /*12288*/:
                        if (message.arg1 != 0) {
                            DJIPreviewActivityLitchi.a(dJIPreviewActivityLitchi, message.arg1, message.arg2);
                            return;
                        } else if (i.getInstance().c() != ProductType.litchiS || i.getInstance().c() != ProductType.litchiC) {
                            DJIPreviewActivityLitchi.M(dJIPreviewActivityLitchi);
                            return;
                        } else {
                            return;
                        }
                    case 16384:
                        dJIPreviewActivityLitchi.l();
                        DJIPreviewActivityLitchi.g(dJIPreviewActivityLitchi, false);
                        return;
                    case 20480:
                        PointF pointF = (PointF) message.obj;
                        DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).setBackgroundResource(R.drawable.fpv_spot_metering);
                        Drawable background = DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).getBackground();
                        float intrinsicWidth = (((float) background.getIntrinsicWidth()) * 1.0f) / 2.0f;
                        float intrinsicHeight = (((float) background.getIntrinsicHeight()) * 1.0f) / 2.0f;
                        DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).setX(pointF.x - intrinsicWidth);
                        DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).setY(pointF.y - intrinsicHeight);
                        DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).setX((intrinsicWidth + pointF.x) - ((float) DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).getWidth()));
                        DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).setY(pointF.y - intrinsicHeight);
                        DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).setX(pointF.x - ((float) (DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).getWidth() / 2)));
                        DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).setY((pointF.y + intrinsicHeight) + 5.0f);
                        if (DJIPreviewActivityLitchi.Q(dJIPreviewActivityLitchi) == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                            DJIPreviewActivityLitchi.a(dJIPreviewActivityLitchi, DataCameraGetPushShotParams.getInstance().getExposureMode());
                            DJIPreviewActivityLitchi.e(dJIPreviewActivityLitchi, DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0);
                        }
                        if (DJIPreviewActivityLitchi.Q(dJIPreviewActivityLitchi) != ExposureMode.i && ExposureMode.e != DJIPreviewActivityLitchi.Q(dJIPreviewActivityLitchi) && DJIPreviewActivityLitchi.R(dJIPreviewActivityLitchi) == 0) {
                            DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).animShow();
                            DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).animShow();
                            DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).animShow();
                            return;
                        }
                        return;
                    case e.ax /*24576*/:
                        DJIPreviewActivityLitchi.h(dJIPreviewActivityLitchi, true);
                        return;
                    case 28672:
                        DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).hide();
                        DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).setBackgroundResource(R.drawable.fpv_center_metering);
                        Drawable background2 = DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).getBackground();
                        int intrinsicWidth2 = background2.getIntrinsicWidth();
                        int intrinsicHeight2 = background2.getIntrinsicHeight();
                        DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).setX((float) ((DJIBaseActivity.screenWidth - intrinsicWidth2) / 2));
                        DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).setY((float) ((DJIBaseActivity.screenHeight - intrinsicHeight2) / 2));
                        DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).setX((float) ((DJIBaseActivity.screenWidth - DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).getWidth()) / 2));
                        DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).setY((float) (((intrinsicHeight2 + DJIBaseActivity.screenHeight) / 2) + 5));
                        return;
                    case 32768:
                        if (message.arg1 == 0) {
                            DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).hide();
                            DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).hide();
                            DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).hide();
                            return;
                        }
                        if (DJIPreviewActivityLitchi.Q(dJIPreviewActivityLitchi) == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                            DJIPreviewActivityLitchi.a(dJIPreviewActivityLitchi, DataCameraGetPushShotParams.getInstance().getExposureMode());
                            if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                                i = 1;
                            }
                            DJIPreviewActivityLitchi.e(dJIPreviewActivityLitchi, i);
                        }
                        if (DJIPreviewActivityLitchi.Q(dJIPreviewActivityLitchi) != ExposureMode.i && ExposureMode.e != DJIPreviewActivityLitchi.Q(dJIPreviewActivityLitchi) && DJIPreviewActivityLitchi.R(dJIPreviewActivityLitchi) == 0) {
                            if (DataCameraGetPushShotParams.getInstance().getMetering() != 2 || message.arg2 == 1) {
                                DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).hide();
                            } else {
                                DJIPreviewActivityLitchi.O(dJIPreviewActivityLitchi).show();
                            }
                            DJIPreviewActivityLitchi.N(dJIPreviewActivityLitchi).show();
                            DJIPreviewActivityLitchi.P(dJIPreviewActivityLitchi).show();
                            return;
                        }
                        return;
                    case 36864:
                        dJIPreviewActivityLitchi.k();
                        return;
                    case 36865:
                        DJIPreviewActivityLitchi.S(dJIPreviewActivityLitchi).a();
                        return;
                    case 36866:
                        DJIPreviewActivityLitchi.T(dJIPreviewActivityLitchi);
                        return;
                    case 36867:
                        DJIPreviewActivityLitchi.U(dJIPreviewActivityLitchi);
                        return;
                    case 36868:
                        dJIPreviewActivityLitchi.q = false;
                        return;
                    case 36869:
                        DJIPreviewActivityLitchi.V(dJIPreviewActivityLitchi);
                        DJILogHelper.getInstance().LOGD("", "lose_osd osdchecktime=" + DJIPreviewActivityLitchi.W(dJIPreviewActivityLitchi) + " osdstatus=" + dJIPreviewActivityLitchi.q, false, true);
                        if (DJIPreviewActivityLitchi.W(dJIPreviewActivityLitchi) == 9) {
                            dJIPreviewActivityLitchi.a(0);
                            return;
                        } else if (dJIPreviewActivityLitchi.q) {
                            sendEmptyMessageDelayed(36869, 1000);
                            return;
                        } else {
                            dJIPreviewActivityLitchi.a(1);
                            return;
                        }
                    case 36870:
                        DJIPreviewActivityLitchi.X(dJIPreviewActivityLitchi);
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
