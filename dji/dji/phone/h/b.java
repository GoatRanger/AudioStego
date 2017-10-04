package dji.phone.h;

public enum b {
    ROTATION_0(0, 270.0f),
    ROTATION_90(90, 180.0f),
    ROTATION_180(180, 90.0f),
    ROTATION_270(270, 0.0f),
    ROTATION_UNKNOWN(-1, -1.0f);
    
    private int f;
    private float g;

    private b(int i, float f) {
        this.f = i;
        this.g = f;
    }

    public float a() {
        return this.g;
    }

    public int b() {
        return this.f;
    }

    public boolean a(int i) {
        return this.f == i;
    }

    public boolean a(float f) {
        return this.g == f;
    }

    public static b findByOrientation(float f) {
        b bVar = ROTATION_UNKNOWN;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].a(f)) {
                return values()[i];
            }
        }
        return bVar;
    }

    public static b findByRotation(int i) {
        b bVar = ROTATION_UNKNOWN;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].a(i)) {
                return values()[i2];
            }
        }
        return bVar;
    }
}
