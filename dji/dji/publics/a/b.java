package dji.publics.a;

public class b {
    public static float a(float f, float f2) {
        if (f > 0.0f) {
            return Math.min(f2, f);
        }
        return Math.max(-f2, f);
    }
}
