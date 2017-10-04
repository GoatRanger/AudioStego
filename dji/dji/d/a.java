package dji.d;

import dji.log.DJILog;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.c.g;
import dji.sdksharedlib.d.c;
import java.util.ArrayList;

public class a extends DJISDKCache {
    private static final String a = "MockDJISDKCache";

    private static class a {
        private static a a = new a();

        private a() {
        }
    }

    private a() {
    }

    public static a getInstance() {
        return a.a;
    }

    public void init() {
        if (!this.isInitialized) {
            this.isInitialized = true;
            DJILog.i(a, "init");
            this.listenerLayer = new g();
            this.storeLayer = new c();
            this.halLayer = new b();
            this.listenerLayer.a();
            this.storeLayer.a(this.listenerLayer);
            this.halLayer.a(this.storeLayer, this.listenerLayer);
            this.listenerLayer.a(getValueChangeListener());
            this.interactionListenerList = new ArrayList();
        }
    }

    public void a() {
        ((b) this.halLayer).h();
    }
}
