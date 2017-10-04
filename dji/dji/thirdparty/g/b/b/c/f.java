package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.b.b.a.e;
import dji.thirdparty.g.b.b.b.a;
import java.io.IOException;

public class f implements dji.thirdparty.g.b.b.a.f {
    private static final String q = System.getProperty("line.separator");
    public final int j;
    public final e k;
    public final a l;
    public final int m;
    private byte[] n;
    private final g.a o;
    private int p;

    public f(e eVar, a aVar, int i, byte[] bArr) {
        this(eVar.k, eVar, aVar, i, bArr);
    }

    public f(int i, e eVar, a aVar, int i2, byte[] bArr) {
        this.p = -1;
        this.j = i;
        this.k = eVar;
        this.l = aVar;
        this.m = i2;
        this.n = bArr;
        if (b()) {
            this.o = null;
        } else {
            this.o = new g.a("Field Seperate value (" + eVar.a() + ")", bArr);
        }
    }

    public static f a(e eVar, int i, Number number) throws dji.thirdparty.g.f {
        if (eVar.l == null || eVar.l.length < 1) {
            throw new dji.thirdparty.g.f("Tag has no default data type.");
        }
        a aVar = eVar.l[0];
        if (eVar.m != 1) {
            throw new dji.thirdparty.g.f("Tag does not expect a single value.");
        }
        return new f(eVar.k, eVar, aVar, 1, aVar.a(number, i));
    }

    public static f a(e eVar, int i, Number[] numberArr) throws dji.thirdparty.g.f {
        if (eVar.l == null || eVar.l.length < 1) {
            throw new dji.thirdparty.g.f("Tag has no default data type.");
        }
        a aVar = eVar.l[0];
        if (eVar.m != numberArr.length) {
            throw new dji.thirdparty.g.f("Tag does not expect a single value.");
        }
        return new f(eVar.k, eVar, aVar, numberArr.length, aVar.a(numberArr, i));
    }

    public static f a(e eVar, int i, String str) throws dji.thirdparty.g.f {
        a aVar;
        if (eVar.l == null) {
            aVar = ga_;
        } else if (eVar.l == gm_) {
            aVar = ga_;
        } else if (eVar.l[0] == ga_) {
            aVar = ga_;
        } else {
            throw new dji.thirdparty.g.f("Tag has unexpected data type.");
        }
        byte[] a = aVar.a(str, i);
        return new f(eVar.k, eVar, aVar, a.length, a);
    }

    protected static final f a(e eVar, int i) throws dji.thirdparty.g.f {
        return new f(eVar, gc_, 1, gc_.a(new int[]{null}, i));
    }

    protected void a(dji.thirdparty.g.a.e eVar) throws IOException, dji.thirdparty.g.f {
        eVar.d(this.j);
        eVar.d(this.l.k);
        eVar.b(this.m);
        if (b()) {
            if (this.o != null) {
                throw new dji.thirdparty.g.f("Unexpected separate value item.");
            } else if (this.n.length > 4) {
                throw new dji.thirdparty.g.f("Local value has invalid length: " + this.n.length);
            } else {
                eVar.a(this.n);
                int length = 4 - this.n.length;
                for (int i = 0; i < length; i++) {
                    eVar.write(0);
                }
            }
        } else if (this.o == null) {
            throw new dji.thirdparty.g.f("Missing separate value item.");
        } else {
            eVar.b(this.o.g());
        }
    }

    protected g a() {
        return this.o;
    }

    protected boolean b() {
        return this.n.length <= 4;
    }

    protected void a(byte[] bArr) throws dji.thirdparty.g.f {
        if (this.n.length != bArr.length) {
            throw new dji.thirdparty.g.f("Cannot change size of value.");
        }
        this.n = bArr;
        if (this.o != null) {
            this.o.a(bArr);
        }
    }

    public String toString() {
        return a(null);
    }

    public String a(String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(this.k);
        stringBuffer.append(q);
        stringBuffer.append(str);
        stringBuffer.append("count: " + this.m);
        stringBuffer.append(q);
        stringBuffer.append(str);
        stringBuffer.append(this.l);
        stringBuffer.append(q);
        return stringBuffer.toString();
    }

    public int c() {
        return this.p;
    }

    public void a(int i) {
        this.p = i;
    }
}
