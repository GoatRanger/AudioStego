package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.b;
import com.nokia.maps.a.c;
import com.nokia.maps.a.i;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;

@HybridPlus
public class CityCoverageRequest extends AbstractListRequest<CityCoverageResult> {
    private i a;

    @HybridPlus
    public enum UpdateType {
        ALL,
        NEW,
        UPDATED
    }

    /* synthetic */ b a() {
        return c();
    }

    /* synthetic */ c b() {
        return c();
    }

    private CityCoverageRequest(i iVar) {
        super(iVar);
        if (iVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = iVar;
    }

    public CityCoverageRequest setRequestCityDetailsEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public CityCoverageRequest setNearbyMax(int i) {
        this.a.b(i);
        return this;
    }

    public CityCoverageRequest setRadius(int i) {
        this.a.c(i);
        return this;
    }

    public CityCoverageRequest setLocation(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    public CityCoverageRequest setTime(Date date) {
        this.a.a(date);
        return this;
    }

    public CityCoverageRequest setUpdateType(UpdateType updateType) {
        this.a.a(updateType);
        return this;
    }

    i c() {
        return this.a;
    }

    static {
        i.a(new am<CityCoverageRequest, i>() {
            public CityCoverageRequest a(i iVar) {
                if (iVar == null) {
                    return null;
                }
                try {
                    return new CityCoverageRequest(iVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
