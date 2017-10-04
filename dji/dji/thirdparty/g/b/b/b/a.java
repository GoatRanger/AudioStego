package dji.thirdparty.g.b.b.b;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.a.b;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.b.b.e;

public abstract class a extends b implements f {
    public final int k;
    public final int l;
    public final String m;

    public abstract byte[] a(Object obj, int i) throws dji.thirdparty.g.f;

    public abstract Object e(e eVar) throws dji.thirdparty.g.e;

    public a(int i, int i2, String str) {
        this.k = i;
        this.l = i2;
        this.m = str;
    }

    public boolean a(e eVar) {
        return this.l > 0 && this.l * eVar.o <= 4;
    }

    public int b(e eVar) throws dji.thirdparty.g.e {
        if (this.l >= 1) {
            return this.l * eVar.o;
        }
        throw new dji.thirdparty.g.e("Unknown field type");
    }

    public static final byte[] a() {
        return new byte[4];
    }

    public final byte[] a(int i) {
        return new byte[(this.l * i)];
    }

    public String c(e eVar) throws dji.thirdparty.g.e {
        Object e = e(eVar);
        if (e == null) {
            return "NULL";
        }
        return e.toString();
    }

    public final byte[] d(e eVar) {
        if (!a(eVar)) {
            return eVar.r;
        }
        int i = eVar.o * this.l;
        Object obj = new byte[i];
        System.arraycopy(eVar.q, 0, obj, 0, i);
        return obj;
    }

    public String toString() {
        return d.G + getClass().getName() + ". type: " + this.k + ", name: " + this.m + ", length: " + this.l + d.H;
    }
}
