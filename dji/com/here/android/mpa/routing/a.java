package com.here.android.mpa.routing;

import com.nokia.maps.am;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.ec;
import com.nokia.maps.k;

@Internal
public final class a {
    private ec a;

    private a(ec ecVar) {
        this.a = ecVar;
    }

    static {
        ec.a(new k<a, ec>() {
            public ec a(a aVar) {
                return aVar.a;
            }
        }, new am<a, ec>() {
            public a a(ec ecVar) {
                return ecVar != null ? new a(ecVar) : null;
            }
        });
    }
}
