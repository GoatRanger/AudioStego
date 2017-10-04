package com.here.android.mpa.routing;

import com.here.android.mpa.urbanmobility.Location;
import com.here.android.mpa.urbanmobility.RouteSection;
import com.here.android.mpa.urbanmobility.TicketCollection;
import com.nokia.maps.a.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public final class UMRoute extends Route {
    private final am a;

    private UMRoute(am amVar) {
        super(amVar);
        this.a = amVar;
    }

    public List<Maneuver> getManeuvers() {
        return this.a.d();
    }

    public String getId() {
        return this.a.n();
    }

    public int getChangesCount() {
        return this.a.o();
    }

    public long getDuration() {
        return this.a.p();
    }

    public Location getDepartureLocation() {
        return this.a.q();
    }

    public Location getArrivalLocation() {
        return this.a.r();
    }

    public List<RouteSection> getSections() {
        return this.a.s();
    }

    public List<TicketCollection> getTicketCollections() {
        return this.a.v();
    }

    static {
        am.a(new com.nokia.maps.am<UMRoute, am>() {
            public UMRoute a(am amVar) {
                if (amVar == null) {
                    return null;
                }
                try {
                    return new UMRoute(amVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
