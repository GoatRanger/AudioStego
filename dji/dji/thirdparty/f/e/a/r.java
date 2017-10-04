package dji.thirdparty.f.e.a;

import dji.thirdparty.f.e;
import java.io.Serializable;

public final class r<T> {
    private static final r a = new r();
    private static final Object b = new Serializable() {
        private static final long a = 1;

        public String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object c = new Serializable() {
        private static final long a = 2;

        public String toString() {
            return "Notification=>NULL";
        }
    };

    private static class a implements Serializable {
        private static final long b = 3;
        final Throwable a;

        public a(Throwable th) {
            this.a = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.a;
        }
    }

    private r() {
    }

    public static <T> r<T> a() {
        return a;
    }

    public Object a(T t) {
        if (t == null) {
            return c;
        }
        return t;
    }

    public Object b() {
        return b;
    }

    public Object a(Throwable th) {
        return new a(th);
    }

    public boolean a(e<? super T> eVar, Object obj) {
        if (obj == b) {
            eVar.o_();
            return true;
        } else if (obj == c) {
            eVar.a_(null);
            return false;
        } else if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (obj.getClass() == a.class) {
            eVar.a(((a) obj).a);
            return true;
        } else {
            eVar.a_(obj);
            return false;
        }
    }

    public boolean b(Object obj) {
        return obj == b;
    }

    public boolean c(Object obj) {
        return obj instanceof a;
    }

    public boolean d(Object obj) {
        return obj == c;
    }

    public boolean e(Object obj) {
        return (obj == null || c(obj) || b(obj)) ? false : true;
    }

    public dji.thirdparty.f.c.a f(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (obj == b) {
            return dji.thirdparty.f.c.a.OnCompleted;
        } else {
            if (obj instanceof a) {
                return dji.thirdparty.f.c.a.OnError;
            }
            return dji.thirdparty.f.c.a.OnNext;
        }
    }

    public T g(Object obj) {
        return obj == c ? null : obj;
    }

    public Throwable h(Object obj) {
        return ((a) obj).a;
    }
}
