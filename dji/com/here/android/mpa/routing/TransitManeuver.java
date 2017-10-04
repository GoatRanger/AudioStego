package com.here.android.mpa.routing;

import com.here.android.mpa.common.TransitType;
import com.nokia.maps.TransitManeuverImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class TransitManeuver extends Maneuver {
    private TransitManeuverImpl a;

    @HybridPlus
    public enum TransitLineStyle {
        SOLID(0),
        DOTTED(1),
        DASHED(2),
        UNDEFINED(3);
        
        private int a;

        private TransitLineStyle(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    private TransitManeuver(TransitManeuverImpl transitManeuverImpl) {
        super(transitManeuverImpl);
        this.a = transitManeuverImpl;
    }

    public String getDepartureStopName() {
        return this.a.getDepartureStopName();
    }

    public String getArrivalStopName() {
        return this.a.getArrivalStopName();
    }

    public String getTerminusStopName() {
        return this.a.getTerminusStopName();
    }

    public String getLineName() {
        return this.a.getLineName();
    }

    public boolean hasPrimaryLineColor() {
        return this.a.n();
    }

    public boolean hasSecondaryLineColor() {
        return this.a.o();
    }

    public int getPrimaryLineColor() {
        return this.a.p();
    }

    public int getSecondaryLineColor() {
        return this.a.q();
    }

    public TransitLineStyle getLineStyle() {
        return this.a.r();
    }

    public String getSystemOfficialName() {
        return this.a.getSystemOfficialName();
    }

    public String getSystemInformalName() {
        return this.a.s();
    }

    public String getSystemShortName() {
        return this.a.s();
    }

    public String getTransitTypeName() {
        return this.a.getTransitTypeName();
    }

    public TransitType getTransitType() {
        return this.a.getTransitType();
    }

    public int getTransitTravelTime() {
        return this.a.getTransitTravelTime();
    }

    public List<TransitRouteElement> getTransitRouteElements() {
        return this.a.v();
    }

    static {
        TransitManeuverImpl.b(new k<TransitManeuver, TransitManeuverImpl>() {
            public TransitManeuverImpl a(TransitManeuver transitManeuver) {
                return transitManeuver.a;
            }
        }, new am<TransitManeuver, TransitManeuverImpl>() {
            public TransitManeuver a(TransitManeuverImpl transitManeuverImpl) {
                return transitManeuverImpl != null ? new TransitManeuver(transitManeuverImpl) : null;
            }
        });
    }
}
