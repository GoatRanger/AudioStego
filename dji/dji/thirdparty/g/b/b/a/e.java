package dji.thirdparty.g.b.b.a;

import dji.thirdparty.g.f;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class e implements g, h {
    protected static final int i = -1;
    public final String j;
    public final int k;
    public final dji.thirdparty.g.b.b.b.a[] l;
    public final int m;
    public final dji.thirdparty.g.b.b.a.g.a n;

    public static class a extends e {
        private static final DateFormat o = new SimpleDateFormat(dji.pilot.usercenter.f.e.c);
        private static final DateFormat p = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");

        public a(String str, int i, dji.thirdparty.g.b.b.b.a aVar, int i2) {
            super(str, i, aVar, i2);
        }

        public Object a(dji.thirdparty.g.b.b.e eVar) throws dji.thirdparty.g.e {
            Object e = eVar.k.e(eVar);
            String str = (String) e;
            try {
                e = o.parse(str);
            } catch (Exception e2) {
                try {
                    e = p.parse(str);
                } catch (Throwable e3) {
                    dji.thirdparty.g.c.c.a(e3);
                }
            }
            return e;
        }

        public byte[] a(dji.thirdparty.g.b.b.b.a aVar, Object obj, int i) throws f {
            throw new f("date encode value: " + obj + " (" + dji.thirdparty.g.c.c.b(obj) + ")");
        }

        public String toString() {
            return "[TagInfo. tag: " + this.k + ", name: " + this.j + " (data)]";
        }

        public boolean b() {
            return true;
        }
    }

    public static class b extends e {
        public b(String str, int i, dji.thirdparty.g.b.b.b.a[] aVarArr, int i2, dji.thirdparty.g.b.b.a.g.a aVar) {
            super(str, i, aVarArr, i2, aVar);
        }

        public b(String str, int i, dji.thirdparty.g.b.b.b.a aVar, int i2, dji.thirdparty.g.b.b.a.g.a aVar2) {
            super(str, i, aVar, i2, aVar2);
        }

        public b(String str, int i, dji.thirdparty.g.b.b.b.a aVar, int i2) {
            super(str, i, aVar, i2);
        }

        public boolean c() {
            return true;
        }
    }

    public static final class c extends e {
        private static final a o = new a(new byte[]{(byte) 65, (byte) 83, (byte) 67, (byte) 73, (byte) 73, (byte) 0, (byte) 0, (byte) 0}, com.alipay.e.a.a.b.b.a.b);
        private static final a p = new a(new byte[]{(byte) 74, (byte) 73, (byte) 83, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, "JIS");
        private static final a q = new a(new byte[]{(byte) 85, (byte) 78, (byte) 73, (byte) 67, (byte) 79, (byte) 68, (byte) 69, (byte) 0}, "UTF-8");
        private static final a r = new a(new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, "ISO-8859-1");
        private static final a[] s = new a[]{o, p, q, r};

        private static final class a {
            public final byte[] a;
            public final String b;

            public a(byte[] bArr, String str) {
                this.a = bArr;
                this.b = str;
            }
        }

        public c(String str, int i, dji.thirdparty.g.b.b.b.a aVar, int i2, dji.thirdparty.g.b.b.a.g.a aVar2) {
            super(str, i, aVar, i2, aVar2);
        }

        public c(String str, int i, dji.thirdparty.g.b.b.b.a[] aVarArr, int i2, dji.thirdparty.g.b.b.a.g.a aVar) {
            super(str, i, aVarArr, i2, aVar);
        }

        public boolean d() {
            return true;
        }

        public byte[] a(dji.thirdparty.g.b.b.b.a aVar, Object obj, int i) throws f {
            if (obj instanceof String) {
                String str = (String) obj;
                try {
                    Object bytes = str.getBytes(o.b);
                    if (new String(bytes, o.b).equals(str)) {
                        Object obj2 = new byte[(bytes.length + o.a.length)];
                        System.arraycopy(o.a, 0, obj2, 0, o.a.length);
                        System.arraycopy(bytes, 0, obj2, o.a.length, bytes.length);
                        return obj2;
                    }
                    bytes = str.getBytes(q.b);
                    byte[] bArr = new byte[(bytes.length + q.a.length)];
                    System.arraycopy(q.a, 0, bArr, 0, q.a.length);
                    System.arraycopy(bytes, 0, bArr, q.a.length, bytes.length);
                    return bArr;
                } catch (Exception e) {
                    throw new f(e.getMessage(), e);
                }
            }
            throw new f("Text value not String: " + obj + " (" + dji.thirdparty.g.c.c.b(obj) + ")");
        }

        public Object a(dji.thirdparty.g.b.b.e eVar) throws dji.thirdparty.g.e {
            if (eVar.n == ga_.k) {
                return ga_.e(eVar);
            }
            if (eVar.n == gf_.k || eVar.n == fZ_.k) {
                byte[] d = eVar.k.d(eVar);
                if (d.length < 8) {
                    try {
                        return new String(d, com.alipay.e.a.a.b.b.a.b);
                    } catch (UnsupportedEncodingException e) {
                        throw new dji.thirdparty.g.e("Text field missing encoding prefix.");
                    }
                }
                for (a aVar : s) {
                    if (dji.thirdparty.g.a.b.b(d, 0, aVar.a, 0, aVar.a.length)) {
                        try {
                            return new String(d, aVar.a.length, d.length - aVar.a.length, aVar.b);
                        } catch (Exception e2) {
                            throw new dji.thirdparty.g.e(e2.getMessage(), e2);
                        }
                    }
                }
                try {
                    return new String(d, com.alipay.e.a.a.b.b.a.b);
                } catch (UnsupportedEncodingException e3) {
                    throw new dji.thirdparty.g.e("Unknown text encoding prefix.");
                }
            }
            dji.thirdparty.g.c.c.b("entry.type", eVar.n);
            dji.thirdparty.g.c.c.b("entry.directoryType", eVar.m);
            dji.thirdparty.g.c.c.b("entry.type", eVar.f());
            dji.thirdparty.g.c.c.a("entry.type", eVar.k);
            throw new dji.thirdparty.g.e("Text field not encoded as bytes.");
        }
    }

    public static final class d extends e {
        public d(String str, int i, dji.thirdparty.g.b.b.b.a[] aVarArr, int i2, dji.thirdparty.g.b.b.a.g.a aVar) {
            super(str, i, aVarArr, i2, aVar);
        }

        public boolean e() {
            return true;
        }

        public byte[] a(dji.thirdparty.g.b.b.b.a aVar, Object obj, int i) throws f {
            return super.a(aVar, obj, i);
        }

        public Object a(dji.thirdparty.g.b.b.e eVar) throws dji.thirdparty.g.e {
            return super.a(eVar);
        }
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a aVar, int i2, dji.thirdparty.g.b.b.a.g.a aVar2) {
        this(str, i, new dji.thirdparty.g.b.b.b.a[]{aVar}, i2, aVar2);
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a aVar, int i2) {
        String str2 = str;
        int i3 = i;
        this(str2, i3, new dji.thirdparty.g.b.b.b.a[]{aVar}, i2, mB);
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a aVar, String str2) {
        String str3 = str;
        int i2 = i;
        this(str3, i2, new dji.thirdparty.g.b.b.b.a[]{aVar}, -1, mB);
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a[] aVarArr, String str2) {
        this(str, i, aVarArr, -1, mB);
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a aVar) {
        this(str, i, aVar, -1, mB);
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a[] aVarArr, int i2, String str2) {
        this(str, i, aVarArr, i2, mB);
    }

    public e(String str, int i, dji.thirdparty.g.b.b.b.a[] aVarArr, int i2, dji.thirdparty.g.b.b.a.g.a aVar) {
        this.j = str;
        this.k = i;
        this.l = aVarArr;
        this.m = i2;
        this.n = aVar;
    }

    public Object a(dji.thirdparty.g.b.b.e eVar) throws dji.thirdparty.g.e {
        return eVar.k.e(eVar);
    }

    public byte[] a(dji.thirdparty.g.b.b.b.a aVar, Object obj, int i) throws f {
        return aVar.a(obj, i);
    }

    public String a() {
        return this.k + " (0x" + Integer.toHexString(this.k) + ": " + this.j + "): ";
    }

    public String toString() {
        return "[TagInfo. tag: " + this.k + " (0x" + Integer.toHexString(this.k) + ", name: " + this.j + dji.pilot.usercenter.protocol.d.H;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }
}
