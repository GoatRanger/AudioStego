package dji.thirdparty.f.e.d.b;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class an {
    public static final Unsafe a;

    private an() {
        throw new IllegalStateException("No instances!");
    }

    static {
        Unsafe unsafe;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Throwable th) {
            unsafe = null;
        }
        a = unsafe;
    }

    public static boolean a() {
        return a != null;
    }

    public static int a(Object obj, long j) {
        int intVolatile;
        do {
            intVolatile = a.getIntVolatile(obj, j);
        } while (!a.compareAndSwapInt(obj, j, intVolatile, intVolatile + 1));
        return intVolatile;
    }

    public static int a(Object obj, long j, int i) {
        int intVolatile;
        do {
            intVolatile = a.getIntVolatile(obj, j);
        } while (!a.compareAndSwapInt(obj, j, intVolatile, intVolatile + i));
        return intVolatile;
    }

    public static int b(Object obj, long j, int i) {
        int intVolatile;
        do {
            intVolatile = a.getIntVolatile(obj, j);
        } while (!a.compareAndSwapInt(obj, j, intVolatile, i));
        return intVolatile;
    }

    public static boolean a(Object obj, long j, int i, int i2) {
        return a.compareAndSwapInt(obj, j, i, i2);
    }

    public static long a(Class<?> cls, String str) {
        try {
            return a.objectFieldOffset(cls.getDeclaredField(str));
        } catch (Throwable e) {
            InternalError internalError = new InternalError();
            internalError.initCause(e);
            throw internalError;
        }
    }
}
