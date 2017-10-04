package dji.pilot.publics.control.p3cupgrade;

import android.os.HandlerThread;
import android.os.Looper;

public class g {
    private HandlerThread a;

    private static final class a {
        private static final g a = new g();

        private a() {
        }
    }

    public static g getInstance() {
        return a.a;
    }

    private g() {
        this.a = new HandlerThread("UpgradeThreadWorker");
        this.a.start();
    }

    public Looper a() {
        return this.a.getLooper();
    }
}
