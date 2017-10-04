package dji.thirdparty.c;

final class s {
    static final int a = 8192;
    static final int b = 1024;
    final byte[] c;
    int d;
    int e;
    boolean f;
    boolean g;
    s h;
    s i;

    s() {
        this.c = new byte[8192];
        this.g = true;
        this.f = false;
    }

    s(s sVar) {
        this(sVar.c, sVar.d, sVar.e);
        sVar.f = true;
    }

    s(byte[] bArr, int i, int i2) {
        this.c = bArr;
        this.d = i;
        this.e = i2;
        this.g = false;
        this.f = true;
    }

    public s a() {
        s sVar = this.h != this ? this.h : null;
        this.i.h = this.h;
        this.h.i = this.i;
        this.h = null;
        this.i = null;
        return sVar;
    }

    public s a(s sVar) {
        sVar.i = this;
        sVar.h = this.h;
        this.h.i = sVar;
        this.h = sVar;
        return sVar;
    }

    public s a(int i) {
        if (i <= 0 || i > this.e - this.d) {
            throw new IllegalArgumentException();
        }
        s sVar;
        if (i >= 1024) {
            sVar = new s(this);
        } else {
            sVar = t.a();
            System.arraycopy(this.c, this.d, sVar.c, 0, i);
        }
        sVar.e = sVar.d + i;
        this.d += i;
        this.i.a(sVar);
        return sVar;
    }

    public void b() {
        if (this.i == this) {
            throw new IllegalStateException();
        } else if (this.i.g) {
            int i = this.e - this.d;
            if (i <= (this.i.f ? 0 : this.i.d) + (8192 - this.i.e)) {
                a(this.i, i);
                a();
                t.a(this);
            }
        }
    }

    public void a(s sVar, int i) {
        if (sVar.g) {
            if (sVar.e + i > 8192) {
                if (sVar.f) {
                    throw new IllegalArgumentException();
                } else if ((sVar.e + i) - sVar.d > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(sVar.c, sVar.d, sVar.c, 0, sVar.e - sVar.d);
                    sVar.e -= sVar.d;
                    sVar.d = 0;
                }
            }
            System.arraycopy(this.c, this.d, sVar.c, sVar.e, i);
            sVar.e += i;
            this.d += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
