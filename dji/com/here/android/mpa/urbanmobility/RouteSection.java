package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.ar;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Collection;
import java.util.List;

@HybridPlus
public final class RouteSection {
    private ar a;

    private RouteSection(ar arVar) {
        if (arVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = arVar;
    }

    public Line getLine() {
        return this.a.a();
    }

    public TransportType getTransportType() {
        return this.a.f();
    }

    public Location getDepartureLocation() {
        return this.a.c();
    }

    public Location getArrivalLocation() {
        return this.a.b();
    }

    public List<GeoCoordinate> getGeometry() {
        return this.a.d();
    }

    public Collection<Ticket> getTickets() {
        return this.a.e();
    }

    public List<IntermediateStop> getIntermediateStops() {
        return this.a.g();
    }

    public int getDistance() {
        return this.a.h();
    }

    public long getDuration() {
        return this.a.i();
    }

    public List<Maneuver> getManeuvers() {
        return this.a.j();
    }

    public Collection<Link> getOperatorDisclaimers() {
        return this.a.k();
    }

    public Collection<Alert> getAlerts() {
        return this.a.l();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((RouteSection) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ar.a(new k<RouteSection, ar>() {
            public ar a(RouteSection routeSection) {
                return routeSection != null ? routeSection.a : null;
            }
        }, new am<RouteSection, ar>() {
            public RouteSection a(ar arVar) {
                return new RouteSection(arVar);
            }
        });
    }
}
