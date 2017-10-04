package dji.midware.g.a;

import com.here.odnp.config.OdnpConfigStatic;
import java.io.IOException;
import java.io.OutputStream;

public class a extends OutputStream {
    protected byte[] a;
    protected int b;
    protected int c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private String g;

    public a() {
        this.g = "default";
        this.a = new byte[1024];
        this.d = new byte[1024];
        this.e = new byte[1024];
    }

    public void a(String str) {
        this.g = str;
    }

    public a(int i) {
        this.g = "default";
        if (i >= 0) {
            this.a = new byte[i];
            this.d = new byte[i];
            this.e = new byte[i];
            return;
        }
        throw new IllegalArgumentException(this.g + " size < 0");
    }

    public synchronized int a() {
        return this.b;
    }

    public synchronized int b() {
        byte b;
        if (this.b <= 0) {
            throw new IllegalArgumentException(this.g + " no byte");
        }
        b = this.a[0];
        this.c++;
        return b;
    }

    public synchronized int a(byte[] bArr, int i, int i2) throws IOException {
        a(bArr.length, i, i2);
        if (i2 > this.b) {
            i2 = this.b;
        }
        System.arraycopy(this.a, this.c, bArr, i, i2);
        a(i2);
        notifyAll();
        return i2;
    }

    protected synchronized void a(int i) {
        if (this.b < i) {
            throw new IllegalArgumentException(this.g + " count < expend");
        }
        this.b -= i;
        System.arraycopy(this.e, 0, this.d, 0, this.b);
        System.arraycopy(this.a, this.c + i, this.d, 0, this.b);
        this.f = this.a;
        this.a = this.d;
        this.d = this.f;
        this.c = 0;
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        a(bArr.length, i, i2);
        if (i2 != 0) {
            if (this.b + i2 > this.a.length) {
                try {
                    wait(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.b + i2 > this.a.length) {
                    throw new ArrayIndexOutOfBoundsException(this.g + " length=" + this.a.length + "; regionLength=" + (this.b + i2));
                }
            }
            System.arraycopy(bArr, i, this.a, this.c + this.b, i2);
            this.b += i2;
        }
    }

    public void write(int i) throws IOException {
    }

    public synchronized void b(int i) {
        if (i > this.b) {
            throw new IllegalArgumentException("skip len>count");
        }
        this.c += i;
    }

    public synchronized void c() {
        this.c = 0;
        this.b = 0;
    }

    public void a(int i, int i2, int i3) {
        if ((i2 | i3) < 0 || i2 > i || i - i2 < i3) {
            throw new ArrayIndexOutOfBoundsException(this.g + "length=" + i + "; regionStart=" + i2 + "; regionLength=" + i3);
        }
    }
}
