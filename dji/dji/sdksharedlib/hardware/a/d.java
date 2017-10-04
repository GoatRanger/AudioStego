package dji.sdksharedlib.hardware.a;

import android.os.Handler;
import dji.log.DJILog;
import dji.sdksharedlib.e.b;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.c;
import java.util.ArrayList;
import java.util.HashMap;

public class d extends c {
    private static final String g = "DJISDKCacheCollectorCharacteristics";
    private static Handler i = new Handler(b.a());
    public String f;
    private HashMap<c, ArrayList<e>> h = new HashMap();

    private class a implements Runnable {
        final /* synthetic */ d a;
        private c b;
        private e c;

        public a(d dVar, c cVar, e eVar) {
            this.a = dVar;
            this.b = cVar;
            this.c = eVar;
        }

        public void run() {
            if (this.a.h == null) {
                DJILog.i(d.g, "Operation array is not initialized.");
            } else if (this.a.h.containsKey(this.b)) {
                ((ArrayList) this.a.h.get(this.b)).add(this.c);
                DJILog.i("DJISDKMergeHandler", "Second time");
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.c);
                this.a.h.put(this.b, arrayList);
                DJILog.i("DJISDKMergeHandler", "First time");
            }
        }
    }

    public d(String str, int i, DJISDKCacheUpdateType dJISDKCacheUpdateType, Class cls) {
        super(str, i, dJISDKCacheUpdateType, cls);
    }

    public void a(c cVar, e eVar) {
        i.post(new a(this, cVar, eVar));
    }

    public void f() {
        if (this.h != null) {
            this.h.clear();
        }
    }

    public HashMap<c, ArrayList<e>> g() {
        return this.h;
    }
}
