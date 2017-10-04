package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.b;
import com.nokia.maps.a.c;
import com.nokia.maps.a.l;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class CitySearchRequest extends AbstractListRequest<CitySearchResult> {
    private l a;

    /* synthetic */ b a() {
        return c();
    }

    /* synthetic */ c b() {
        return c();
    }

    private CitySearchRequest(l lVar) {
        super(lVar);
        if (lVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = lVar;
    }

    public CitySearchRequest setRequestCityDetailsEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    l c() {
        return this.a;
    }

    static {
        l.a(new am<CitySearchRequest, l>() {
            public CitySearchRequest a(l lVar) {
                if (lVar == null) {
                    return null;
                }
                try {
                    return new CitySearchRequest(lVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
