package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.z;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class Link {
    private z a;

    @HybridPlus
    public enum LinkType {
        AGENCY,
        AGENCY_LOGO,
        TARIFF,
        ALERT,
        WEBSITE,
        BOOKING,
        UNKNOWN
    }

    private Link(z zVar) {
        if (zVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = zVar;
    }

    public String getText() {
        return this.a.a();
    }

    public String getUrl() {
        return this.a.b();
    }

    public String getUrlText() {
        return this.a.c();
    }

    public LinkType getType() {
        return this.a.d();
    }

    public boolean isRequired() {
        return this.a.e();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Link) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        z.a(new am<Link, z>() {
            public Link a(z zVar) {
                return new Link(zVar);
            }
        });
    }
}
