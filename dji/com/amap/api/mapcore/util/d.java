package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.amap.api.mapcore.util.dp.a.c;
import com.amap.api.mapcore.util.dv.a;
import com.amap.api.maps.MapsInitializer;

class d extends Thread {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void run() {
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                dv a = new a(r.b, "3.3.2", r.d).a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore"}).a();
                if (!TextUtils.isEmpty(MapsInitializer.KEY)) {
                    dm.a(MapsInitializer.KEY);
                }
                dm.a(this.a.H, a);
                if (dm.a == 0) {
                    this.a.l.sendEmptyMessage(2);
                }
                dp.a a2 = dp.a(this.a.H, a, "common;exception;sdkcoordinate;sdkupdate");
                if (a2 != null) {
                    if (a2.g != null) {
                        a.a(a2.g.a);
                    }
                    if (a2.i != null) {
                        new du(this.a.H, r.b, a2.i.a, a2.i.b).a();
                    }
                    if (a2.h != null) {
                        c cVar = a2.h;
                        if (cVar != null) {
                            Object obj = cVar.b;
                            Object obj2 = cVar.a;
                            Object obj3 = cVar.c;
                            if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj3)) {
                                new ey(this.a.H, null, a).a();
                            } else {
                                new ey(this.a.H, new ez(obj2, obj, obj3), a).a();
                            }
                        } else {
                            new ey(this.a.H, null, a).a();
                        }
                    }
                }
                r.h = a;
                ee.a(this.a.H, a);
                interrupt();
                this.a.setRunLowFrame(false);
            }
        } catch (Throwable th) {
            interrupt();
            ee.a(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
        }
    }
}
