package dji.thirdparty.f.e.d;

import java.security.AccessController;
import java.security.PrivilegedAction;

public final class h {
    public static final int a = 0;
    private static final int b = d();
    private static final boolean c = (b != 0);

    public static boolean a() {
        return c;
    }

    public static int b() {
        return b;
    }

    private static int d() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION", true, c()).getField("SDK_INT").get(null)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    static ClassLoader c() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
            public /* synthetic */ Object run() {
                return a();
            }

            public ClassLoader a() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }
}
