package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.TransitType;
import com.nokia.maps.a.w;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class LineCategory {
    private w a;

    private LineCategory(w wVar) {
        if (wVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = wVar;
    }

    @Deprecated
    public TransitType getTransitType() {
        return this.a.c();
    }

    public TransportType getTransportType() {
        return this.a.b();
    }

    public String getLocalName() {
        return this.a.a();
    }

    public String getIconUrl(LineCategoryIconSize lineCategoryIconSize) {
        return this.a.a(lineCategoryIconSize);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((LineCategory) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        w.a(new 1());
    }
}
