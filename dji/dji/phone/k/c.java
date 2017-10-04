package dji.phone.k;

public class c {
    private static final String a = "RotationUtil";

    public static float a(int i) {
        float f = (float) i;
        if (f == 0.0f || f == 90.0f) {
            return (float) i;
        }
        if (f == 270.0f) {
            return 90.0f;
        }
        if (f == 180.0f) {
            return 0.0f;
        }
        return 0.0f;
    }
}
