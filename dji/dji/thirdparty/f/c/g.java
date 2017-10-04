package dji.thirdparty.f.c;

import dji.thirdparty.f.i.d;
import java.util.HashSet;
import java.util.Set;

public final class g extends RuntimeException {
    private static final long a = -569558213262703934L;
    private final boolean b;
    private final Object c;

    public static class a extends RuntimeException {
        private static final long a = -3454462756050397899L;
        private final Object b;

        private static final class a {
            static final Set<Class<?>> a = a();

            private a() {
            }

            private static Set<Class<?>> a() {
                Set<Class<?>> hashSet = new HashSet();
                hashSet.add(Boolean.class);
                hashSet.add(Character.class);
                hashSet.add(Byte.class);
                hashSet.add(Short.class);
                hashSet.add(Integer.class);
                hashSet.add(Long.class);
                hashSet.add(Float.class);
                hashSet.add(Double.class);
                return hashSet;
            }
        }

        public a(Object obj) {
            super("OnError while emitting onNext value: " + a(obj));
            this.b = obj;
        }

        public Object a() {
            return this.b;
        }

        static String a(Object obj) {
            if (obj == null) {
                return "null";
            }
            if (a.a.contains(obj.getClass())) {
                return obj.toString();
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Enum) {
                return ((Enum) obj).name();
            }
            String a = d.getInstance().b().a(obj);
            if (a != null) {
                return a;
            }
            return obj.getClass().getName() + ".class";
        }
    }

    private g(Throwable th) {
        super(th);
        this.b = false;
        this.c = null;
    }

    private g(Throwable th, Object obj) {
        super(th);
        this.b = true;
        this.c = obj;
    }

    public Object a() {
        return this.c;
    }

    public boolean b() {
        return this.b;
    }

    public static g a(Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable c = b.c(th);
        if (c instanceof a) {
            return new g(th, ((a) c).a());
        }
        return new g(th);
    }

    public static Throwable a(Throwable th, Object obj) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable c = b.c(th);
        if (!(c != null && (c instanceof a) && ((a) c).a() == obj)) {
            b.a(th, new a(obj));
        }
        return th;
    }
}
