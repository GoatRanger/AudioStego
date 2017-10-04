package dji.pilot.fpv.model;

import dji.midware.util.c;
import dji.pilot.usercenter.protocol.d;
import java.nio.charset.Charset;

public class k {
    protected static final int a = 200;
    public static final String b = "Map Loading";
    private static final int m = 20;
    private static final Charset n = Charset.forName("UTF-8");
    public String c = b;
    public String d = b;
    public String e = b;
    public String f = b;
    public double g;
    public double h;
    public float i;
    public float j;
    public int k;
    public long l;
    private int o = 12345;

    protected void a(byte[] bArr) {
        this.c = new String(bArr, 0, 20, n);
        this.d = new String(bArr, 20, 20, n);
        this.e = new String(bArr, 40, 20, n);
        this.c = new String(bArr, 60, 20, n);
        this.g = c.e(bArr, 80);
        this.h = c.e(bArr, 88);
        this.o = c.b(bArr, 96);
        this.i = c.d(bArr, 100);
        this.j = c.d(bArr, 104);
        this.k = c.b(bArr, 108);
        this.l = c.c(bArr, d.k);
    }

    protected byte[] a() {
        Object obj = new byte[200];
        Object bytes = this.c.getBytes(n);
        System.arraycopy(bytes, 0, obj, 0, bytes.length);
        Object bytes2 = this.d.getBytes(n);
        System.arraycopy(bytes2, 0, obj, 20, bytes2.length);
        bytes2 = this.e.getBytes(n);
        System.arraycopy(bytes2, 0, obj, 40, bytes2.length);
        bytes2 = this.f.getBytes(n);
        System.arraycopy(bytes2, 0, obj, 60, bytes2.length);
        System.arraycopy(c.a(this.g), 0, obj, 80, 8);
        System.arraycopy(c.a(this.h), 0, obj, 88, 8);
        System.arraycopy(c.a(this.o), 0, obj, 96, 4);
        System.arraycopy(c.a(this.i), 0, obj, 100, 4);
        System.arraycopy(c.a(this.j), 0, obj, 104, 4);
        System.arraycopy(c.a(this.k), 0, obj, 108, 4);
        System.arraycopy(c.a(this.l), 0, obj, d.k, 8);
        return obj;
    }
}
