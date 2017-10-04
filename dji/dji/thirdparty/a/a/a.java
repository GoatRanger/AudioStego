package dji.thirdparty.a.a;

import android.app.Activity;
import android.util.Log;
import dji.thirdparty.a.c;
import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class a {
    private final Executor a;
    private final Constructor<?> b;
    private final c c;
    private Object d;

    public static class a {
        private Executor a;
        private Class<?> b;
        private c c;

        private a() {
        }

        public a a(Executor executor) {
            this.a = executor;
            return this;
        }

        public a a(Class<?> cls) {
            this.b = cls;
            return this;
        }

        public a a(c cVar) {
            this.c = cVar;
            return this;
        }

        public a a() {
            return a(null);
        }

        public a a(Activity activity) {
            return a(activity.getClass());
        }

        public a a(Object obj) {
            if (this.c == null) {
                this.c = c.a();
            }
            if (this.a == null) {
                this.a = Executors.newCachedThreadPool();
            }
            if (this.b == null) {
                this.b = h.class;
            }
            return new a(this.a, this.c, this.b, obj);
        }
    }

    public interface b {
        void a() throws Exception;
    }

    public static a a() {
        return new a();
    }

    public static a b() {
        return new a().a();
    }

    private a(Executor executor, c cVar, Class<?> cls, Object obj) {
        this.a = executor;
        this.c = cVar;
        this.d = obj;
        try {
            this.b = cls.getConstructor(new Class[]{Throwable.class});
        } catch (Throwable e) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", e);
        }
    }

    public void a(final b bVar) {
        this.a.execute(new Runnable(this) {
            final /* synthetic */ a a;

            public void run() {
                try {
                    bVar.a();
                } catch (Throwable e) {
                    try {
                        Object newInstance = this.a.b.newInstance(new Object[]{e});
                        if (e instanceof g) {
                            ((g) e).a(this.a.d);
                        }
                        this.a.c.e(newInstance);
                    } catch (Throwable e2) {
                        Log.e(c.b, "Original exception:", e);
                        throw new RuntimeException("Could not create failure event", e2);
                    }
                }
            }
        });
    }
}
