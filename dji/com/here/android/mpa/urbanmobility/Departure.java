package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.r;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public final class Departure extends AbstractDeparture<r> {
    Departure(r rVar) {
        super(rVar);
    }

    public Station getStation() {
        return ((r) this.a).m();
    }

    public String getPlatform() {
        return ((r) this.a).j();
    }

    public DepartureFrequency getDepartureFrequency() {
        return ((r) this.a).k();
    }

    public List<AlternativeDeparture> getAlternativeDepartures() {
        return ((r) this.a).l();
    }

    static {
        r.a(new am<Departure, r>() {
            public Departure a(r rVar) {
                return new Departure(rVar);
            }
        });
    }
}
