package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;
import java.util.EnumSet;
import java.util.Iterator;

@Online
public enum Map$PedestrianFeature {
    CROSSWALK(1),
    STAIRS(2),
    ESCALATOR(4),
    ELEVATOR(8),
    TUNNEL(16),
    BRIDGE(32);
    
    private int a;

    private Map$PedestrianFeature(int i) {
        this.a = i;
    }

    static EnumSet<Map$PedestrianFeature> a(int i) {
        EnumSet<Map$PedestrianFeature> noneOf = EnumSet.noneOf(Map$PedestrianFeature.class);
        if ((i & 1) != 0) {
            noneOf.add(CROSSWALK);
        }
        if ((i & 2) != 0) {
            noneOf.add(STAIRS);
        }
        if ((i & 4) != 0) {
            noneOf.add(ESCALATOR);
        }
        if ((i & 8) != 0) {
            noneOf.add(ELEVATOR);
        }
        if ((i & 16) != 0) {
            noneOf.add(TUNNEL);
        }
        if ((i & 32) != 0) {
            noneOf.add(BRIDGE);
        }
        return noneOf;
    }

    static int a(EnumSet<Map$PedestrianFeature> enumSet) {
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((Map$PedestrianFeature) it.next()).a | i;
        }
        return i;
    }
}
