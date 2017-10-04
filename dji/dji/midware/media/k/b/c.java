package dji.midware.media.k.b;

import dji.midware.media.e;
import dji.midware.media.k.b.b.a;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class c {
    private static boolean a = false;
    private final int b;
    private final int c;
    private final a d;
    private final ArrayBlockingQueue<b> e = new ArrayBlockingQueue(this.b);
    private final ArrayBlockingQueue<b> f = new ArrayBlockingQueue(this.b);
    private int g = 0;

    public c(int i, int i2, a aVar) {
        this.c = i;
        this.b = i2;
        this.d = aVar;
    }

    public void a() {
        this.e.clear();
        this.f.clear();
        this.g = 0;
    }

    public void b() {
        this.e.clear();
        this.f.clear();
    }

    public b c() {
        b bVar = (b) this.e.peek();
        if (bVar != null) {
            this.e.poll();
        } else {
            synchronized (this.e) {
                if (this.g < this.b) {
                    this.g++;
                    bVar = new b(this.d, this.c);
                } else {
                    bVar = null;
                }
            }
        }
        return bVar;
    }

    public void a(b bVar) {
        this.e.offer(bVar);
    }

    public int d() {
        return this.f.size();
    }

    public b e() {
        try {
            return (b) this.f.poll(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }

    public b f() {
        return (b) this.f.peek();
    }

    public void b(b bVar) {
        try {
            this.f.put(bVar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        e.c(a, this.d.toString(), String.format("Try to queue in a frame.  Index=%d, Queue size=%d", new Object[]{Long.valueOf(bVar.c()), Integer.valueOf(this.f.size())}));
    }
}
