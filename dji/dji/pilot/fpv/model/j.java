package dji.pilot.fpv.model;

import dji.midware.data.manager.P3.n;
import dji.midware.util.c;
import dji.pilot.publics.objects.DJIApplication;
import java.nio.charset.Charset;
import org.xeustechnologies.jtar.TarHeader;

public class j extends n {
    private static final int d = 85;
    private static final Charset e = Charset.forName("UTF-8");
    public int a = 2;
    public String b = "";
    public int c;
    private byte f = (byte) 0;
    private byte[] g = new byte[10];
    private String h = "";
    private String i = "";
    private long j = 0;
    private String k = "";
    private String l = "";
    private String m = "";

    protected void doPack() {
    }

    public j(boolean z) {
        super(z);
    }

    public void clear() {
        super.clear();
        this.f = (byte) 0;
        this.g = new byte[10];
        this.h = "";
        this.i = "";
        this.j = 0;
        this.k = "";
        this.l = "";
        this.m = "";
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        this.f = this._recData[0];
        this.a = this._recData[1];
        this.b = this._recData[2] + ".";
        this.b += this._recData[3] + ".";
        this.b += this._recData[4] + "";
        System.arraycopy(this._recData, 5, this.g, 0, 10);
        if (!a(this.g)) {
            this.c = 5;
        }
        this.h = new String(this._recData, 5, 10, e).trim();
        this.i = new String(this._recData, 15, 32, e).trim();
        this.j = ((Long) get(47, 8, Long.class)).longValue();
        this.k = new String(this._recData, 55, 10, e).trim();
        this.l = new String(this._recData, 65, 10, e).trim();
        this.m = new String(this._recData, 75, 10, e).trim();
    }

    public byte[] getRecData() {
        byte[] bArr = new byte[85];
        bArr[0] = this.f;
        bArr[1] = (byte) this.a;
        String[] split = this.b.equals("") ? DJIApplication.e.split("\\.") : this.b.split("\\.");
        bArr[2] = (byte) Integer.parseInt(split[0]);
        bArr[3] = (byte) Integer.parseInt(split[1]);
        bArr[4] = (byte) Integer.parseInt(split[2]);
        Object b = b(c.a(this.h));
        System.arraycopy(b, 0, bArr, 5, a(b.length, 10));
        b = this.i.trim().getBytes(e);
        System.arraycopy(b, 0, bArr, 15, a(b.length, 32));
        c.a(c.a(this.j), bArr, 47);
        b = b(c.a(this.k));
        System.arraycopy(b, 0, bArr, 55, a(b.length, 10));
        b = b(c.a(this.l));
        System.arraycopy(b, 0, bArr, 65, a(b.length, 10));
        b = b(c.a(this.m));
        System.arraycopy(b, 0, bArr, 75, a(b.length, 10));
        return bArr;
    }

    private int a(int i, int i2) {
        return i < i2 ? i : i2;
    }

    private byte[] b(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] < TarHeader.LF_NORMAL) {
                bArr[i] = TarHeader.LF_NORMAL;
            }
        }
        return bArr;
    }

    public boolean a(byte[] bArr) {
        for (byte b : bArr) {
            if (b < TarHeader.LF_NORMAL) {
                return false;
            }
        }
        return true;
    }

    public void a(int i) {
        this.f = (byte) i;
    }

    public void a(String str) {
        this.h = str;
    }

    public void b(String str) {
        this.i = str;
    }

    public void a(long j) {
        this.j = j;
    }

    public void c(String str) {
        this.k = str;
    }

    public void d(String str) {
        this.l = str;
    }

    public void e(String str) {
        this.m = str;
    }

    public String a() {
        return DJIApplication.e;
    }

    public int b() {
        return this.f;
    }

    public String c() {
        return this.i;
    }

    public long d() {
        return this.j;
    }

    public String e() {
        return this.k;
    }

    public String f() {
        return this.l;
    }

    public String g() {
        return this.m;
    }
}
