package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.g;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;

@HybridPlus
public final class AlternativeDeparture extends AbstractDeparture<g> {
    AlternativeDeparture(g gVar) {
        super(gVar);
    }

    public Date getTime() {
        return super.getTime();
    }

    static {
        g.a(new am<AlternativeDeparture, g>() {
            public AlternativeDeparture a(g gVar) {
                return new AlternativeDeparture(gVar);
            }
        });
    }
}
