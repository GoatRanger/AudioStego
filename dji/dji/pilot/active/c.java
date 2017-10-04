package dji.pilot.active;

public class c {
    private static c a = null;
    private boolean[] b = new boolean[]{false, false, false, false, false, false};
    private boolean c = false;
    private int d = 0;
    private String e = "";

    public enum a {
        TRUE
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public boolean a(int i) {
        if (i > this.b.length - 1 || i < 0) {
            i = 0;
        }
        return this.b[i];
    }

    public void a(int i, boolean z) {
        if (i > this.b.length - 1 || i < 0) {
            i = 0;
        }
        this.c = true;
        this.d = i;
        this.b[i] = z;
    }

    public boolean a() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(String str) {
        this.e = str;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public void d() {
        int length = this.b.length;
        for (int i = 0; i != length; i++) {
            this.b[i] = false;
        }
    }
}
