package dji.sdksharedlib;

import dji.log.DJILog;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.e;
import dji.sdksharedlib.c.g;
import dji.sdksharedlib.c.h;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.a;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.b;
import java.util.ArrayList;
import java.util.List;

public class DJISDKCache implements b {
    private static final String TAG = "DJISDKCache";
    public a halLayer;
    protected List<e> interactionListenerList;
    protected boolean isInitialized = false;
    public g listenerLayer;
    public c storeLayer;
    private b subscriptions = new b();

    private static class SingletonHolder {
        private static DJISDKCache instance = new DJISDKCache();

        private SingletonHolder() {
        }
    }

    public static DJISDKCache getInstance() {
        if ("release".toLowerCase().equals("mock")) {
            return dji.d.a.getInstance();
        }
        return SingletonHolder.instance;
    }

    protected DJISDKCache() {
    }

    public void setInteractionListener(e eVar) {
        if (this.interactionListenerList != null && !this.interactionListenerList.contains(eVar)) {
            this.interactionListenerList.add(eVar);
        }
    }

    public boolean removeInteractionListener(e eVar) {
        if (this.interactionListenerList != null) {
            return this.interactionListenerList.remove(eVar);
        }
        return false;
    }

    public void init() {
        if (!this.isInitialized) {
            this.isInitialized = true;
            DJILog.i(TAG, "init");
            this.listenerLayer = new g();
            this.storeLayer = new c();
            this.halLayer = new a();
            this.listenerLayer.a();
            this.storeLayer.a(this.listenerLayer);
            this.halLayer.a(this.storeLayer, this.listenerLayer);
            this.listenerLayer.a(getValueChangeListener());
            this.interactionListenerList = new ArrayList();
        }
    }

    public void destroy() {
        this.isInitialized = false;
        DJILog.i(TAG, "destroy");
        if (this.halLayer != null) {
            this.halLayer.i();
        }
        if (this.storeLayer != null) {
            this.storeLayer.a();
        }
        if (!this.subscriptions.b()) {
            this.subscriptions.n_();
        }
        if (this.listenerLayer != null) {
            this.listenerLayer.b();
        }
    }

    public void addMockAbstraction(int i, String str, Class<? extends dji.sdksharedlib.hardware.abstractions.b> cls) {
        if (this.halLayer != null) {
            this.halLayer.b(i, str, cls);
            return;
        }
        throw new NullPointerException("DJISDKCacheHWAbstractionLayer is null, call init first");
    }

    public void addSubscription(l lVar) {
        this.subscriptions.a(lVar);
    }

    public void removeSubscription(l lVar) {
        this.subscriptions.b(lVar);
    }

    public boolean startListeningForUpdates(dji.sdksharedlib.b.c cVar, d dVar, boolean z) {
        if (this.listenerLayer != null) {
            return this.listenerLayer.a(cVar, dVar, z);
        }
        return false;
    }

    public void stopListeningOnKey(dji.sdksharedlib.b.c cVar, d dVar) {
        this.listenerLayer.b(cVar, dVar);
    }

    public void stopListening(d dVar) {
        this.listenerLayer.b(dVar);
    }

    public void setValue(dji.sdksharedlib.b.c cVar, Object obj, h hVar) {
        this.halLayer.a(cVar, obj, hVar);
        if (this.interactionListenerList != null && !this.interactionListenerList.isEmpty()) {
            for (e eVar : this.interactionListenerList) {
                if (eVar != null) {
                    eVar.a(cVar.b(), cVar.c(), cVar.d(), cVar.e(), cVar.f(), obj);
                }
            }
        }
    }

    public void getValue(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.c.c cVar2) {
        dji.sdksharedlib.d.a a = this.storeLayer.a(cVar);
        if (a == null || !a.f()) {
            this.halLayer.a(cVar, cVar2);
        } else {
            cVar2.a(a);
        }
        if (this.interactionListenerList != null && !this.interactionListenerList.isEmpty()) {
            for (e eVar : this.interactionListenerList) {
                if (eVar != null) {
                    eVar.a(cVar.b(), cVar.c(), cVar.d(), cVar.e(), cVar.f());
                }
            }
        }
    }

    public dji.sdksharedlib.d.a getAvailableValue(dji.sdksharedlib.b.c cVar) {
        if (cVar == null || this.storeLayer == null) {
            return null;
        }
        return this.storeLayer.a(cVar);
    }

    public void performAction(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.c.b bVar, Object... objArr) {
        this.halLayer.a(cVar, bVar, objArr);
        if (this.interactionListenerList != null && !this.interactionListenerList.isEmpty()) {
            for (e eVar : this.interactionListenerList) {
                if (eVar != null) {
                    eVar.a(cVar.b(), cVar.c(), cVar.d(), cVar.e(), cVar.f(), objArr);
                }
            }
        }
    }

    protected d getValueChangeListener() {
        return new d() {
            public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                if (DJISDKCache.this.interactionListenerList != null && !DJISDKCache.this.interactionListenerList.isEmpty()) {
                    for (e eVar : DJISDKCache.this.interactionListenerList) {
                        if (eVar != null) {
                            eVar.a(cVar.b(), cVar.c(), cVar.d(), cVar.e(), cVar.f(), aVar, aVar2);
                        }
                    }
                }
            }
        };
    }
}
