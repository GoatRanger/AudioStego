package dji.thirdparty.b.a.a;

public final class f {
    public static final dji.thirdparty.c.f a = dji.thirdparty.c.f.a(":status");
    public static final dji.thirdparty.c.f b = dji.thirdparty.c.f.a(":method");
    public static final dji.thirdparty.c.f c = dji.thirdparty.c.f.a(":path");
    public static final dji.thirdparty.c.f d = dji.thirdparty.c.f.a(":scheme");
    public static final dji.thirdparty.c.f e = dji.thirdparty.c.f.a(":authority");
    public static final dji.thirdparty.c.f f = dji.thirdparty.c.f.a(":host");
    public static final dji.thirdparty.c.f g = dji.thirdparty.c.f.a(":version");
    public final dji.thirdparty.c.f h;
    public final dji.thirdparty.c.f i;
    final int j;

    public f(String str, String str2) {
        this(dji.thirdparty.c.f.a(str), dji.thirdparty.c.f.a(str2));
    }

    public f(dji.thirdparty.c.f fVar, String str) {
        this(fVar, dji.thirdparty.c.f.a(str));
    }

    public f(dji.thirdparty.c.f fVar, dji.thirdparty.c.f fVar2) {
        this.h = fVar;
        this.i = fVar2;
        this.j = (fVar.j() + 32) + fVar2.j();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.h.equals(fVar.h) && this.i.equals(fVar.i)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.h.hashCode() + 527) * 31) + this.i.hashCode();
    }

    public String toString() {
        return String.format("%s: %s", new Object[]{this.h.a(), this.i.a()});
    }
}
