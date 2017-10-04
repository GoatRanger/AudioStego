package dji.midware.data.b.a;

public class a {
    private static int a = 0;
    private static int b = 0;

    public static synchronized int getSeq() {
        int i;
        synchronized (a.class) {
            a++;
            if (a == 85) {
                a++;
            } else if (a >= 65535) {
                a = 0;
            }
            i = a;
        }
        return i;
    }

    public static synchronized int getSessionId() {
        int i;
        synchronized (a.class) {
            b++;
            if (b == 85) {
                b++;
            } else if (b >= 65535) {
                b = 0;
            }
            i = b;
        }
        return i;
    }

    public static synchronized int sessionId() {
        int i;
        synchronized (a.class) {
            i = b;
        }
        return i;
    }
}
