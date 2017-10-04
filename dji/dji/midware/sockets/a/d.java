package dji.midware.sockets.a;

import dji.log.DJILogHelper;

public class d {
    private static d a = null;
    private static int b = 204800;
    private int c = 0;
    private byte[] d;
    private byte[] e;
    private int f = 0;
    private int g = 0;
    private boolean h = true;
    private int i = 0;

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
                a.a(b);
            }
            dVar = a;
        }
        return dVar;
    }

    public void a(int i) {
        this.c = i;
        this.d = new byte[i];
        this.e = new byte[i];
    }

    public synchronized int a() {
        return this.f;
    }

    public synchronized int b() {
        return this.g;
    }

    public synchronized boolean a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public synchronized boolean a(byte[] bArr, int i) {
        return a(bArr, 0, i);
    }

    public synchronized boolean a(byte[] bArr, int i, int i2) {
        boolean z = false;
        synchronized (this) {
            this.h = true;
            this.i = (this.f + this.g) + i2;
            if (this.i + 2048 > this.c) {
                System.arraycopy(this.d, this.f, this.e, 0, this.g);
                System.arraycopy(this.e, 0, this.d, 0, this.g);
                this.f = 0;
                this.i = this.g + i2;
            }
            if (this.i > this.c) {
                d();
                DJILogHelper.getInstance().LOGD("", "buffer 长度太大了 并且不能够重置 " + System.currentTimeMillis(), false, false);
            } else {
                System.arraycopy(bArr, i, this.d, this.f + this.g, i2);
                this.g += i2;
                z = this.h;
            }
        }
        return z;
    }

    public synchronized byte[] c() {
        return this.d;
    }

    public synchronized byte b(int i) {
        return this.d[this.f + i];
    }

    public synchronized boolean b(byte[] bArr, int i, int i2) {
        boolean z = false;
        synchronized (this) {
            if (i + i2 <= this.g) {
                System.arraycopy(this.d, this.f + i, bArr, 0, i2);
                z = true;
            }
        }
        return z;
    }

    public synchronized void c(int i) {
        if (this.g != 0) {
            this.f += i;
            this.g -= i;
        }
    }

    public synchronized void d() {
        this.f = 0;
        this.g = 0;
    }
}
