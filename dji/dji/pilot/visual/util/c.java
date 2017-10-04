package dji.pilot.visual.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.e.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushFovParam;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackException;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus.TrackMode;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataSingleSetPointPos.TapMode;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;
import dji.pilot.R;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.model.f;
import dji.pilot.fpv.model.o;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.publics.e.a;

public class c {
    public static final String a = "Visual";

    public static void a(String str) {
    }

    public static boolean a() {
        TrackMode c = dji.pilot.visual.a.c.getInstance().b().c();
        return (c == TrackMode.CONFIRM && b()) || TrackMode.LOST != c;
    }

    public static boolean b() {
        TrackException trackException = dji.pilot.visual.a.c.getInstance().b().d().f;
        return trackException == TrackException.NONE || TrackException.LOW_DETECT == trackException || TrackException.SHAKE == trackException || TrackException.RC_HALT == trackException || TrackException.APP_HALT == trackException || TrackException.TOO_CLOSE == trackException || TrackException.TOO_FAR == trackException;
    }

    public static boolean c() {
        ProductType c = i.getInstance().c();
        c$b a = dji.pilot.fpv.flightmode.c.getInstance().a();
        if (c$b.POINT == a) {
            if (ProductType.Pomato == c) {
                return true;
            }
            return false;
        } else if (c$b.TRACK == a) {
            if ((ProductType.Pomato == c || a.c(c)) && a()) {
                return true;
            }
            return false;
        } else if (c$b.TRACK_SELFIE != a) {
            return false;
        } else {
            return true;
        }
    }

    public static int d() {
        ProductType c = i.getInstance().c();
        c$b a = dji.pilot.fpv.flightmode.c.getInstance().a();
        if (c$b.POINT == a) {
            if (ProductType.Pomato == c) {
                return R.drawable.mini_point;
            }
            return 0;
        } else if (c$b.TRACK == a) {
            if ((ProductType.Pomato == c || a.c(c)) && a()) {
                return R.drawable.mini_track;
            }
            return 0;
        } else if (c$b.TRACK_SELFIE == a) {
            return R.drawable.mini_selfie;
        } else {
            return 0;
        }
    }

    public static boolean e() {
        return DataFlycGetPushAvoidParam.getInstance().isUserAvoidEnable();
    }

    public static boolean a(float f, float f2) {
        return ((double) Math.abs(f - f2)) >= 0.005d;
    }

    public static String a(Context context, SensorType sensorType) {
        if (SensorType.Front == sensorType) {
            return context.getString(R.string.vision_type_front);
        }
        if (SensorType.Back == sensorType) {
            return context.getString(R.string.vision_type_back);
        }
        if (SensorType.Left == sensorType) {
            return context.getString(R.string.vision_type_left);
        }
        if (SensorType.Right == sensorType) {
            return context.getString(R.string.vision_type_right);
        }
        return "";
    }

    public static o.a a(SensorType sensorType) {
        ProductType c = i.getInstance().c();
        if (SensorType.Front == sensorType) {
            return a.l(c);
        }
        if (SensorType.Back == sensorType) {
            return a.m(c);
        }
        if (SensorType.Left == sensorType) {
            return a.n(c);
        }
        if (SensorType.Right == sensorType) {
            return a.o(c);
        }
        if (SensorType.Top == sensorType) {
            return a.q(c);
        }
        if (SensorType.Bottom == sensorType) {
            return a.p(c);
        }
        return o.a.NON;
    }

    private static float a(MODE mode) {
        if (mode == MODE.TAKEPHOTO) {
            return 82.0f;
        }
        if (mode != MODE.RECORD) {
            return g();
        }
        int videoFormat = DataCameraGetPushShotParams.getInstance().getVideoFormat();
        int videoFps = DataCameraGetPushShotParams.getInstance().getVideoFps();
        if (videoFormat == 20 || videoFormat == 22) {
            return g();
        }
        if (videoFormat == 24 || videoFormat == 14 || videoFormat == 16 || videoFormat == 18) {
            return 82.0f;
        }
        if (videoFormat == 10 && videoFps == 7) {
            return 41.0f;
        }
        return f.a;
    }

    private static float b(MODE mode) {
        if (mode == MODE.TAKEPHOTO) {
            return 64.0f;
        }
        if (mode != MODE.RECORD) {
            return g();
        }
        int videoFormat = DataCameraGetPushShotParams.getInstance().getVideoFormat();
        if (videoFormat == 20 || videoFormat == 22) {
            return g();
        }
        return 62.0f;
    }

