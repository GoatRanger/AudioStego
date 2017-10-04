package dji.gs.utils;

public class b {
    private static final float a = 1.0E-5f;
    private static final float b = 0.0025f;
    private static float c = 0.0f;
    private static float d = 1.0f;

    public static dji.gs.e.b a(double d, double d2) {
        if (c > b) {
            d = -1.0f;
        } else if (c < -0.0025f) {
            d = 1.0f;
        }
        c += d * a;
        return a.a(new dji.gs.e.b(((double) c) + d, ((double) c) + d2));
    }
}
