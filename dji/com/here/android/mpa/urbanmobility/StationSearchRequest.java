package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.au;
import com.nokia.maps.a.b;
import com.nokia.maps.a.c;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public final class StationSearchRequest extends AbstractListRequest<StationSearchResult> {
    private au a;

    /* synthetic */ b a() {
        return c();
    }

    /* synthetic */ c b() {
        return c();
    }

    private StationSearchRequest(au auVar) {
        super(auVar);
        if (auVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = auVar;
    }

    @Deprecated
    public StationSearchRequest setRadius(float f) {
        this.a.a(f);
        return this;
    }

    public StationSearchRequest setRadius(int i) {
        this.a.b(i);
        return this;
    }

    @Deprecated
    public StationSearchRequest setStationDetailsReturned(boolean z) {
        this.a.a(Boolean.valueOf(z));
        return this;
    }

    public StationSearchRequest setRequestStationDetailsEnabled(boolean z) {
        this.a.a(Boolean.valueOf(z));
        return this;
    }

    public StationSearchRequest setStationNameMatchingMethod(NameMatchingMethod nameMatchingMethod) {
        this.a.a(nameMatchingMethod);
        return this;
    }

    public StationSearchRequest setMaximumResults(int i) {
        super.setMaximumResults(i);
        return this;
    }

    @Deprecated
    public float getRadius() {
        return (float) this.a.i();
    }

    @Deprecated
    public boolean isStationDetailsReturned() {
        return this.a.j();
    }

    @Deprecated
    public NameMatchingMethod getStationNameMatchingMethod() {
        return this.a.k();
    }

    @Deprecated
    public String getStationName() {
        return this.a.l();
    }

    @Deprecated
    public Collection<String> getStationIds() {
        return this.a.m();
    }

    @Deprecated
    public GeoCoordinate getStationCoordinate() {
        return this.a.n();
    }

    au c() {
        return this.a;
    }

    static {
        au.a(new 1());
    }
}
