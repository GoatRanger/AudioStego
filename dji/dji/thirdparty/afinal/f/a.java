package dji.thirdparty.afinal.f;

public abstract class a<T> {
    private boolean a = true;
    private int b = 1000;

    public abstract void a(long j, long j2);

    public abstract void a(T t);

    public abstract void a(Throwable th, int i, String str);

    public abstract void a(boolean z);

    public boolean e() {
        return this.a;
    }

    public int f() {
        return this.b;
    }

    public a<T> a(boolean z, int i) {
        this.a = z;
        this.b = i;
        return this;
    }
}
