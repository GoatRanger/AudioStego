package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ag;
import com.nokia.maps.a.c;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class NearbyCoverageRequest extends AbstractRequest<NearbyCoverageResult> {
    private ag a;

    /* synthetic */ c b() {
        return a();
    }

    private NearbyCoverageRequest(ag agVar) {
        super(agVar);
        if (agVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = agVar;
    }

    public NearbyCoverageRequest setRequestCityDetailsEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    ag a() {
        return this.a;
    }

    static {
        ag.a(new am<NearbyCoverageRequest, ag>() {
            public NearbyCoverageRequest a(ag agVar) {
                if (agVar == null) {
                    return null;
                }
                try {
                    return new NearbyCoverageRequest(agVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
