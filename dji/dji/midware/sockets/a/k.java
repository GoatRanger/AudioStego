package dji.midware.sockets.a;

import dji.log.DJILogHelper;
import dji.midware.util.c;

public class k {
    private static k a = null;
    private byte[] b = new byte[0];

    public static synchronized k getInstance() {
        k kVar;
        synchronized (k.class) {
            if (a == null) {
                a = new k();
            }
            kVar = a;
        }
        return kVar;
    }

    public synchronized void a(byte[] bArr) {
        if (this.b.length + bArr.length > 3145728) {
            DJILogHelper.getInstance().LOGD("", "buffer 长度太大了 " + this.b.length, true, false);
            this.b = bArr;
        } else {
            this.b = c.a(this.b, bArr);
        }
    }

    public synchronized byte[] a() {
        return this.b;
    }

    public synchronized void a(int i) {
        if (i > this.b.length) {
            DJILogHelper.getInstance().LOGD("", "len大过了buffer的长度 ");
        } else {
            this.b = c.h(this.b, i);
        }
    }

    public synchronized void b() {
        this.b = new byte[0];
    }
}
