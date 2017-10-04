package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.e;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class Address {
    private e a;

    private Address(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = eVar;
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public String getName() {
        return this.a.b();
    }

    public String getCountry() {
        return this.a.c();
    }

    public String getCountryCode() {
        return this.a.d();
    }

    public String getState() {
        return this.a.e();
    }

    public String getPostalCode() {
        return this.a.f();
    }

    public String getCity() {
        return this.a.g();
    }

    public String getDistrict() {
        return this.a.h();
    }

    public String getStreet() {
        return this.a.i();
    }

    public String getHouseNumber() {
        return this.a.j();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Address) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        e.a(new am<Address, e>() {
            public Address a(e eVar) {
                return new Address(eVar);
            }
        });
    }
}
