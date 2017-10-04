package dji.sdksharedlib.hardware.a;

import android.os.Handler;
import android.util.Log;
import dji.common.error.DJISDKCacheError;
import dji.log.DJILog;
import dji.sdksharedlib.e.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class i {
    private static final String a = "DJISDKMergeHandler";
    private Handler b = new Handler(b.a());
    private HashMap<String, a> c = new HashMap();

    public static class a implements Runnable {
        public dji.sdksharedlib.hardware.abstractions.b a;
        public d b;

        public a(dji.sdksharedlib.hardware.abstractions.b bVar, d dVar) {
            this.a = bVar;
            this.b = dVar;
        }

        public void run() {
            if (this.a == null) {
                Log.d(i.a, "abstraction is null");
            } else if (this.b == null) {
                Log.d(i.a, "characteristics is null.");
            } else {
                try {
                    HashMap hashMap = new HashMap();
                    for (Entry entry : this.b.g().entrySet()) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                    this.b.f();
                    ((Method) this.a.n().get(this.b.a)).invoke(this.a, new Object[]{hashMap});
                } catch (Exception e) {
                    a(DJISDKCacheError.INVALID_KEY_FOR_COMPONENT);
                }
            }
        }

        private void a(DJISDKCacheError dJISDKCacheError) {
            for (Entry entry : this.b.g().entrySet()) {
                if (entry.getValue() != null) {
                    Iterator it = ((ArrayList) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        e eVar = (e) it.next();
                        if (eVar != null) {
                            eVar.a(dJISDKCacheError);
                        }
                    }
                }
            }
        }
    }

    public i() {
        b.getInstance();
    }

    public synchronized void a(final a aVar, int i) {
        if (this.b == null) {
            DJILog.e(a, "Handler is terminated by exception.");
            throw new RuntimeException("Merge logic breaks: handler is null.");
        } else if (this.c == null) {
            DJILog.e(a, "Signal hash map is terminated by exception");
            throw new RuntimeException("Merge logic breaks: signal hash map is null.");
        } else if (aVar.a == null) {
            DJILog.e(a, "Do not insert null for abstraction in signal");
            throw new RuntimeException("Requirement exception, abstraction in signal is null.");
        } else if (aVar.b == null) {
            DJILog.e(a, "Do not insert null for characteristics in signal");
            throw new RuntimeException("Requirement exception, characteristics in signal is null.");
        } else {
            String str = aVar.b.a;
            if (!this.c.containsKey(str)) {
                this.c.put(str, aVar);
                this.b.postDelayed(new Runnable(this) {
                    final /* synthetic */ i b;

                    public void run() {
                        aVar.run();
                        this.b.a(aVar);
                    }
                }, (long) aVar.b.e());
            }
        }
    }

    private void a(a aVar) {
        this.c.remove(aVar.b.a);
    }

    public void a() {
        this.b.removeCallbacksAndMessages(null);
        this.b = null;
        this.c = null;
    }
}
