package dji.thirdparty.b.a.b;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class o extends Exception {
    private static final Method a;
    private IOException b;

    static {
        Method declaredMethod;
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Exception e) {
            declaredMethod = null;
        }
        a = declaredMethod;
    }

    public o(IOException iOException) {
        super(iOException);
        this.b = iOException;
    }

    public IOException a() {
        return this.b;
    }

    public void a(IOException iOException) {
        a(iOException, this.b);
        this.b = iOException;
    }

    private void a(IOException iOException, IOException iOException2) {
        if (a != null) {
            try {
                a.invoke(iOException, new Object[]{iOException2});
            } catch (InvocationTargetException e) {
            } catch (IllegalAccessException e2) {
            }
        }
    }
}
