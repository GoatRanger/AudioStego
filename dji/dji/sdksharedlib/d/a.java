package dji.sdksharedlib.d;

public class a {
    private b a;
    private a b;
    private Object c;
    private long d;
    private long e = -1;

    @Deprecated
    public a(Object obj) {
        this.c = obj;
    }

    public a(Object obj, b bVar, a aVar) {
        this.c = obj;
        this.a = bVar;
        this.b = aVar;
        this.d = System.currentTimeMillis();
    }

    public a(Object obj, b bVar, a aVar, long j) {
        this.c = obj;
        this.a = bVar;
        this.b = aVar;
        this.d = System.currentTimeMillis();
        this.e = j;
    }

    public a a() {
        return this.b;
    }

    public long b() {
        return this.d;
    }

    public void c() {
        this.d = System.currentTimeMillis();
    }

    public long d() {
        return this.e;
    }

    public Object e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == null && this.c == null) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if ((obj instanceof a) && obj.equals(this.c)) {
            return true;
        }
        return false;
    }

    public boolean a(Object obj) {
        if (this.c == null && obj == null) {
            return true;
        }
        if (this.c == null || obj == null) {
            return false;
        }
        return this.c.equals(obj);
    }

    public boolean f() {
        if (this.a == b.a || this.a == b.c) {
            return false;
        }
        if (this.e == -1 || System.currentTimeMillis() - this.d <= this.e) {
            return true;
        }
        return false;
    }
}