    private static float c(MODE mode) {
        if (mode == MODE.TAKEPHOTO) {
            RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
            if (RatioType.R_4_3 == imageRatio) {
                return 68.0f;
            }
            if (RatioType.R_16_9 == imageRatio) {
                return 72.5f;
            }
            return g();
        } else if (mode != MODE.RECORD) {
            return g();
        } else {
            int videoFormat = DataCameraGetPushShotParams.getInstance().getVideoFormat();
            int videoFps = DataCameraGetPushShotParams.getInstance().getVideoFps();
            if (videoFormat == 20 || videoFormat == 22) {
                return 68.0f;
            }
            if (videoFormat == 24 || videoFormat == 14 || videoFormat == 16 || videoFormat == 18) {
                return 72.5f;
            }
            if (videoFormat == 10 && videoFps == 7) {
                return 41.0f;
            }
            return 68.0f;
        }
    }

    public static float f() {
        if (DataCameraGetPushFovParam.getInstance().hasFovData()) {
            return DataCameraGetPushFovParam.getInstance().getFovH();
        }
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        ProductType c = i.getInstance().c();
        if (a.c(c)) {
            return b(mode);
        }
        if (c == ProductType.Pomato) {
            return c(mode);
        }
        return a(mode);
    }

    public static float g() {
        if (DataCameraGetPushFovParam.getInstance().hasFovData()) {
            return DataCameraGetPushFovParam.getInstance().getFovH();
        }
        ProductType c = i.getInstance().c();
        if (a.c(c)) {
            return 66.0f;
        }
        if (c == ProductType.Pomato) {
            return 74.0f;
        }
        return 84.0f;
    }

    public static float a(RatioType ratioType) {
        if (DataCameraGetPushFovParam.getInstance().hasFovData()) {
            return DataCameraGetPushFovParam.getInstance().getFovV();
        }
        float f = f();
        if (ratioType == RatioType.R_4_3) {
            return (f * 3.0f) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
        }
        if (ratioType == RatioType.R_3_2) {
            return (f * 2.0f) / 3.0f;
        }
        return (f * 9.0f) / b.a;
    }

    public static float a(ProductType productType) {
        if (ProductType.Pomato == productType) {
            return 5.0f;
        }
        return 0.0f;
    }

    public static boolean b(ProductType productType) {
        return productType == ProductType.Pomato || a.c(productType);
    }

    public static boolean c(ProductType productType) {
        return productType == ProductType.Pomato || a.c(productType);
    }

    public static boolean d(ProductType productType) {
        return productType == ProductType.Pomato;
    }

    public static boolean a(TrackingMode trackingMode) {
        return TrackingMode.a == trackingMode;
    }

    public static boolean a(TapMode tapMode) {
        return (TapMode.d == tapMode || TapMode.c == tapMode) ? false : true;
    }

    public static boolean e(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return c$b.TRACK == dji.pilot.fpv.flightmode.c.getInstance().a() && (productType == ProductType.Pomato || a.c(productType));
    }

    public static boolean h() {
        c$b a = dji.pilot.fpv.flightmode.c.getInstance().a();
        if (a == null || (!a.equals(c$b.POINT) && !a.equals(c$b.TRACK) && !a.equals(c$b.TRACK_SELFIE))) {
            return true;
        }
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.a = d.b;
        bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
        bVar.b = R.string.gs_hint_no_portrait_in_tracking;
        dji.thirdparty.a.c.a().e(bVar);
        return false;
    }

    public static boolean a(Context context, Runnable runnable) {
        DataEyeGetPushException instance = DataEyeGetPushException.getInstance();
        if (!ServiceManager.getInstance().f()) {
            DJIErrorPopView.b.b(d.b, R.string.visual_track_start_failed, R.string.visual_point_novideo, dji.pilot.fpv.view.DJIErrorPopView.c.a, DJIErrorPopView.f.a);
            return false;
        } else if (e()) {
            if (DataOsdGetPushCommon.getInstance().groundOrSky() != 2) {
                DJIErrorPopView.b.b(d.b, R.string.visual_track_exception_ground, 0, dji.pilot.fpv.view.DJIErrorPopView.c.a, DJIErrorPopView.f.a);
                return false;
            } else if (instance.isFronImageOverExposure()) {
                DJIErrorPopView.b.b(d.b, R.string.visual_point_over_exposure, 0, dji.pilot.fpv.view.DJIErrorPopView.c.a, DJIErrorPopView.f.a);
                return false;
            } else if (!instance.isFronImageUnderExposure()) {
                return true;
            } else {
                DJIErrorPopView.b.b(d.b, R.string.visual_point_under_exposure, 0, dji.pilot.fpv.view.DJIErrorPopView.c.a, DJIErrorPopView.f.a);
                return false;
            }
        } else if (runnable == null) {
            dji.pilot.publics.widget.b.a(context, (int) R.string.visual_point_need_avoid_alert_title, (int) R.string.visual_track_need_avoid_alert_content, (int) R.string.app_cancel, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.app_enter, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dji.pilot.visual.a.c.getInstance().b(true);
                    dialogInterface.dismiss();
                }
            }).show();
            return false;
        } else {
            runnable.run();
            return false;
        }
    }
}
