package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ae;
import com.nokia.maps.a.b;
import com.nokia.maps.a.c;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;
import java.util.Set;

@HybridPlus
public class MultiBoardRequest extends AbstractListRequest<MultiBoardResult> {
    private ae a;

    /* synthetic */ b a() {
        return c();
    }

    /* synthetic */ c b() {
        return c();
    }

    private MultiBoardRequest(ae aeVar) {
        super(aeVar);
        if (aeVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = aeVar;
    }

    public MultiBoardRequest setDepartureTime(Date date) {
        this.a.a(date);
        return this;
    }

    public MultiBoardRequest setRequestRealTimeInfoEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public MultiBoardRequest setStationIds(Set<String> set) {
        this.a.a((Set) set);
        return this;
    }

    public MultiBoardRequest setRadius(int i) {
        this.a.b(i);
        return this;
    }

    public MultiBoardRequest setMaxDeparturesPerStation(int i) {
        this.a.c(i);
        return this;
    }

    ae c() {
        return this.a;
    }

    static {
        ae.a(new am<MultiBoardRequest, ae>() {
            public MultiBoardRequest a(ae aeVar) {
                if (aeVar == null) {
                    return null;
                }
                try {
                    return new MultiBoardRequest(aeVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
