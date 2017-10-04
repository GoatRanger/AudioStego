package com.nokia.maps;

import com.here.android.mpa.routing.RouteManager.Error;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;

class TransitRouteRestHandler$1 implements ApplicationContext$c {
    final /* synthetic */ et a;

    TransitRouteRestHandler$1(et etVar) {
        this.a = etVar;
    }

    @HybridPlusNative
    public void a() {
        ez.a(new Runnable(this) {
            final /* synthetic */ TransitRouteRestHandler$1 a;

            {
                this.a = r1;
            }

            public void run() {
                Error a = RouteManagerImpl.a(this.a.a.a()).a(this.a.a.a, this.a.a.b);
                if (a != Error.NONE) {
                    this.a.a.b.onCalculateRouteFinished(a, new ArrayList());
                }
            }
        });
    }

    @HybridPlusNative
    public void b() {
        this.a.c();
    }
}
