package lecho.lib.hellocharts.h;

public class c {
    public static final int[] a = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

    public static float a(float f) {
        if (Float.isNaN(f) || f == Float.POSITIVE_INFINITY) {
            return f;
        }
        float f2 = f + 0.0f;
        return Float.intBitsToFloat((f2 >= 0.0f ? 1 : -1) + Float.floatToRawIntBits(f2));
    }

    public static float b(float f) {
        if (Float.isNaN(f) || f == Float.NEGATIVE_INFINITY) {
            return f;
        }
        if (f == 0.0f) {
            return -1.4E-45f;
        }
        return Float.intBitsToFloat((f > 0.0f ? -1 : 1) + Float.floatToRawIntBits(f));
    }

    public static double a(double d) {
        if (Double.isNaN(d) || d == Double.POSITIVE_INFINITY) {
            return d;
        }
        double d2 = d + 0.0d;
        return Double.longBitsToDouble(((long) (d2 >= 0.0d ? 1 : -1)) + Double.doubleToRawLongBits(d2));
    }

    public static double b(double d) {
        if (Double.isNaN(d) || d == Double.NEGATIVE_INFINITY) {
            return d;
        }
        if (d == 0.0d) {
            return -1.401298464324817E-45d;
        }
        return Double.longBitsToDouble(((long) (d > 0.0d ? -1 : 1)) + Double.doubleToRawLongBits(d));
    }

    public static boolean a(float f, float f2, float f3, float f4) {
        float abs = Math.abs(f - f2);
        if (abs <= f3) {
            return true;
        }
        float abs2 = Math.abs(f);
        float abs3 = Math.abs(f2);
        if (abs2 <= abs3) {
            abs2 = abs3;
        }
        if (abs > abs2 * f4) {
            return false;
        }
        return true;
    }

    public static float c(double d) {
        double d2;
        if (d < 0.0d) {
            d2 = -d;
        } else {
            d2 = d;
        }
        float pow = (float) Math.pow(10.0d, (double) (1 - ((int) ((float) Math.ceil((double) ((float) Math.log10(d2)))))));
        return ((float) Math.round(((double) pow) * d)) / pow;
    }

    public static int a(char[] cArr, float f, int i, int i2, char c) {
        if (i2 >= a.length) {
            cArr[i - 1] = '.';
            return 1;
        }
        Object obj = null;
        if (f == 0.0f) {
            cArr[i - 1] = '0';
            return 1;
        }
        int i3;
        if (f < 0.0f) {
            obj = 1;
            f = -f;
        }
        if (i2 > a.length) {
            i2 = a.length - 1;
        }
        long round = (long) Math.round(((float) a[i2]) * f);
        int i4 = i - 1;
        int i5 = 0;
        while (true) {
            if (round == 0 && i5 >= i2 + 1) {
                break;
            }
            long j = round / 10;
            int i6 = i4 - 1;
            cArr[i4] = (char) (((int) (round % 10)) + 48);
            i5++;
            if (i5 == i2) {
                i3 = i6 - 1;
                cArr[i6] = c;
                i5++;
            } else {
                i3 = i6;
            }
            i4 = i3;
            round = j;
        }
        if (cArr[i4 + 1] == c) {
            i3 = i4 - 1;
            cArr[i4] = '0';
            i5++;
        } else {
            i3 = i4;
        }
        if (obj == null) {
            return i5;
        }
        int i7 = i3 - 1;
        cArr[i3] = '-';
        return i5 + 1;
    }

    public static void a(float f, float f2, int i, a aVar) {
        double d = (double) (f2 - f);
        if (i == 0 || d <= 0.0d) {
            aVar.a = new float[0];
            aVar.b = 0;
            return;
        }
        d = (double) c(d / ((double) i));
        double pow = Math.pow(10.0d, (double) ((int) Math.log10(d)));
        if (((int) (d / pow)) > 5) {
            d = Math.floor(10.0d * pow);
        }
        pow = Math.ceil(((double) f) / d) * d;
        int i2 = 0;
        double d2 = pow;
        while (d2 <= a(Math.floor(((double) f2) / d) * d)) {
            d2 += d;
            i2++;
        }
        aVar.b = i2;
        if (aVar.a.length < i2) {
            aVar.a = new float[i2];
        }
        for (int i3 = 0; i3 < i2; i3++) {
            aVar.a[i3] = (float) pow;
            pow += d;
        }
        if (d < 1.0d) {
            aVar.c = (int) Math.ceil(-Math.log10(d));
        } else {
            aVar.c = 0;
        }
    }
}
