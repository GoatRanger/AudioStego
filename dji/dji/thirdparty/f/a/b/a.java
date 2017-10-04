package dji.thirdparty.f.a.b;

import android.os.Handler;
import android.os.Looper;
import dji.thirdparty.f.g;

public final class a {

    private static class a {
        static final g a = new b(new Handler(Looper.getMainLooper()));

        private a() {
        }
    }

    private a() {
        throw new AssertionError("No instances");
    }

    public static g a() {
        g b = dji.thirdparty.f.a.a.a.getInstance().b().b();
        return b != null ? b : a.a;
    }
}
