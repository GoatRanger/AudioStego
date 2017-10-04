package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.m;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;
import java.util.List;

@HybridPlus
public class CitySearchResult {
    private m a;

    CitySearchResult(m mVar) {
        if (mVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = mVar;
    }

    public Date getRefTime() {
        return this.a.b();
    }

    public int getRealTimeCount() {
        return this.a.c();
    }

    public int getSimpleRoutingCount() {
        return this.a.d();
    }

    public int getTimeTableCount() {
        return this.a.e();
    }

    public List<City> getCities() {
        return this.a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((CitySearchResult) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        m.b(new am<CitySearchResult, m>() {
            public CitySearchResult a(m mVar) {
                return new CitySearchResult(mVar);
            }
        });
    }
}
