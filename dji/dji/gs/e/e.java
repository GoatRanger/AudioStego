package dji.gs.e;

public class e {
    public static final int a = 200;
    private float b = 0.0f;
    private long c = 0;
    private int d = 30;
    private boolean e = false;
    private a f = a.LEFT;

    public enum a {
        LEFT,
        RIGHT,
        FRONT,
        BACK
    }

    public float a() {
        return this.b;
    }

    public e a(float f) {
        this.b = f;
        return this;
    }

    public long b() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public int c() {
        return this.d;
    }

    public e a(int i) {
        this.d = i;
        return this;
    }

    public a d() {
        return this.f;
    }

    public e a(a aVar) {
        this.f = aVar;
        return this;
    }

    public int e() {
        switch (this.f) {
        }
        return 0;
    }

    public boolean f() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }
}
