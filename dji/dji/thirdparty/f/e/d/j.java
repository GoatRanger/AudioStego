package dji.thirdparty.f.e.d;

import dji.thirdparty.f.c.c;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.r;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.l;
import java.util.Queue;

public class j implements l {
    static int b;
    public static final int c = b;
    public static g<Queue<Object>> d = new g<Queue<Object>>() {
        protected /* synthetic */ Object b() {
            return a();
        }

        protected z<Object> a() {
            return new z(j.c);
        }
    };
    public static g<Queue<Object>> e = new g<Queue<Object>>() {
        protected /* synthetic */ Object b() {
            return a();
        }

        protected r<Object> a() {
            return new r(j.c);
        }
    };
    private static final dji.thirdparty.f.e.a.r<Object> f = dji.thirdparty.f.e.a.r.a();
    public volatile Object a;
    private Queue<Object> g;
    private final int h;
    private final g<Queue<Object>> i;

    public static j c() {
        if (an.a()) {
            return new j(d, c);
        }
        return new j();
    }

    public static j d() {
        if (an.a()) {
            return new j(e, c);
        }
        return new j();
    }

    static {
        b = 128;
        if (h.a()) {
            b = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                b = Integer.parseInt(property);
            } catch (Exception e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
    }

    private j(Queue<Object> queue, int i) {
        this.g = queue;
        this.i = null;
        this.h = i;
    }

    private j(g<Queue<Object>> gVar, int i) {
        this.i = gVar;
        this.g = (Queue) gVar.e();
        this.h = i;
    }

    public synchronized void e() {
        Object obj = this.g;
        g gVar = this.i;
        if (!(gVar == null || obj == null)) {
            obj.clear();
            this.g = null;
            gVar.a(obj);
        }
    }

    public void n_() {
        e();
    }

    j() {
        this(new o(c), c);
    }

    public void a(Object obj) throws c {
        Object obj2 = 1;
        Object obj3 = null;
        synchronized (this) {
            Queue queue = this.g;
            if (queue == null) {
                int i = 1;
                obj2 = null;
            } else if (queue.offer(f.a(obj))) {
                obj2 = null;
            }
        }
        if (obj3 != null) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (obj2 != null) {
            throw new c();
        }
    }

    public void f() {
        if (this.a == null) {
            this.a = f.b();
        }
    }

    public void a(Throwable th) {
        if (this.a == null) {
            this.a = f.a(th);
        }
    }

    public int g() {
        return this.h - i();
    }

    public int h() {
        return this.h;
    }

    public int i() {
        Queue queue = this.g;
        if (queue == null) {
            return 0;
        }
        return queue.size();
    }

    public boolean j() {
        Queue queue = this.g;
        if (queue == null) {
            return true;
        }
        return queue.isEmpty();
    }

    public Object k() {
        Object obj = null;
        synchronized (this) {
            Queue queue = this.g;
            if (queue == null) {
            } else {
                Object poll = queue.poll();
                obj = this.a;
                if (poll == null && obj != null && queue.peek() == null) {
                    this.a = null;
                } else {
                    obj = poll;
                }
            }
        }
        return obj;
    }

    public Object l() {
        Object obj;
        synchronized (this) {
            Queue queue = this.g;
            if (queue == null) {
                obj = null;
            } else {
                Object peek = queue.peek();
                obj = this.a;
                if (!(peek == null && obj != null && queue.peek() == null)) {
                    obj = peek;
                }
            }
        }
        return obj;
    }

    public boolean b(Object obj) {
        return f.b(obj);
    }

    public boolean c(Object obj) {
        return f.c(obj);
    }

    public Object d(Object obj) {
        return f.g(obj);
    }

    public boolean a(Object obj, e eVar) {
        return f.a(eVar, obj);
    }

    public Throwable e(Object obj) {
        return f.h(obj);
    }

    public boolean b() {
        return this.g == null;
    }
}
