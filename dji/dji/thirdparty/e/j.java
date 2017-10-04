package dji.thirdparty.e;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

class j {
    private static final j a = c();

    static class a extends j {

        static class a implements Executor {
            private final Handler a = new Handler(Looper.getMainLooper());

            a() {
            }

            public void execute(Runnable runnable) {
                this.a.post(runnable);
            }
        }

        a() {
        }

        public Executor b() {
            return new a();
        }

        dji.thirdparty.e.c.a a(Executor executor) {
            return new g(executor);
        }
    }

    static class b extends j {

        static class a implements Executor {
            private static Object a;
            private static Method b;

            a() {
            }

            static {
                try {
                    Class cls = Class.forName("org.robovm.apple.foundation.NSOperationQueue");
                    a = cls.getDeclaredMethod("getMainQueue", new Class[0]).invoke(null, new Object[0]);
                    b = cls.getDeclaredMethod("addOperation", new Class[]{Runnable.class});
                } catch (Exception e) {
                    throw new AssertionError(e);
                }
            }

            public void execute(Runnable runnable) {
                Object e;
                try {
                    b.invoke(a, new Object[]{runnable});
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    throw new AssertionError(e);
                } catch (IllegalAccessException e3) {
                    e = e3;
                    throw new AssertionError(e);
                } catch (InvocationTargetException e4) {
                    Throwable cause = e4.getCause();
                    if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    } else if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else {
                        throw new RuntimeException(cause);
                    }
                }
            }
        }

        b() {
        }

        public Executor b() {
            return new a();
        }

        dji.thirdparty.e.c.a a(Executor executor) {
            return new g(executor);
        }
    }

    @IgnoreJRERequirement
    static class c extends j {
        c() {
        }

        boolean a(Method method) {
            return method.isDefault();
        }

        Object a(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            Constructor declaredConstructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            return ((Lookup) declaredConstructor.newInstance(new Object[]{cls, Integer.valueOf(-1)})).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }
    }

    j() {
    }

    static j a() {
        return a;
    }

    private static j c() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new a();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new c();
        } catch (ClassNotFoundException e2) {
            try {
                Class.forName("org.robovm.apple.foundation.NSObject");
                return new b();
            } catch (ClassNotFoundException e3) {
                return new j();
            }
        }
    }

    Executor b() {
        return null;
    }

    dji.thirdparty.e.c.a a(Executor executor) {
        if (executor != null) {
            return new g(executor);
        }
        return f.a;
    }

    boolean a(Method method) {
        return false;
    }

    Object a(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
