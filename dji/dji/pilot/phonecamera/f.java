package dji.pilot.phonecamera;

public class f {
    private static b a;

    public static synchronized e a() {
        e eVar;
        synchronized (f.class) {
            if (a == null) {
                a = new b();
            }
            eVar = a;
        }
        return eVar;
    }
}
