package dji.pilot.visual.util;

import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetPushFovParam;
import dji.midware.util.a.b;
import dji.pilot.fpv.camera.a.a;
import java.util.Arrays;
import lecho.lib.hellocharts.model.h;

public class d {
    public static final float a = 0.017453292f;
    public static final float b = 57.29578f;
    private static float[] c = new float[3];
    private static float[] d = new float[9];
    private static float[] e = new float[2];

    public static void a(float f, float f2, float f3) {
        c[0] = f;
        c[1] = f2;
        c[2] = f3;
        a(c, d);
    }

    public static void a(float[] fArr, float[] fArr2) {
        float sin = (float) Math.sin((double) fArr[2]);
        float cos = (float) Math.cos((double) fArr[2]);
        float sin2 = (float) Math.sin((double) fArr[1]);
        float cos2 = (float) Math.cos((double) fArr[1]);
        float sin3 = (float) Math.sin((double) fArr[0]);
        float cos3 = (float) Math.cos((double) fArr[0]);
        fArr2[0] = (cos2 * cos3) - ((sin * sin2) * sin3);
        fArr2[3] = (cos2 * sin3) + ((sin * sin2) * cos3);
        fArr2[6] = (-sin2) * cos;
        fArr2[1] = (-cos) * sin3;
        fArr2[4] = cos * cos3;
        fArr2[7] = sin;
        fArr2[2] = (sin2 * cos3) + ((sin * cos2) * sin3);
        fArr2[5] = (sin2 * sin3) - ((sin * cos2) * cos3);
        fArr2[8] = cos * cos2;
    }

    public static float[] a(float[] fArr, RatioType ratioType, boolean z) {
        float f = (float) (((double) (((d[0] * fArr[0]) + (d[3] * fArr[1])) + (d[6] * fArr[2]))) + 1.0E-8d);
        float f2 = (d[7] * fArr[2]) + ((d[1] * fArr[0]) + (d[4] * fArr[1]));
        float f3 = (d[8] * fArr[2]) + ((d[2] * fArr[0]) + (d[5] * fArr[1]));
        float f4 = 0.5625f;
        if (ratioType == RatioType.R_4_3) {
            f4 = h.l;
        } else if (ratioType == RatioType.R_3_2) {
            f4 = 0.6666667f;
        }
        float f5 = c.f();
        double atan = (Math.atan(Math.tan((double) ((dji.pilot.visual.a.d.c * f5) * a)) * ((double) f4)) * 57.295780181884766d) * 2.0d;
        e[0] = (float) ((1.0d + (((double) (f2 / f)) / Math.tan((((double) f5) * 0.5d) * 0.01745329238474369d))) * 0.5d);
        e[1] = (float) ((1.0d + (((double) (f3 / f)) / Math.tan((atan * 0.5d) * 0.01745329238474369d))) * 0.5d);
        a.a(null, "Pos-" + Arrays.toString(e));
        return e;
    }

    public static void a(float[] fArr, float f, float f2, RatioType ratioType, boolean z) {
        float tan = (float) (Math.tan((double) ((c.f() * a) / 2.0f)) / Math.tan((double) ((a * c.g()) / 2.0f)));
        float f3 = h.l;
        if (!DataCameraGetPushFovParam.getInstance().hasFovData()) {
            if (ratioType == RatioType.R_4_3) {
                f3 = h.l;
            } else if (ratioType == RatioType.R_3_2) {
                f3 = 0.6666667f;
            } else {
                f3 = 0.5625f;
            }
        }
        fArr[0] = (float) (((((double) f) - 0.5d) * ((double) tan)) + 0.5d);
        fArr[1] = (float) ((((double) tan) * ((((double) f2) - 0.5d) * ((double) (f3 * b.b)))) + 0.5d);
        if (fArr[0] < 0.0f) {
            fArr[0] = 0.0f;
        } else if (fArr[0] > 1.0f) {
            fArr[0] = 1.0f;
        }
        if (fArr[1] < 0.0f) {
            fArr[1] = 0.0f;
        } else if (fArr[1] > 1.0f) {
            fArr[1] = 1.0f;
        }
        a.a(null, "Before Pos-" + Arrays.toString(fArr));
    }
}
