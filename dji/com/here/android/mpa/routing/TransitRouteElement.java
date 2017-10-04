package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.TransitManeuver.TransitLineStyle;
import com.nokia.maps.TransitRouteElementImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Date;
import java.util.List;

@HybridPlus
public final class TransitRouteElement {
    private TransitRouteElementImpl a;

    private TransitRouteElement(TransitRouteElementImpl transitRouteElementImpl) {
        this.a = transitRouteElementImpl;
    }

    public String getDestination() {
        return this.a.getDestination();
    }

    public List<GeoCoordinate> getGeometry() {
        return this.a.a();
    }

    public List<GeoCoordinate> getLineGeometry() {
        return this.a.b();
    }

    public String getLineName() {
        return this.a.getLineName();
    }

    public boolean hasPrimaryLineColor() {
        return this.a.hasPrimaryLineColor();
    }

    public boolean hasSecondaryLineColor() {
        return this.a.hasSecondaryLineColor();
    }

    public int getPrimaryLineColor() {
        return this.a.c();
    }

    public int getSecondaryLineColor() {
        return this.a.d();
    }

    public TransitLineStyle getLineStyle() {
        return this.a.e();
    }

    public String getSystemOfficialName() {
        return this.a.getSystemOfficialName();
    }

    public String getSystemInformalName() {
        return this.a.getSystemInformalName();
    }

    public String getSystemShortName() {
        return this.a.getSystemShortName();
    }

    public Image getSystemLogo() {
        return this.a.f();
    }

    public Image getSystemAccessLogo() {
        return this.a.g();
    }

    public TransitType getTransitType() {
        return this.a.h();
    }

    public String getTransitTypeName() {
        return this.a.getTransitTypeName();
    }

    public final int getVehicleTravelTime() {
        return this.a.getVehicleTravelTime();
    }

    public final int getDuration() {
        return this.a.getTotalDuration();
    }

    public final Date getDepartureTime() {
        return this.a.i();
    }

    public final Date getArrivalTime() {
        return this.a.j();
    }

    public TransitRouteStop getDepartureStop() {
        return this.a.k();
    }

    public TransitRouteStop getArrivalStop() {
        return this.a.l();
    }

    public final Identifier getId() {
        return this.a.m();
    }

    static {
        TransitRouteElementImpl.a(new k<TransitRouteElement, TransitRouteElementImpl>() {
            public TransitRouteElementImpl a(TransitRouteElement transitRouteElement) {
                return transitRouteElement.a;
            }
        }, new am<TransitRouteElement, TransitRouteElementImpl>() {
            public TransitRouteElement a(TransitRouteElementImpl transitRouteElementImpl) {
                return transitRouteElementImpl != null ? new TransitRouteElement(transitRouteElementImpl) : null;
            }
        });
    }
}
