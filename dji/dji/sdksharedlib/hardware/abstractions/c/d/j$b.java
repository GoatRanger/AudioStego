package dji.sdksharedlib.hardware.abstractions.c.d;

enum j$b {
    PHOTO_SHOOTING_STATE_INSTANCE;
    
    private static j$a b;
    private static boolean c;

    static {
        c = false;
    }

    public static j$b getInstance() {
        if (!c) {
            b = j$a.Idle;
            c = true;
        }
        return PHOTO_SHOOTING_STATE_INSTANCE;
    }

    public void a() {
        if (b == j$a.Idle) {
            b = j$a.Working;
        }
    }

    public void b() {
        if (b == j$a.Working) {
            b = j$a.Idle;
        }
    }
}
