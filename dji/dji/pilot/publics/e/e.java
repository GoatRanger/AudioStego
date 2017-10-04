package dji.pilot.publics.e;

public class e {
    public static final float a = 3.2808f;
    public static final float b = 2.2369f;
    public static final float c = 39.4f;
    public static final String d = "DjiFormat";
    public static final float e = 3.6f;
    public static final float f = 273.15f;

    public static final float a(float f) {
        return e * f;
    }

    public static final float b(float f) {
        return f - f;
    }

    public static final float c(float f) {
        return f + f;
    }

    public static final float d(float f) {
        return (1.8f * f) + 32.0f;
    }

    public static final float e(float f) {
        return (f - 32.0f) / 1.8f;
    }

    public static float f(float f) {
        return a * f;
    }

    public static float g(float f) {
        return f / a;
    }

    public static float h(float f) {
        return b * f;
    }

    public static float i(float f) {
        return f / b;
    }

    public static float j(float f) {
        if (c()) {
            return f;
        }
        return f(f);
    }

    public static float k(float f) {
        if (c()) {
            return g(f);
        }
        return f;
    }

    public static float l(float f) {
        if (c()) {
            return f;
        }
        return g(f);
    }

    public static float m(float f) {
        if (c()) {
            return f(f);
        }
        return f;
    }

    public static String a() {
        return c() ? "m" : "ft";
    }

    public static String b() {
        return c() ? "m/s" : "mile/h";
    }

    public static boolean c() {
        return true;
    }
}
