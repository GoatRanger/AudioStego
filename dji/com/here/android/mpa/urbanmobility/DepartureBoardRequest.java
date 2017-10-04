package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.b;
import com.nokia.maps.a.c;
import com.nokia.maps.a.p;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;

@HybridPlus
public final class DepartureBoardRequest extends AbstractListRequest<DepartureBoard> {
    private p a;

    /* synthetic */ b a() {
        return c();
    }

    /* synthetic */ c b() {
        return c();
    }

    private DepartureBoardRequest(p pVar) {
        super(pVar);
        if (pVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = pVar;
    }

    @Deprecated
    public DepartureBoardRequest setStrict(boolean z) {
        this.a.a(z);
        return this;
    }

    public DepartureBoardRequest setStrictResultEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public DepartureBoardRequest setTime(Date date) {
        this.a.a(date);
        return this;
    }

    @Deprecated
    public DepartureBoardRequest setRealTimeInfoReturned(boolean z) {
        this.a.b(z);
        return this;
    }

    public DepartureBoardRequest setRequestRealTimeInfoEnabled(boolean z) {
        this.a.b(z);
        return this;
    }

    @Deprecated
    public String getStationId() {
        return this.a.i();
    }

    @Deprecated
    public GeoCoordinate getStationCoordinate() {
        return this.a.j();
    }

    @Deprecated
    public boolean isStrict() {
        return this.a.k();
    }

    @Deprecated
    public Date getTime() {
        return this.a.l();
    }

    @Deprecated
    public boolean isRealTimeInfoReturned() {
        return this.a.m();
    }

    p c() {
        return this.a;
    }

    static {
        p.a(new 1());
    }
}
