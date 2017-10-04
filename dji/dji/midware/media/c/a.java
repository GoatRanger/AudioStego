package dji.midware.media.c;

import java.nio.ByteBuffer;

public class a {
    private ByteBuffer a;
    private boolean b;
    private Object c = new Object();
    private Thread d = null;
    private int e = 0;

    public a(int i, boolean z) {
        this.b = z;
        if (z) {
            this.a = ByteBuffer.allocateDirect(i);
        } else {
            this.a = ByteBuffer.allocate(i);
        }
    }

    public int a() {
        return this.a.capacity();
    }

    public int b() {
        return this.e;
    }

    public ByteBuffer c() {
        d();
        return this.a;
    }

    public void d() {
        synchronized (this.c) {
            if (Thread.currentThread().equals(this.d)) {
                throw new RuntimeException("you have locked the object and can't lock it again");
            }
            while (this.d != null) {
                try {
                    this.c.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.d = Thread.currentThread();
        }
    }

    public void e() {
        synchronized (this.c) {
            if (Thread.currentThread().equals(this.d)) {
                this.d = null;
                this.c.notifyAll();
            } else {
                throw new RuntimeException("you have NOT locked the object and can't unlock it.");
            }
        }
    }

    public ByteBuffer f() {
        d();
        return this.a;
    }

    public void a(ByteBuffer byteBuffer) {
        d();
        this.e = byteBuffer.remaining();
        if (this.a.capacity() < byteBuffer.remaining()) {
            int capacity = this.a.capacity();
            while (capacity < byteBuffer.remaining()) {
                capacity = (int) (((double) capacity) * 1.5d);
            }
            if (this.b) {
                this.a = ByteBuffer.allocateDirect(capacity);
            } else {
                this.a = ByteBuffer.allocate(capacity);
            }
        }
        this.a.clear();
        this.a.put(byteBuffer);
        e();
    }

    public void a(byte[] bArr, int i) {
        a(ByteBuffer.wrap(bArr, 0, i));
    }

    public void a(int i) {
        this.e = i;
    }
}
