package dji.device.common.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.location.Location;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.a.c;
import dji.device.camera.a.e;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.logic.f.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot.fpv.R;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Pattern;

public class a {
    public static final int a = 60;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static float e = 0.0f;
    private static int f = 3;
    private static boolean g = false;
    private static boolean h = false;
    private static final int i = 1;
    private static volatile int j = 0;
    private static final DecimalFormat k = new DecimalFormat("#,###");
    private static int l = 0;
    private static final float[] m = new float[2];
    private static final float n = 100000.0f;
    private static RatioType o = RatioType.R_16_9;
    private static RatioType p = RatioType.R_16_9;

    public static boolean a() {
        boolean z = true;
        if (j >= 1) {
            z = false;
        }
        if (z) {
            j++;
        }
        return z;
    }

    public static float a(double d, double d2, double d3, double d4) {
        Arrays.fill(m, 0.0f);
        Location.distanceBetween(d, d2, d3, d4, m);
        if (m[0] <= 0.0f || m[0] > n) {
            m[0] = 0.0f;
        }
        return m[0];
    }

    public static boolean a(SDCardState sDCardState) {
        if (SDCardState.Normal == sDCardState || SDCardState.Slow == sDCardState) {
            return true;
        }
        return false;
    }

    public static int b(SDCardState sDCardState) {
        int i = R.string.sdcardstatus_normal;
        if (sDCardState == SDCardState.None) {
            return R.string.sdcardstatus_removal;
        }
        if (sDCardState == SDCardState.Invalid) {
            return R.string.sdcardstatus_invalid;
        }
        if (sDCardState == SDCardState.WriteProtection) {
            return R.string.sdcardstatus_write_protect;
        }
        if (sDCardState == SDCardState.Unformat) {
            return R.string.sdcardstatus_not_formated;
        }
        if (sDCardState == SDCardState.Formating) {
            return R.string.sdcardstatus_formating;
        }
        if (sDCardState == SDCardState.Illegal) {
            return R.string.sdcardstatus_invalid_filesystem;
        }
        if (sDCardState == SDCardState.Busy) {
            return R.string.sdcardstatus_busy;
        }
        if (sDCardState == SDCardState.Full) {
            return R.string.sdcardstatus_full;
        }
        if (sDCardState == SDCardState.Slow) {
            return R.string.sdcardstatus_slow;
        }
        if (sDCardState == SDCardState.Unknow) {
            return R.string.sdcardstatus_unknown_error;
        }
        if (sDCardState == SDCardState.IndexMax) {
            return R.string.sdcardstatus_full;
        }
        if (sDCardState == SDCardState.Initialzing) {
            return R.string.sdcardstatus_initial;
        }
        if (sDCardState == SDCardState.ToFormat) {
            return R.string.sdcardstatus_toformat;
        }
        if (sDCardState == SDCardState.Slow) {
            return R.string.sdcardstatus_slow;
        }
        return i;
    }

    public static boolean b() {
        if (!b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) || e.getInstance().b() == dji.device.camera.a.e.a.SLOWMOTION_1080 || DJICameraDataManagerCompat.getInstance().isCur4kVideo() || c.getInstance().c() == c.b.PANO || DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
            return false;
        }
        return true;
    }

    public static String a(String str) {
        ProductType c = i.getInstance().c();
        if (c == ProductType.Orange || c == ProductType.BigBanana || c == ProductType.OrangeRAW || c == ProductType.None || c == ProductType.OTHER) {
            return str;
        }
        return c.toString() + str;
    }

    public static boolean b(String str) {
        String str2 = "[A-Z0-9a-z-_ ]{1,32}";
        return Pattern.compile("[A-Z0-9a-z-_ ]{1,32}").matcher(str).matches();
    }

    public static boolean c(String str) {
        String str2 = "[A-Z0-9a-z]{8,63}";
        return Pattern.compile("[A-Z0-9a-z]{8,63}").matcher(str).matches();
    }

    public static int a(int i, int i2) {
        if (i > 0 && i <= 100) {
            return ((int) ((((float) (i - 1)) * ((float) i2)) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) + 1;
        }
        if (i <= 100) {
            return 0;
        }
        return i2;
    }

    private static Shape c() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    public static String a(int i, Context context) {
        int[] a = c.a(i);
        return context.getString(R.string.fpv_videotime, new Object[]{Integer.valueOf(a[2]), Integer.valueOf(a[1]), Integer.valueOf(a[0])});
    }

    public static int a(Context context) {
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        float b = b(context);
        if (Math.abs(b - dji.midware.util.a.b.a) < Math.abs(b - dji.midware.util.a.b.b)) {
            o = RatioType.R_16_9;
        } else {
            o = RatioType.R_4_3;
        }
        if (o == RatioType.R_16_9) {
            return (int) (((float) DJIPreviewActivityLongan.mScreenWidth) / dji.midware.util.a.b.a);
        }
        return (int) (((((float) i2) * 1.0f) / ((float) i)) * ((float) DJIPreviewActivityLongan.mScreenWidth));
    }

    @SuppressLint({"NewApi"})
    private static float b(Context context) {
        int i;
        int i2;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        int i3;
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i >= i2) {
                i3 = i2;
                i2 = i;
                i = i3;
            }
        } else {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            i3 = point.x > point.y ? point.y : point.x;
            i2 = point.x > point.y ? point.x : point.y;
            i = i3;
        }
        return (((float) i2) * 1.0f) / ((float) i);
    }
}
